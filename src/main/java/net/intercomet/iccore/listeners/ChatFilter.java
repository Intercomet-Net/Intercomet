package net.intercomet.iccore.listeners;

import net.intercomet.iccore.Intercomet;
import net.intercomet.iccore.util.Values;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.*;

public class ChatFilter implements Listener {
    private List<Pattern> blocked = new ArrayList<>();
    private String replacement;

    private final Plugin plugin;

    public ChatFilter(Plugin plugin) {
        this.plugin = plugin;
    }

    public void loadFilter() {
        blocked.clear();

        if (!plugin.getConfig().getBoolean("chat-filter.enabled")) return;

        List<String> patterns = plugin.getConfig().getStringList("chat-filter.blocked");

        for (String pattern : patterns) {
            blocked.add(Pattern.compile(pattern));
        }

        replacement = plugin.getConfig().getString("chat-filter.replacement");
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (blocked.isEmpty()) return;

        String message = event.getMessage();

        for (Pattern pattern : blocked) {
            Matcher matcher = pattern.matcher(message);

            if (matcher.find()) {
                event.setCancelled(true);
            }
        }

        for (UUID uuid : ((Intercomet) plugin).getStaff()) {
            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "tempmute -s " + event.getPlayer().getName() + " 30d " + "Flagged by ChatFilter");
        }
    }
}
