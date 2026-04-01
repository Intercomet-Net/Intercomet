package net.intercomet.iccore.commands;

import net.intercomet.iccore.util.Values;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static net.intercomet.iccore.util.Perm.checkPerms;
import static net.intercomet.iccore.util.Perm.noPerms;

public class GMSPCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if (!(commandSender instanceof Player player)) return true;

        if (!checkPerms(player, "core.staff.gmsp")) return noPerms(player);

        player.setGameMode(GameMode.SPECTATOR);
        player.sendMessage(Values.getPrefix() + "Your gamemode has been set " + Values.getAltPrefix() + "SPECTATOR");

        return true;
    }
}
