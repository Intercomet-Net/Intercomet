<<<<<<< HEAD
package net.intercomet.iccore.listeners;

import net.intercomet.iccore.Intercomet;
import net.luckperms.api.model.user.User;
import net.luckperms.api.query.QueryOptions;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static net.intercomet.iccore.util.Perm.checkPerms;

public class ChatCooldown implements Listener {
    private Intercomet plugin;

    public ChatCooldown(Intercomet plugin) {
        this.plugin = plugin;
    }

    public int getCooldown(Player player) {
        User user = plugin.getLuckPerms().getUserManager().getUser(player.getUniqueId());

        if (user == null) return -1;

        var options = plugin.getLuckPerms().getContextManager().getQueryOptions(player);

        if (options.options().isEmpty()) return -1;

        var metaData = user.getCachedData().metaData();

        String cd = "10";

        if (cd == null) return -1;

        try {
            return Integer.parseInt(cd);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if (checkPerms(player, "core.chatcooldown.bypass")) return;

        int cooldown = getCooldown(player);

        if (cooldown == 0) return;

        if (cooldown == -1) plugin.getLogger().info("Could not set cooldown for " + player.getName() + "! Defaulting to 0");

        long now = System.currentTimeMillis();
        long last = plugin.getCooldowns().getOrDefault(player.getUniqueId(), 0L);

        long remaining = (last + (cooldown * 1000L)) - now;

        if (remaining > 0L) {
            event.setCancelled(true);
            player.sendMessage("§7You must wait " + (remaining / 1000) + " seconds before you can chat!");
        }

        plugin.getCooldowns().put(player.getUniqueId(), now);
    }
}
=======
package net.intercomet.iccore.listeners;

import net.intercomet.iccore.Intercomet;
import net.luckperms.api.model.user.User;
import net.luckperms.api.query.QueryOptions;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static net.intercomet.iccore.util.Perm.checkPerms;

public class ChatCooldown implements Listener {
    private Intercomet plugin;

    public ChatCooldown(Intercomet plugin) {
        this.plugin = plugin;
    }

    public int getCooldown(Player player) {
        User user = plugin.getLuckPerms().getUserManager().getUser(player.getUniqueId());

        if (user == null) return -1;

        var options = plugin.getLuckPerms().getContextManager().getQueryOptions(player);

        if (options.options().isEmpty()) return -1;

        var metaData = user.getCachedData().metaData();

        String cd = "10";

        if (cd == null) return -1;

        try {
            return Integer.parseInt(cd);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if (checkPerms(player, "core.chatcooldown.bypass")) return;

        int cooldown = getCooldown(player);

        if (cooldown == 0) return;

        if (cooldown == -1) plugin.getLogger().info("Could not set cooldown for " + player.getName() + "! Defaulting to 0");

        long now = System.currentTimeMillis();
        long last = plugin.getCooldowns().getOrDefault(player.getUniqueId(), 0L);

        long remaining = (last + (cooldown * 1000L)) - now;

        if (remaining > 0L) {
            event.setCancelled(true);
            player.sendMessage("§7You must wait " + (remaining / 1000) + " seconds before you can chat!");
        }

        plugin.getCooldowns().put(player.getUniqueId(), now);
    }
}
>>>>>>> 6c8a590 (mmm)
