<<<<<<< HEAD
package net.intercomet.iccore.commands;

import net.intercomet.iccore.Intercomet;
import net.intercomet.iccore.util.Values;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import static net.intercomet.iccore.util.Perm.checkPerms;
import static net.intercomet.iccore.util.Perm.noPerms;

public class TPAHereCommand implements CommandExecutor {
    private final Intercomet plugin;
    private final long waitTime;

    public TPAHereCommand(Intercomet plugin, long waitTime) {
        this.plugin = plugin;
        this.waitTime = waitTime;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player player)) return true;

        if (!checkPerms(player, "core.all.tpa")) return noPerms(player);

        if (args.length == 0) {
            player.sendMessage(Values.getPrefix() + "You must specify a player!");
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            player.sendMessage(Values.getPrefix() + "That player is not online!");

            return true;
        }

        long now = System.currentTimeMillis();
        long last = plugin.getTpCooldowns().getOrDefault(player.getUniqueId(), 0L);

        long remaining = (last + (waitTime * 1000L)) - now;

        if (remaining > 0L) {
            player.sendMessage("§7You must wait " + (remaining / 1000) + " seconds before you can teleport!");

            return true;
        } else if (remaining < 0L) {
            sender.getServer().dispatchCommand(sender, "tp " + target.getName() + "" + player.getName());
        }

        return true;
    }
}
=======
package net.intercomet.iccore.commands;

import net.intercomet.iccore.Intercomet;
import net.intercomet.iccore.util.Values;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import static net.intercomet.iccore.util.Perm.checkPerms;
import static net.intercomet.iccore.util.Perm.noPerms;

public class TPAHereCommand implements CommandExecutor {
    private final Intercomet plugin;
    private final long waitTime;

    public TPAHereCommand(Intercomet plugin, long waitTime) {
        this.plugin = plugin;
        this.waitTime = waitTime;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player player)) return true;

        if (!checkPerms(player, "core.all.tpa")) return noPerms(player);

        if (args.length == 0) {
            player.sendMessage(Values.getPrefix() + "You must specify a player!");
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            player.sendMessage(Values.getPrefix() + "That player is not online!");

            return true;
        }

        long now = System.currentTimeMillis();
        long last = plugin.getTpCooldowns().getOrDefault(player.getUniqueId(), 0L);

        long remaining = (last + (waitTime * 1000L)) - now;

        if (remaining > 0L) {
            player.sendMessage("§7You must wait " + (remaining / 1000) + " seconds before you can teleport!");

            return true;
        } else if (remaining < 0L) {
            sender.getServer().dispatchCommand(sender, "tp " + target.getName() + "" + player.getName());
        }

        return true;
    }
}
>>>>>>> 6c8a590 (mmm)
