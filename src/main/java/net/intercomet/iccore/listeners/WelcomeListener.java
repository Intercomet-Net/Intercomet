package net.intercomet.iccore.listeners;

import net.intercomet.iccore.util.Values;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class WelcomeListener implements Listener {
    private final JavaPlugin plugin;

    public WelcomeListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if (!event.getPlayer().hasPlayedBefore()) return;

        event.setCancelled(true);

        if (event.getMessage().contains("welcome")) {
            event.getPlayer().sendMessage(Values.getWelcomeMessage() + "§a" + event.getPlayer().getName());

            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_AMETHYST_BLOCK_PLACE, 1f, 1f);
        }
    }
}
