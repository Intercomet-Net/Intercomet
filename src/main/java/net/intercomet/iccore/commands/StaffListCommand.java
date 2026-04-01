<<<<<<< HEAD
package net.intercomet.iccore.commands;

import net.intercomet.iccore.Intercomet;
import net.intercomet.iccore.util.Time;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static net.intercomet.iccore.util.Perm.checkPerms;

public class StaffListCommand implements CommandExecutor {
    private final Intercomet plugin;

    public StaffListCommand(Intercomet plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {
        if (!(sender instanceof Player player)) return true;

        player.sendMessage("§8§m-------------------------");
        player.sendMessage("§eStaff List");

        player.sendMessage("§aOnline:");

        for (Player online : Bukkit.getOnlinePlayers()) {
            if (checkPerms(player, "core.staff")) {
                player.sendMessage(" §7- §a" + online.getDisplayName());
            }
        }

        player.sendMessage("§cOffline:");

        for (OfflinePlayer offline : Bukkit.getOfflinePlayers()) {
            if (offline.isOnline()) continue;

            if (!checkPerms(player, "core.staff") || !offline.hasPlayedBefore()) continue;

            long lastseen = plugin.getLastSeenCache().get(offline.getUniqueId());

            if (lastseen == -1) continue;

            player.sendMessage(" §7- §c" + offline.getName()
                    + " §7(" + Time.formatTime(lastseen) + " ago)");

            player.sendMessage("§8§m-------------------------");
        }

        return true;
    }
}
=======
package net.intercomet.iccore.commands;

import net.intercomet.iccore.Intercomet;
import net.intercomet.iccore.util.Time;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static net.intercomet.iccore.util.Perm.checkPerms;

public class StaffListCommand implements CommandExecutor {
    private final Intercomet plugin;

    public StaffListCommand(Intercomet plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {
        if (!(sender instanceof Player player)) return true;

        player.sendMessage("§8§m-------------------------");
        player.sendMessage("§eStaff List");

        player.sendMessage("§aOnline:");

        for (Player online : Bukkit.getOnlinePlayers()) {
            if (checkPerms(player, "core.staff")) {
                player.sendMessage(" §7- §a" + online.getDisplayName());
            }
        }

        player.sendMessage("§cOffline:");

        for (OfflinePlayer offline : Bukkit.getOfflinePlayers()) {
            if (offline.isOnline()) continue;

            if (!checkPerms(player, "core.staff") || !offline.hasPlayedBefore()) continue;

            long lastseen = plugin.getLastSeenCache().get(offline.getUniqueId());

            if (lastseen == -1) continue;

            player.sendMessage(" §7- §c" + offline.getName()
                    + " §7(" + Time.formatTime(lastseen) + " ago)");

            player.sendMessage("§8§m-------------------------");
        }

        return true;
    }
}
>>>>>>> 6c8a590 (mmm)
