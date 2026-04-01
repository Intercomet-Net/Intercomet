package net.intercomet.iccore.commands;

import net.intercomet.iccore.Intercomet;
import net.intercomet.iccore.util.Values;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.jetbrains.annotations.NotNull;

import static net.intercomet.iccore.util.Perm.checkPerms;
import static net.intercomet.iccore.util.Perm.noPerms;

public class FreezeCommand implements CommandExecutor, Listener {
    private final Intercomet plugin;

    public FreezeCommand(Intercomet plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {
        if (!checkPerms((Player) commandSender, "core.staff.freeze")) return noPerms((Player) commandSender);

        if (!(args.length == 1)) {
            commandSender.sendMessage(Values.getPrefix() + "Usage: /freeze <player>");

            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            commandSender.sendMessage(Values.getPrefix() + target.getName() + " is not online!");

            return true;
        }

        target.setWalkSpeed(0);

        if (target.isFlying()) {
            target.setAllowFlight(false);
            target.setFlying(false);
        }

        plugin.getFrozenPlayers().put(target.getUniqueId(), true);

        return true;
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (plugin.getFrozenPlayers().containsKey(event.getEntity().getUniqueId())) {
            event.setCancelled(true);
        }
    }
}
