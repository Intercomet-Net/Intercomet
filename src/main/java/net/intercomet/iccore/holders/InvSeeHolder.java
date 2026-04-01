<<<<<<< HEAD
package net.intercomet.iccore.holders;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.UUID;

public record InvSeeHolder(UUID targetUUID) implements InventoryHolder {
    @Override
    public Inventory getInventory() {
        return null;
    }
}
=======
package net.intercomet.iccore.holders;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.UUID;

public record InvSeeHolder(UUID targetUUID) implements InventoryHolder {
    @Override
    public Inventory getInventory() {
        return null;
    }
}
>>>>>>> 6c8a590 (mmm)
