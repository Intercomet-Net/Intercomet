<<<<<<< HEAD
package net.intercomet.iccore.commands;

import net.intercomet.iccore.Intercomet;
import net.intercomet.iccore.data.PunishContext;
import net.intercomet.iccore.holders.PunishHistoryHolder;
import net.intercomet.iccore.util.ItemCreator;
import net.intercomet.iccore.util.Values;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static net.intercomet.iccore.util.Perm.checkPerms;
import static net.intercomet.iccore.util.Perm.noPerms;

public class PunishHistoryCommand implements CommandExecutor {
    private final Intercomet plugin;

    public PunishHistoryCommand(Intercomet plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if (!(commandSender instanceof Player player)) return true;

        if (!checkPerms(player, "core.staff.punish.history")) return noPerms(player);

        openPunishHistoryGUI(player);

        return true;
    }

    private void openPunishHistoryGUI(Player viewer) {
        Inventory inv = Bukkit.createInventory(new PunishHistoryHolder(viewer.getUniqueId()), 27, Values.getPrefix() + "Punish History");

        int page = plugin.getHistoryPages().getOrDefault(viewer.getUniqueId(), 0);

        List<PunishContext> contexts = new ArrayList<>(plugin.getPunishContexts().values());

        int itemsPerPage = 25;
        int start = page * itemsPerPage;
        int end = Math.min(start + itemsPerPage, contexts.size());

        int slot = 0;

        for (int i = 0; i < end; i++) {
            while (slot == 18 || slot == 26) continue;

            if (slot >= 27) break;

            PunishContext context = contexts.get(i);

            inv.setItem(
                    slot,
                    ItemCreator.createItem(Material.PAPER, context.getAction() + " | " + context.getDuration() + " | " + context.getReason())
            );

            slot++;
        }

        if (page > 0) {
            inv.setItem(18, ItemCreator.createItem(Material.ARROW, "§aPrevious Page"));
        }

        if (end < contexts.size()) {
            inv.setItem(26, ItemCreator.createItem(Material.ARROW, "§aNext Page"));
        }

        viewer.openInventory(inv);
    }

    @EventHandler
    public void onPunishHistoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;

        event.setCancelled(true);

        int slot = event.getRawSlot();

        UUID uuid = player.getUniqueId();

        int page = plugin.getHistoryPages().getOrDefault(uuid, 0);

        if (slot == 18) {
            if (page > 0) {
                plugin.getHistoryPages().put(uuid, page - 1);
                openPunishHistoryGUI(player);
            }
        }

        if (slot == 26) {
            plugin.getHistoryPages().put(uuid, page + 1);
            openPunishHistoryGUI(player);
        }
    }
}
=======
package net.intercomet.iccore.commands;

import net.intercomet.iccore.Intercomet;
import net.intercomet.iccore.data.PunishContext;
import net.intercomet.iccore.holders.PunishHistoryHolder;
import net.intercomet.iccore.util.ItemCreator;
import net.intercomet.iccore.util.Values;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static net.intercomet.iccore.util.Perm.checkPerms;
import static net.intercomet.iccore.util.Perm.noPerms;

public class PunishHistoryCommand implements CommandExecutor {
    private final Intercomet plugin;

    public PunishHistoryCommand(Intercomet plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if (!(commandSender instanceof Player player)) return true;

        if (!checkPerms(player, "core.staff.punish.history")) return noPerms(player);

        openPunishHistoryGUI(player);

        return true;
    }

    private void openPunishHistoryGUI(Player viewer) {
        Inventory inv = Bukkit.createInventory(new PunishHistoryHolder(viewer.getUniqueId()), 27, Values.getPrefix() + "Punish History");

        int page = plugin.getHistoryPages().getOrDefault(viewer.getUniqueId(), 0);

        List<PunishContext> contexts = new ArrayList<>(plugin.getPunishContexts().values());

        int itemsPerPage = 25;
        int start = page * itemsPerPage;
        int end = Math.min(start + itemsPerPage, contexts.size());

        int slot = 0;

        for (int i = 0; i < end; i++) {
            while (slot == 18 || slot == 26) continue;

            if (slot >= 27) break;

            PunishContext context = contexts.get(i);

            inv.setItem(
                    slot,
                    ItemCreator.createItem(Material.PAPER, context.getAction() + " | " + context.getDuration() + " | " + context.getReason())
            );

            slot++;
        }

        if (page > 0) {
            inv.setItem(18, ItemCreator.createItem(Material.ARROW, "§aPrevious Page"));
        }

        if (end < contexts.size()) {
            inv.setItem(26, ItemCreator.createItem(Material.ARROW, "§aNext Page"));
        }

        viewer.openInventory(inv);
    }

    @EventHandler
    public void onPunishHistoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;

        event.setCancelled(true);

        int slot = event.getRawSlot();

        UUID uuid = player.getUniqueId();

        int page = plugin.getHistoryPages().getOrDefault(uuid, 0);

        if (slot == 18) {
            if (page > 0) {
                plugin.getHistoryPages().put(uuid, page - 1);
                openPunishHistoryGUI(player);
            }
        }

        if (slot == 26) {
            plugin.getHistoryPages().put(uuid, page + 1);
            openPunishHistoryGUI(player);
        }
    }
}
>>>>>>> 6c8a590 (mmm)
