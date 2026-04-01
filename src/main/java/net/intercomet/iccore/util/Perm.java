package net.intercomet.iccore.util;

import org.bukkit.entity.Player;

public class Perm {
    public static boolean noPerms(Player player) {
        player.sendMessage(Values.getPrefix() + "You do not have permissions to use this command.");

        return true;
    }

    public static boolean checkPerms(Player player, String perm) {
        return player.hasPermission(perm);
    }
}
