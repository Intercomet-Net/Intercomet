package net.intercomet.iccore.commands;

import net.intercomet.iccore.util.Values;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class MSGCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {
        if (!(args.length == 2)) {
            sender.sendMessage(Values.getPrefix() + "Usage: /" + command.getName() + " <player> <message>");

            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            sender.sendMessage(Values.getPrefix() + args[0] + " is not online");

            return true;
        }

        String message = args[1];

        target.sendMessage("§b[From %s] %s".formatted(sender.getName(), message));
        sender.sendMessage("§b[To %s] %s".formatted(target.getName(), message));

        return true;
    }
}
