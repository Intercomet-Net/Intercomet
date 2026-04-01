<<<<<<< HEAD
package net.intercomet.iccore.commands;

import net.intercomet.iccore.Intercomet;
import net.intercomet.iccore.util.Time;
import net.intercomet.iccore.util.Values;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

import static net.intercomet.iccore.util.Perm.checkPerms;
import static net.intercomet.iccore.util.Perm.noPerms;

public class SeenCommand implements CommandExecutor, Listener {
    private final Intercomet plugin;

    public SeenCommand(Intercomet plugin) {
        this.plugin = plugin;
    }

    public long getLastSeen(UUID uuid) {
        return plugin.getConfig().getLong("lastseen." + uuid, -1);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player player)) return true;

        if (!checkPerms(player, "core.staff.seen")) return noPerms(player);

        if (args.length == 0) {
            player.sendMessage(Values.getStaffPrefix() + "Usage: /seen <player>");
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target != null) {
            player.sendMessage(Values.getStaffPrefix() + "is currently online");
        }

        OfflinePlayer offline = Bukkit.getOfflinePlayer(args[0]);
        long lastSeen = getLastSeen(offline.getUniqueId());

        player.sendMessage(Values.getStaffPrefix() + "Last seen: " + Time.formatTime(lastSeen) + " ago");

        return true;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        plugin.getConfig().set("lastseen." + player.getUniqueId(), System.currentTimeMillis());

        plugin.getLastSeenCache().put(player.getUniqueId(), System.currentTimeMillis());
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if (!checkPerms(player, "core.staff")) return;

        plugin.getLastSeenCache().put(player.getUniqueId(), System.currentTimeMillis());

        plugin.getConfig().set("lastseen." + player.getUniqueId(), System.currentTimeMillis());

        plugin.saveConfig();
    }
}
=======
package net.intercomet.iccore.commands;

import net.intercomet.iccore.Intercomet;
import net.intercomet.iccore.util.Time;
import net.intercomet.iccore.util.Values;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

import static net.intercomet.iccore.util.Perm.checkPerms;
import static net.intercomet.iccore.util.Perm.noPerms;

public class SeenCommand implements CommandExecutor, Listener {
    private final Intercomet plugin;

    public SeenCommand(Intercomet plugin) {
        this.plugin = plugin;
    }

    public long getLastSeen(UUID uuid) {
        return plugin.getConfig().getLong("lastseen." + uuid, -1);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player player)) return true;

        if (!checkPerms(player, "core.staff.seen")) return noPerms(player);

        if (args.length == 0) {
            player.sendMessage(Values.getStaffPrefix() + "Usage: /seen <player>");
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target != null) {
            player.sendMessage(Values.getStaffPrefix() + "is currently online");
        }

        OfflinePlayer offline = Bukkit.getOfflinePlayer(args[0]);
        long lastSeen = getLastSeen(offline.getUniqueId());

        player.sendMessage(Values.getStaffPrefix() + "Last seen: " + Time.formatTime(lastSeen) + " ago");

        return true;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        plugin.getConfig().set("lastseen." + player.getUniqueId(), System.currentTimeMillis());

        plugin.getLastSeenCache().put(player.getUniqueId(), System.currentTimeMillis());
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if (!checkPerms(player, "core.staff")) return;

        plugin.getLastSeenCache().put(player.getUniqueId(), System.currentTimeMillis());

        plugin.getConfig().set("lastseen." + player.getUniqueId(), System.currentTimeMillis());

        plugin.saveConfig();
    }
}
>>>>>>> 6c8a590 (mmm)
