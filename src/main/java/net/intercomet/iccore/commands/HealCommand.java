<<<<<<< HEAD
package net.intercomet.iccore.commands;

import net.intercomet.iccore.util.Perm;
import net.intercomet.iccore.util.Values;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class HealCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {
        if (!(sender instanceof Player player)) return true;

        if (!Perm.checkPerms(player, "core.staff.heal")) return Perm.noPerms(player);

        if (args.length != 1) {
            player.setHealth(20);
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target != null) {
            target.setHealth(20);
        } else {
            player.sendMessage(Values.getPrefix() + "Player not found!");
        }

        return true;
    }
}
=======
package net.intercomet.iccore.commands;

import net.intercomet.iccore.util.Perm;
import net.intercomet.iccore.util.Values;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class HealCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {
        if (!(sender instanceof Player player)) return true;

        if (!Perm.checkPerms(player, "core.staff.heal")) return Perm.noPerms(player);

        if (args.length != 1) {
            player.setHealth(20);
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target != null) {
            target.setHealth(20);
        } else {
            player.sendMessage(Values.getPrefix() + "Player not found!");
        }

        return true;
    }
}
>>>>>>> 6c8a590 (mmm)
