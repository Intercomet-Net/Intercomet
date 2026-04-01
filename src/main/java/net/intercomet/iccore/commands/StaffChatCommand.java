<<<<<<< HEAD
package net.intercomet.iccore.commands;

import me.clip.placeholderapi.PlaceholderAPI;
import net.intercomet.iccore.Intercomet;
import net.intercomet.iccore.util.Values;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

import static net.intercomet.iccore.util.Perm.checkPerms;
import static net.intercomet.iccore.util.Perm.noPerms;

public class StaffChatCommand implements CommandExecutor, Listener {
    private final JavaPlugin plugin;
    private final Map<UUID, Boolean> staffToggleSessions;
    private final String luckpermsPrefix = "%luckperms_perfix%";
    private String resolved;

    public StaffChatCommand(JavaPlugin plugin) {
        this.plugin = plugin;
        this.staffToggleSessions = ((Intercomet) plugin).getStaffToggleSessions();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {
        if (!(sender instanceof Player player)) return true;

        if (!checkPerms(player, "core.staff.chat")) return noPerms(player);

        resolved = PlaceholderAPI.setPlaceholders(player, luckpermsPrefix);

        if (args.length != 1) {
            if (!staffToggleSessions.containsKey(player.getUniqueId())) {
                if (!checkPerms(player, "core.staff.chat.toggle")) return true;

                staffToggleSessions.put(player.getUniqueId(), true);
                player.sendMessage(Values.getStaffPrefix() + "Staff chat has been enabled");
            } else {
                if (!checkPerms(player, "core.staff.chat.toggle")) return true;

                staffToggleSessions.put(player.getUniqueId(), false);
                player.sendMessage(Values.getStaffPrefix() + "Staff chat has been disabled");
            }
        }

        if (args.length == 1) {
            for (UUID uuid : ((Intercomet) plugin).getStaff()) {
                Bukkit.getPlayer(uuid).sendMessage(Values.getStaffPrefix() + resolved + " " + Arrays.toString(args));
            }
        }

        return true;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (!checkPerms(event.getPlayer(), "core.staff.chat")) return;

        event.setCancelled(true);

        if (staffToggleSessions.containsKey(event.getPlayer().getUniqueId())) {
            for (UUID uuid : ((Intercomet) plugin).getStaff()) {
                resolved =  PlaceholderAPI.setPlaceholders(Bukkit.getPlayer(uuid), luckpermsPrefix);

                Bukkit.getPlayer(uuid).sendMessage(Values.getStaffPrefix() + resolved + " " + event.getMessage());
            }
        }
    }
}
=======
package net.intercomet.iccore.commands;

import me.clip.placeholderapi.PlaceholderAPI;
import net.intercomet.iccore.Intercomet;
import net.intercomet.iccore.util.Values;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

import static net.intercomet.iccore.util.Perm.checkPerms;
import static net.intercomet.iccore.util.Perm.noPerms;

public class StaffChatCommand implements CommandExecutor, Listener {
    private final JavaPlugin plugin;
    private final Map<UUID, Boolean> staffToggleSessions;
    private final String luckpermsPrefix = "%luckperms_perfix%";
    private String resolved;

    public StaffChatCommand(JavaPlugin plugin) {
        this.plugin = plugin;
        this.staffToggleSessions = ((Intercomet) plugin).getStaffToggleSessions();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {
        if (!(sender instanceof Player player)) return true;

        if (!checkPerms(player, "core.staff.chat")) return noPerms(player);

        resolved = PlaceholderAPI.setPlaceholders(player, luckpermsPrefix);

        if (args.length != 1) {
            if (!staffToggleSessions.containsKey(player.getUniqueId())) {
                if (!checkPerms(player, "core.staff.chat.toggle")) return true;

                staffToggleSessions.put(player.getUniqueId(), true);
                player.sendMessage(Values.getStaffPrefix() + "Staff chat has been enabled");
            } else {
                if (!checkPerms(player, "core.staff.chat.toggle")) return true;

                staffToggleSessions.put(player.getUniqueId(), false);
                player.sendMessage(Values.getStaffPrefix() + "Staff chat has been disabled");
            }
        }

        if (args.length == 1) {
            for (UUID uuid : ((Intercomet) plugin).getStaff()) {
                Bukkit.getPlayer(uuid).sendMessage(Values.getStaffPrefix() + resolved + " " + Arrays.toString(args));
            }
        }

        return true;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (!checkPerms(event.getPlayer(), "core.staff.chat")) return;

        event.setCancelled(true);

        if (staffToggleSessions.containsKey(event.getPlayer().getUniqueId())) {
            for (UUID uuid : ((Intercomet) plugin).getStaff()) {
                resolved =  PlaceholderAPI.setPlaceholders(Bukkit.getPlayer(uuid), luckpermsPrefix);

                Bukkit.getPlayer(uuid).sendMessage(Values.getStaffPrefix() + resolved + " " + event.getMessage());
            }
        }
    }
}
>>>>>>> 6c8a590 (mmm)
