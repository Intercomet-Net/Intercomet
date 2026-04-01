<<<<<<< HEAD
package net.intercomet.iccore.commands;

import net.intercomet.iccore.util.Values;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static net.intercomet.iccore.util.Perm.checkPerms;
import static net.intercomet.iccore.util.Perm.noPerms;

public class FlyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if (!(commandSender instanceof Player player)) return true;

        if (!checkPerms(player, "core.staff.fly")) return noPerms(player);

        player.setAllowFlight(!player.getAllowFlight());
        player.sendMessage(Values.getPrefix() + "Flight mode set to " + (player.getAllowFlight() ? "ON" : "OFF"));

        return true;
    }
}
=======
package net.intercomet.iccore.commands;

import net.intercomet.iccore.util.Values;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static net.intercomet.iccore.util.Perm.checkPerms;
import static net.intercomet.iccore.util.Perm.noPerms;

public class FlyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if (!(commandSender instanceof Player player)) return true;

        if (!checkPerms(player, "core.staff.fly")) return noPerms(player);

        player.setAllowFlight(!player.getAllowFlight());
        player.sendMessage(Values.getPrefix() + "Flight mode set to " + (player.getAllowFlight() ? "ON" : "OFF"));

        return true;
    }
}
>>>>>>> 6c8a590 (mmm)
