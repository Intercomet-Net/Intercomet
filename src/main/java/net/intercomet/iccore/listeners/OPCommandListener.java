<<<<<<< HEAD
package net.intercomet.iccore.listeners;

import net.intercomet.iccore.Intercomet;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class OPCommandListener implements Listener {
    private final Intercomet plugin;

    public OPCommandListener(Intercomet plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();

        if (!player.isOp()) return;

        String cmd = event.getMessage();

        plugin.sendStaffAlert("§e" + player.getName() + "used: §7" + cmd);
    }
=======
package net.intercomet.iccore.listeners;

import net.intercomet.iccore.Intercomet;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class OPCommandListener implements Listener {
    private final Intercomet plugin;

    public OPCommandListener(Intercomet plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();

        if (!player.isOp()) return;

        String cmd = event.getMessage();

        plugin.sendStaffAlert("§e" + player.getName() + "used: §7" + cmd);
    }
>>>>>>> 6c8a590 (mmm)
}