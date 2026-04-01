<<<<<<< HEAD
package net.intercomet.iccore.commands;

import net.intercomet.iccore.util.Values;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

import static net.intercomet.iccore.util.Perm.checkPerms;
import static net.intercomet.iccore.util.Perm.noPerms;

public class AlertCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Server server = sender.getServer();

        if (sender instanceof Player p) {
            if (!checkPerms(p, "core.staff.alert")) return noPerms(p);

            if (args.length == 0) {
                p.sendMessage(Values.getPrefix() + "You must specify a message!");
            }

            String message = String.join(args[0]);

            server.sendPlainMessage(Values.getPrefix() + "(" + p.getName() + ")");
            server.sendPlainMessage("§7 §7 §7§l➥ " + message);
        }

        if (!(sender instanceof Player)) {
            if (args.length == 0) {
                server.getConsoleSender().sendMessage(Values.getPrefix() + "You must specify a message!");
            }

            String message = String.join(args[0]);

            server.sendPlainMessage(Values.getPrefix());
            server.sendPlainMessage("§7 §7 §7§l➥ " + message);
        }

        return true;
    }
}
=======
package net.intercomet.iccore.commands;

import net.intercomet.iccore.util.Values;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

import static net.intercomet.iccore.util.Perm.checkPerms;
import static net.intercomet.iccore.util.Perm.noPerms;

public class AlertCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Server server = sender.getServer();

        if (sender instanceof Player p) {
            if (!checkPerms(p, "core.staff.alert")) return noPerms(p);

            if (args.length == 0) {
                p.sendMessage(Values.getPrefix() + "You must specify a message!");
            }

            String message = String.join(args[0]);

            server.sendPlainMessage(Values.getPrefix() + "(" + p.getName() + ")");
            server.sendPlainMessage("§7 §7 §7§l➥ " + message);
        }

        if (!(sender instanceof Player)) {
            if (args.length == 0) {
                server.getConsoleSender().sendMessage(Values.getPrefix() + "You must specify a message!");
            }

            String message = String.join(args[0]);

            server.sendPlainMessage(Values.getPrefix());
            server.sendPlainMessage("§7 §7 §7§l➥ " + message);
        }

        return true;
    }
}
>>>>>>> 6c8a590 (mmm)
