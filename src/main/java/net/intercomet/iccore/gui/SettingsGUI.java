package net.intercomet.iccore.gui;

import net.intercomet.iccore.Intercomet;
import net.intercomet.iccore.data.player.Profile;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class SettingsGUI {
    private final Intercomet plugin;

    public SettingsGUI(Intercomet plugin) {
        this.plugin = plugin;
    }

    public void open(Player player) {
        Profile profile = plugin.getProfileManager().getProfile(player.getUniqueId());

        Inventory inv = Bukkit.createInventory(null, 27, "§8Settings");

        inv.setItem(11, createToggleItem(
                blockState(profile.getSettings().isPrivateMessages()),
                "§7Private Messages",
                profile.getSettings().isPrivateMessages()
                ));

        inv.setItem(13, createToggleItem(
                blockState(profile.getSettings().isShowOtherPlayers()),
                "§7Show other players",
                profile.getSettings().isShowOtherPlayers()
        ));

        inv.setItem(15, createToggleItem(
                blockState(profile.getSettings().isChatPings()),
                "§7Chat Pings",
                profile.getSettings().isChatPings()
        ));

        player.openInventory(inv);
    }

    private Material blockState(boolean b) {
        return b ? Material.GREEN_WOOL : Material.RED_WOOL;
    }

    private ItemStack createToggleItem(Material mat, String name, boolean enabled) {
        ItemStack item = new ItemStack(mat);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);
        meta.setLore(List.of(
                "§7Status: " + (enabled ? "§aEnabled" : "§cDisabled"),
                "§7Click to toggle"
        ));

        item.setItemMeta(meta);
        return item;
    }
}
