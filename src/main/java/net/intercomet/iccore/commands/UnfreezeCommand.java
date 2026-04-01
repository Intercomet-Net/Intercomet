package net.intercomet.iccore.commands;

import net.intercomet.iccore.Intercomet;
import net.intercomet.iccore.util.Values;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static net.intercomet.iccore.util.Perm.checkPerms;
import static net.intercomet.iccore.util.Perm.noPerms;

public class UnfreezeCommand implements CommandExecutor {
    private final Intercomet plugin;

    public UnfreezeCommand(Intercomet plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {
        if (!checkPerms((Player) commandSender, "core.staff.freeze")) return noPerms((Player) commandSender);

        if (!(args.length == 0)) {
            commandSender.sendMessage(Values.getPrefix() + "Usage: /freeze <player>");

            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        target.setWalkSpeed(0.1f);
        target.setAllowFlight(true);

        plugin.getFrozenPlayers().put(target.getUniqueId(), true);

        return true;
    }
}
