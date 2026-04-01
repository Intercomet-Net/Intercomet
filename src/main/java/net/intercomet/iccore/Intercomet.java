package net.intercomet.iccore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import net.intercomet.iccore.commands.*;
import net.intercomet.iccore.commands.complete.*;
import net.intercomet.iccore.data.PunishContext;
import net.intercomet.iccore.data.Punishment;
import net.intercomet.iccore.data.db.DatabaseManager;
import net.intercomet.iccore.data.player.Profile;
import net.intercomet.iccore.data.player.ProfileManager;
import net.intercomet.iccore.data.player.Stat;
import net.intercomet.iccore.listeners.*;
import net.intercomet.iccore.util.Values;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.h2.engine.Database;

import java.io.*;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static net.intercomet.iccore.util.Perm.checkPerms;

@SuppressWarnings({"ResultOfMethodCallIgnored", "DataFlowIssue"})
public final class Intercomet extends JavaPlugin implements Listener {
    private final DatabaseManager databaseManager = new DatabaseManager(this);
    private final ProfileManager profileManager = new ProfileManager(this);
    private final Set<UUID> staff = new HashSet<>();
    private final Set<UUID> vanishedPlayers = new HashSet<>();
    private final Map<UUID, UUID> invseeSessions = new HashMap<>();
    private final Map<UUID, PunishContext> punishContexts = new HashMap<>();
    private final Map<UUID, Integer> historyPageMap = new HashMap<>();
    private final Map<UUID, Boolean> staffToggleSessions = new HashMap<>();
    private final Map<UUID, Long> lastSeenCache =  new HashMap<>();
    private final Map<UUID, Long> chatCooldowns = new HashMap<>();
    private final Map<UUID, Boolean> frozenPlayers = new HashMap<>();
    private final Map<UUID, Long> tpCooldowns = new HashMap<>();
    private File punishmentsFile;
    private Gson gson;
    private LuckPerms luckPerms;
    private List<Punishment> punishments = new ArrayList<>();
    private final Set<UUID> staffAlerts = new HashSet<>();

    private final String PREFIX = Values.getPrefix();
    private final String ALT_PREFIX = Values.getAltPrefix();

    @Override
    public void onEnable() {
        luckPerms = LuckPermsProvider.get();

        saveDefaultConfig();

        for (Player p : getServer().getOnlinePlayers()) {
            if (checkPerms(p, "core.staff.chat") && !staff.contains(p)) {
                staff.add(p.getUniqueId());
            }
        }

        var filter = new ChatFilter(this);

        filter.loadFilter();

        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("punish").setExecutor(new PunishCommand(this));
        getCommand("punishhistory").setExecutor(new PunishHistoryCommand(this));
        getCommand("invsee").setExecutor(new InvSeeCommand(this));
        getCommand("gma").setExecutor(new GMACommand());
        getCommand("gms").setExecutor(new GMSCommand());
        getCommand("gmsp").setExecutor(new GMSPCommand());
        getCommand("gma").setExecutor(new GMACommand());
        getCommand("vanish").setExecutor(new VanishCommand(this));
        getCommand("staff").setExecutor(new StaffToggleCommand(this));
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("feed").setExecutor(new FeedComamnd());
        getCommand("stafflist").setExecutor(new StaffListCommand(this));
        getCommand("tpa").setExecutor(new TPACommand(this, getConfig().getInt("teleport.tpa.duration")));
        getCommand("tpahere").setExecutor(new TPACommand(this, getConfig().getInt("teleport.tpa.duration")));
        getCommand("freeze").setExecutor(new FreezeCommand(this));
        getCommand("unfreeze").setExecutor(new UnfreezeCommand(this));
        getCommand("settings").setExecutor(new SettingsCommand(this));

        getCommand("punish").setTabCompleter(new PunishTabCompleter());
        getCommand("invsee").setTabCompleter(new InvSeeTabCompleter());
        getCommand("heal").setTabCompleter(new HealTabCompleter());
        getCommand("feed").setTabCompleter(new FeedTabCompleter());
        getCommand("freeze").setTabCompleter(new PlayerTabCompleter());
        getCommand("unfreeze").setTabCompleter(new PlayerTabCompleter());

        Bukkit.getPluginManager().registerEvents(new OPCommandListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerLeaveListener(this), this);
        Bukkit.getPluginManager().registerEvents(new WelcomeListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ChatCooldown(this), this);
        Bukkit.getPluginManager().registerEvents(filter, this);
        Bukkit.getPluginManager().registerEvents(new SettingsListener(this), this);

        if (!this.getDataFolder().exists()) {
            this.getDataFolder().mkdirs();
        }

        punishmentsFile = new File(this.getDataFolder(), "punishments.json");

        gson = new GsonBuilder().setPrettyPrinting().create();

        createFiles();

        loadPunishments();

        this.getLogger().info("Successfully enabled.");

        Bukkit.getScheduler().runTaskAsynchronously(this, this::saveConfig);
    }

    private void createFiles() {
        try {
            if (!punishmentsFile.exists()) {
                punishmentsFile.createNewFile();
            }
        } catch (IOException e) {
            this.getLogger().warning("Could not create punishments file!");
            this.getLogger().warning("Data will not be saved on restart/stop!");
        }
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().runTask(this, this::savePunishments);

        this.getLogger().info("Successfully disabled.");
    }

    public void saveProfile(Profile profile) {
        UUID uuid = profile.getUniqueId();

        try (Connection conn = databaseManager.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("""
                MERGE INTO player_settings (uuid, private_messages, show_other_players, chat_pings)
                VALUES (?, ?, ?, ?)
            """);

            ps.setString(1, uuid.toString());
            ps.setBoolean(2, profile.getSettings().isPrivateMessages());
            ps.setBoolean(3, profile.getSettings().isShowOtherPlayers());
            ps.setBoolean(4, profile.getSettings().isChatPings());
            ps.executeUpdate();

            for (var entry : profile.getStats().entrySet()) {
                PreparedStatement statPS = conn.prepareStatement("""
                    MERGE INTO player_stats (uuid, stat_key, stat_value)
                    VALUES (?, ?, ?)
                """);

                statPS.setString(1, uuid.toString());
                statPS.setString(2, entry.getKey());
                statPS.setString(3, String.valueOf(entry.getValue().value()));

                statPS.executeUpdate();
                statPS.close();
            }

            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadProfile(Profile profile) {
        UUID uuid = profile.getUniqueId();

        try (Connection conn = databaseManager.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("""
                SELECT * FROM player_settings WHERE uuid = ?
            """);

            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                profile.getSettings().setPrivateMessages(rs.getBoolean("privateMessages"));
                profile.getSettings().setShowOtherPlayers(rs.getBoolean("showOtherPlayers"));
                profile.getSettings().setChatPings(rs.getBoolean("chatPings"));
            }

            rs.close();
            ps.close();

            PreparedStatement statPs = conn.prepareStatement("""
                SELECT stat_key, stat_value FROM player_stats WHERE uuid = ?
            """);

            statPs.setString(1, uuid.toString());
            ResultSet statRs = statPs.executeQuery();

            if (statRs.next()) {
                String key = statRs.getString("stat_key");
                String value = statRs.getString("stat_value");

                profile.setStats(key, new Stat<>(value));
            }

            statPs.close();
            statRs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<UUID, Long> getTpCooldowns() {
        return tpCooldowns;
    }

    public Map<UUID, Boolean> getFrozenPlayers() {
        return frozenPlayers;
    }

    public Map<UUID, Long> getCooldowns() {
        return chatCooldowns;
    }

    public LuckPerms getLuckPerms() {
        return luckPerms;
    }

    public Map<UUID, Long> getLastSeenCache() {
        return lastSeenCache;
    }

    public Map<UUID, Integer> getHistoryPages() {
        return historyPageMap;
    }

    public Set<UUID> getStaffAlerts() {
        return staffAlerts;
    }

    public void sendStaffAlert(String message) {
        for (UUID uuid : this.staffAlerts) {
            Player player = Bukkit.getPlayer(uuid);
            if (player != null && player.isOnline()) {
                player.sendMessage(Values.getStaffPrefix() + message);
            }
        }
    }

    public Map<UUID, PunishContext> getPunishContexts() {
        return punishContexts;
    }

    public Map<UUID, UUID> getInvseeSessions() {
        return invseeSessions;
    }

    public Set<UUID> getVanishedPlayers() {
        return vanishedPlayers;
    }

    public Map<UUID, Boolean> getStaffToggleSessions() {
        return staffToggleSessions;
    }

    public Set<UUID> getStaff() {
        return staff;
    }

    public void loadPunishments() {
        try (Reader reader = new FileReader(punishmentsFile)) {
            Type type = new TypeToken<List<Punishment>>() {}.getType();
            List<Punishment> data = gson.fromJson(reader, type);

            if (data != null) {
                punishments = data;
            }
        } catch (IOException e) {
            this.getLogger().warning("Could not load punishments!");
            this.getLogger().warning("There may be issues with recently punished players");
        }
    }

    public void savePunishments() {
        try (Writer writer = new FileWriter(punishmentsFile)) {
            gson.toJson(punishments, writer);
        } catch (IOException e) {
            this.getLogger().warning("Could not save punishments!");
            this.getLogger().warning("There may be issues with recently punished players");
        }
    }

    public List<Punishment> getPunishments() {
        return punishments;
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public ProfileManager getProfileManager() {
        return profileManager;
    }
}
