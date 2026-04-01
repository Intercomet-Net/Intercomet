package net.intercomet.iccore.commands;

import net.intercomet.iccore.Intercomet;
import net.intercomet.iccore.util.Values;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import static net.intercomet.iccore.util.Perm.checkPerms;
import static net.intercomet.iccore.util.Perm.noPerms;

public class VanishCommand implements CommandExecutor {
    private final JavaPlugin plugin;

    public VanishCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {
        if (!(commandSender instanceof Player player)) return true;

        if (!checkPerms(player, "core.staff.vanish")) return noPerms(player);

        if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);


        }

        if (((Intercomet) plugin).getVanishedPlayers().contains(player.getUniqueId())) {
            ((Intercomet) plugin).getVanishedPlayers().remove(player.getUniqueId());

            for (Player p : Bukkit.getOnlinePlayers()) {
                p.showPlayer(plugin, player);
            }

            player.sendMessage(Values.getPrefix() + "You are now " + Values.getAltPrefix() + "UNVANISHED");
        } else {
            ((Intercomet) plugin).getVanishedPlayers().add(player.getUniqueId());

            for (Player p : Bukkit.getOnlinePlayers()) {
                if (!checkPerms(p, "core.staff.vanish")) p.hidePlayer(plugin, player);
            }

            player.sendMessage(Values.getPrefix() + "You are now " + Values.getAltPrefix() + "VANISHED");
        }

        return true;
    }
}
