package net.intercomet.iccore.holders;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.UUID;

public record PunishHistoryHolder(UUID targetUUID) implements InventoryHolder {
    @Override
    public Inventory getInventory() {
        return null;
    }
}