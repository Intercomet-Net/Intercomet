<<<<<<< HEAD
package net.intercomet.iccore.commands;

import net.intercomet.iccore.Intercomet;
import net.intercomet.iccore.util.Perm;
import net.intercomet.iccore.util.Values;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class StaffToggleCommand implements CommandExecutor {
    private final Intercomet plugin;

    public StaffToggleCommand(Intercomet plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if (!(sender instanceof Player player)) return true;

        if (!Perm.checkPerms(player, "core.staff.toggle")) return Perm.noPerms(player);

        UUID uuid = player.getUniqueId();

        if (plugin.getStaffAlerts().contains(uuid)) {
            plugin.getStaffAlerts().remove(uuid);
            player.sendMessage(Values.getStaffPrefix() + "Staff alerts have been disabled");
        } else {
            plugin.getStaffAlerts().add(uuid);
            player.sendMessage(Values.getStaffPrefix() + "Staff alerts have been enabled");
        }

        return true;
    }
}
=======
package net.intercomet.iccore.commands;

import net.intercomet.iccore.Intercomet;
import net.intercomet.iccore.util.Perm;
import net.intercomet.iccore.util.Values;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class StaffToggleCommand implements CommandExecutor {
    private final Intercomet plugin;

    public StaffToggleCommand(Intercomet plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if (!(sender instanceof Player player)) return true;

        if (!Perm.checkPerms(player, "core.staff.toggle")) return Perm.noPerms(player);

        UUID uuid = player.getUniqueId();

        if (plugin.getStaffAlerts().contains(uuid)) {
            plugin.getStaffAlerts().remove(uuid);
            player.sendMessage(Values.getStaffPrefix() + "Staff alerts have been disabled");
        } else {
            plugin.getStaffAlerts().add(uuid);
            player.sendMessage(Values.getStaffPrefix() + "Staff alerts have been enabled");
        }

        return true;
    }
}
>>>>>>> 6c8a590 (mmm)
