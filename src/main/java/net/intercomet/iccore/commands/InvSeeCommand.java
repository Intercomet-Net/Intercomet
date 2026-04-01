<<<<<<< HEAD
package net.intercomet.iccore.commands;

import net.intercomet.iccore.Intercomet;
import net.intercomet.iccore.holders.InvSeeHolder;
import net.intercomet.iccore.util.Values;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

import static net.intercomet.iccore.util.Perm.checkPerms;
import static net.intercomet.iccore.util.Perm.noPerms;

public class InvSeeCommand implements CommandExecutor {
    private final Intercomet plugin;

    public InvSeeCommand(Intercomet plugin) {
        this.plugin = plugin;
    }

    private void openInvSeeInventory(Player viewer, Player target) {
        Inventory inv = Bukkit.createInventory(target, 36, Values.getPrefix() + target.getName() + "'s Inventory");

        ItemStack[] contents = target.getInventory().getContents();

        for (int i = 0; i < contents.length; i++) {
            inv.setItem(i, contents[i]);
        }

        plugin.getInvseeSessions().put(viewer.getUniqueId(), target.getUniqueId());
        viewer.openInventory(inv);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {
        if (!(commandSender instanceof Player player)) return true;

        if (!checkPerms(player, "core.staff.invsee")) return noPerms(player);

        if (args.length != 1) {
            player.sendMessage(Values.getPrefix() + "Usage: /invsee <player>");

            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            player.sendMessage(Values.getPrefix() + "Player not found.");

            return true;
        }

        openInvSeeInventory(player, target);

        return true;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;

        InventoryHolder holder = event.getWhoClicked().getInventory().getHolder();

        if (!(holder instanceof InvSeeHolder)) return;

        UUID targetUUID = ((InvSeeHolder) holder).targetUUID();

        Player target = Bukkit.getPlayer(targetUUID);

        Bukkit.getScheduler().runTask(plugin, () -> {
            ItemStack[] newContents = event.getInventory().getContents();

            for (int i = 0; i < target.getInventory().getContents().length && i < 54; i++) {
                target.getInventory().setItem(i, newContents[i]);
            }
        });
    }
}
=======
package net.intercomet.iccore.commands;

import net.intercomet.iccore.Intercomet;
import net.intercomet.iccore.holders.InvSeeHolder;
import net.intercomet.iccore.util.Values;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

import static net.intercomet.iccore.util.Perm.checkPerms;
import static net.intercomet.iccore.util.Perm.noPerms;

public class InvSeeCommand implements CommandExecutor {
    private final Intercomet plugin;

    public InvSeeCommand(Intercomet plugin) {
        this.plugin = plugin;
    }

    private void openInvSeeInventory(Player viewer, Player target) {
        Inventory inv = Bukkit.createInventory(target, 36, Values.getPrefix() + target.getName() + "'s Inventory");

        ItemStack[] contents = target.getInventory().getContents();

        for (int i = 0; i < contents.length; i++) {
            inv.setItem(i, contents[i]);
        }

        plugin.getInvseeSessions().put(viewer.getUniqueId(), target.getUniqueId());
        viewer.openInventory(inv);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {
        if (!(commandSender instanceof Player player)) return true;

        if (!checkPerms(player, "core.staff.invsee")) return noPerms(player);

        if (args.length != 1) {
            player.sendMessage(Values.getPrefix() + "Usage: /invsee <player>");

            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            player.sendMessage(Values.getPrefix() + "Player not found.");

            return true;
        }

        openInvSeeInventory(player, target);

        return true;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;

        InventoryHolder holder = event.getWhoClicked().getInventory().getHolder();

        if (!(holder instanceof InvSeeHolder)) return;

        UUID targetUUID = ((InvSeeHolder) holder).targetUUID();

        Player target = Bukkit.getPlayer(targetUUID);

        Bukkit.getScheduler().runTask(plugin, () -> {
            ItemStack[] newContents = event.getInventory().getContents();

            for (int i = 0; i < target.getInventory().getContents().length && i < 54; i++) {
                target.getInventory().setItem(i, newContents[i]);
            }
        });
    }
}
>>>>>>> 6c8a590 (mmm)
