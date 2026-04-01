package net.intercomet.iccore.listeners;

import net.intercomet.iccore.Intercomet;
import net.intercomet.iccore.data.player.Profile;
import net.intercomet.iccore.data.player.Settings;
import net.intercomet.iccore.gui.SettingsGUI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class SettingsListener implements Listener {
    private final Intercomet plugin;

    public SettingsListener(Intercomet plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;

        if (!event.getView().getTitle().equals("§8Settings")) return;

        event.setCancelled(true);

        if (event.getCurrentItem() == null) return;

        Profile profile = plugin.getProfileManager().getProfile(event.getWhoClicked().getUniqueId());
        Settings settings = profile.getSettings();

        int slot = event.getSlot();

        switch (slot) {
            case 11 -> {
                settings.setPrivateMessages(!settings.isPrivateMessages());
            }

            case 13 -> {
                settings.setShowOtherPlayers(!settings.isShowOtherPlayers());
            }

            case 15 -> {
                settings.setChatPings(!settings.isChatPings());
            }
        }

        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
           plugin.saveProfile(profile);
        });

        new SettingsGUI(plugin).open(player);
    }
}
