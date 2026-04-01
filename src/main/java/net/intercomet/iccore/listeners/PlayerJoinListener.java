<<<<<<< HEAD
package net.intercomet.iccore.listeners;

import net.intercomet.iccore.Intercomet;
import net.intercomet.iccore.data.player.Profile;
import net.intercomet.iccore.data.player.ProfileManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerJoinListener implements Listener {
    private final Intercomet plugin;

    public PlayerJoinListener(Intercomet plugin) {
        this.plugin = plugin;
    }

    @SuppressWarnings("DataFlowIssue")
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        plugin.getServer().sendPlainMessage(plugin.getConfig().getString("join-message"));

        Profile profile = plugin.getProfileManager().getProfile(event.getPlayer().getUniqueId());

        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            plugin.loadProfile(profile);
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
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerJoinListener implements Listener {
    private final Intercomet plugin;

    public PlayerJoinListener(Intercomet plugin) {
        this.plugin = plugin;
    }

    @SuppressWarnings("DataFlowIssue")
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        plugin.getServer().sendPlainMessage(plugin.getConfig().getString("join-message"));

        Profile profile = plugin.getProfileManager().getProfile(event.getPlayer().getUniqueId());

        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            plugin.loadProfile(profile);
        });
    }
}
>>>>>>> 6c8a590 (mmm)
