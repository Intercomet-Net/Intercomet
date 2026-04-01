<<<<<<< HEAD
package net.intercomet.iccore.listeners;

import net.intercomet.iccore.Intercomet;
import net.intercomet.iccore.data.player.Profile;
import net.intercomet.iccore.data.player.ProfileManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerLeaveListener implements Listener {
    private final Intercomet plugin;

    public PlayerLeaveListener(Intercomet plugin) {
        this.plugin = plugin;
    }

    @SuppressWarnings("DataFlowIssue")
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        plugin.getServer().sendPlainMessage(plugin.getConfig().getString("leave-message"));

        Profile profile = plugin.getProfileManager().getProfile(event.getPlayer().getUniqueId());

        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            plugin.saveProfile(profile);
        });
    }
}
=======
package net.intercomet.iccore.listeners;

import net.intercomet.iccore.Intercomet;
import net.intercomet.iccore.data.player.Profile;
import net.intercomet.iccore.data.player.ProfileManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerLeaveListener implements Listener {
    private final Intercomet plugin;

    public PlayerLeaveListener(Intercomet plugin) {
        this.plugin = plugin;
    }

    @SuppressWarnings("DataFlowIssue")
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        plugin.getServer().sendPlainMessage(plugin.getConfig().getString("leave-message"));

        Profile profile = plugin.getProfileManager().getProfile(event.getPlayer().getUniqueId());

        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            plugin.saveProfile(profile);
        });
    }
}
>>>>>>> 6c8a590 (mmm)
