<<<<<<< HEAD
package net.intercomet.iccore.listeners;

import net.intercomet.iccore.Intercomet;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEvent;

import java.util.Objects;

public class SpawnListener implements Listener {
    private final Intercomet plugin;

    public SpawnListener(Intercomet plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onSpawn(PlayerEvent event) {
        Player player = event.getPlayer();

        String spawnLocation = Objects.requireNonNull(plugin.getConfig().getString("spawn"));
        String world = Objects.requireNonNull(plugin.getConfig().get("world")).toString();

        Server server = plugin.getServer();

        Location lo = new Location(server.getWorld(world), 1.2f, 1.2f, 1.2f);
    }
}
=======
package net.intercomet.iccore.listeners;

import net.intercomet.iccore.Intercomet;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEvent;

import java.util.Objects;

public class SpawnListener implements Listener {
    private final Intercomet plugin;

    public SpawnListener(Intercomet plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onSpawn(PlayerEvent event) {
        Player player = event.getPlayer();

        String spawnLocation = Objects.requireNonNull(plugin.getConfig().getString("spawn"));
        String world = Objects.requireNonNull(plugin.getConfig().get("world")).toString();

        Server server = plugin.getServer();

        Location lo = new Location(server.getWorld(world), 1.2f, 1.2f, 1.2f);
    }
}
>>>>>>> 6c8a590 (mmm)
