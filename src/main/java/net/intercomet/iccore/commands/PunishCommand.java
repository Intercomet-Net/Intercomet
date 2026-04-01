<<<<<<< HEAD
package net.intercomet.iccore.commands;

import net.intercomet.iccore.Intercomet;
import net.intercomet.iccore.data.PunishContext;
import net.intercomet.iccore.data.Punishment;
import net.intercomet.iccore.holders.PunishHolder;
import net.intercomet.iccore.util.ItemCreator;
import net.intercomet.iccore.util.Values;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

import static net.intercomet.iccore.util.Perm.checkPerms;
import static net.intercomet.iccore.util.Perm.noPerms;

public class PunishCommand implements CommandExecutor, Listener {
    private final Intercomet plugin;
    private CommandSender sender;

    public PunishCommand(Intercomet plugin) {
        this.plugin = plugin;
    }

    private void openPunishGUI(Player punisher, Player target) {
        Inventory inv = Bukkit.createInventory(new PunishHolder(target.getUniqueId()), 27, Values.getPrefix() + "Punish " + target.getName());

        inv.setItem(11, ItemCreator.createItem(Material.PAPER, Values.getPrefix() + "Warn"));
        inv.setItem(13, ItemCreator.createItem(Material.BARRIER, Values.getPrefix() + "Mute"));
        inv.setItem(14, ItemCreator.createItem(Material.SHIELD, Values.getPrefix() + "Kick"));
        inv.setItem(15, ItemCreator.createItem(Material.MACE, Values.getPrefix() + "Ban"));

        punisher.openInventory(inv);
    }

    @EventHandler
    public void onPunishClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player punisher)) return;

        if (!(event.getInventory().getHolder() instanceof PunishHolder holder)) return;

        event.setCancelled(true);

        Player target = Bukkit.getPlayer(holder.targetUUID());

        ItemStack item = event.getCurrentItem();

        if (item == null || !item.hasItemMeta()) return;

        String name = item.getItemMeta().getDisplayName();

        String action = null;

        if (name.contains("Warn")) action = "warn";
        if (name.contains("Mute")) action = "mute";
        if (name.contains("Kick")) action = "kick";
        if (name.contains("Ban")) action = "ban";

        plugin.getPunishContexts().put(punisher.getUniqueId(), new PunishContext(holder.targetUUID(), action, null, null));

        punisher.closeInventory();
        punisher.sendMessage(Values.getPrefix() + "Please enter a reason: ");

        punisher.closeInventory();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {
        if (!(commandSender instanceof Player player)) return true;

        if (!checkPerms(player, "core.staff.punish")) return noPerms(player);

        if (args.length != 1) {
            player.sendMessage(Values.getPrefix() + "Usage: /punish <player>");

            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            player.sendMessage(Values.getPrefix() + "Player not found.");

            return true;
        }

        openPunishGUI(player, target);

        this.sender = commandSender;

        return true;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player punisher = event.getPlayer();

        if (!plugin.getPunishContexts().containsKey(punisher.getUniqueId())) return;

        event.setCancelled(true);

        PunishContext context = plugin.getPunishContexts().get(punisher.getUniqueId());

        boolean warnorkick = (context.getAction().equalsIgnoreCase("warn") || context.getAction().equalsIgnoreCase("kick"));

        if (context.getReason() == null) {
            context.setReason(event.getMessage());

            if (!warnorkick) {
                punisher.sendMessage(Values.getPrefix() + "Please enter a duration or type 'perm' for permanent: ");
            }
        }

        if (!warnorkick) {
            context.setDuration(event.getMessage());
        }

        OfflinePlayer target = Bukkit.getOfflinePlayer(context.getTargetUUID());

        if (!target.hasPlayedBefore()) {
            punisher.sendMessage(Values.getPrefix() + "Player not found.");

            plugin.getPunishContexts().remove(punisher.getUniqueId());
        }

        String command = getCommand(context, target);

        event.getPlayer().getServer().dispatchCommand(sender, command);

        Punishment punishment = new Punishment(
                punisher.getUniqueId(),
                target.getUniqueId(),
                context.getReason(),
                context.getAction(),
                context.getDuration()
        );

        plugin.getPunishments().add(punishment);

        Bukkit.getScheduler().runTaskAsynchronously(plugin, plugin::savePunishments);
    }

    private @NonNull String getCommand(PunishContext context, OfflinePlayer target) {
        String command = "";

        switch (context.getAction()) {
            case "Warn" -> command = "warn -s " + target.getName() + " " + context.getReason();
            case "Mute" -> command = context.getDuration().equalsIgnoreCase("perm") ? "mute -s " + target.getName() + " " + context.getReason() : "tempmute -s " + target.getName() + " " + context.getDuration() + " " + context.getReason();
            case "Kick" -> command = "kick -s " + target.getName();
            case "Ban" -> command = context.getDuration().equalsIgnoreCase("perm") ? "ban -s " + target.getName() + context.getReason() : "tempban -s " + target.getName() + " " + context.getDuration() + " " + context.getReason();
        }
        return command;
    }
}
=======
package net.intercomet.iccore.commands;

import net.intercomet.iccore.Intercomet;
import net.intercomet.iccore.data.PunishContext;
import net.intercomet.iccore.data.Punishment;
import net.intercomet.iccore.holders.PunishHolder;
import net.intercomet.iccore.util.ItemCreator;
import net.intercomet.iccore.util.Values;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

import static net.intercomet.iccore.util.Perm.checkPerms;
import static net.intercomet.iccore.util.Perm.noPerms;

public class PunishCommand implements CommandExecutor, Listener {
    private final Intercomet plugin;
    private CommandSender sender;

    public PunishCommand(Intercomet plugin) {
        this.plugin = plugin;
    }

    private void openPunishGUI(Player punisher, Player target) {
        Inventory inv = Bukkit.createInventory(new PunishHolder(target.getUniqueId()), 27, Values.getPrefix() + "Punish " + target.getName());

        inv.setItem(11, ItemCreator.createItem(Material.PAPER, Values.getPrefix() + "Warn"));
        inv.setItem(13, ItemCreator.createItem(Material.BARRIER, Values.getPrefix() + "Mute"));
        inv.setItem(14, ItemCreator.createItem(Material.SHIELD, Values.getPrefix() + "Kick"));
        inv.setItem(15, ItemCreator.createItem(Material.MACE, Values.getPrefix() + "Ban"));

        punisher.openInventory(inv);
    }

    @EventHandler
    public void onPunishClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player punisher)) return;

        if (!(event.getInventory().getHolder() instanceof PunishHolder holder)) return;

        event.setCancelled(true);

        Player target = Bukkit.getPlayer(holder.targetUUID());

        ItemStack item = event.getCurrentItem();

        if (item == null || !item.hasItemMeta()) return;

        String name = item.getItemMeta().getDisplayName();

        String action = null;

        if (name.contains("Warn")) action = "warn";
        if (name.contains("Mute")) action = "mute";
        if (name.contains("Kick")) action = "kick";
        if (name.contains("Ban")) action = "ban";

        plugin.getPunishContexts().put(punisher.getUniqueId(), new PunishContext(holder.targetUUID(), action, null, null));

        punisher.closeInventory();
        punisher.sendMessage(Values.getPrefix() + "Please enter a reason: ");

        punisher.closeInventory();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {
        if (!(commandSender instanceof Player player)) return true;

        if (!checkPerms(player, "core.staff.punish")) return noPerms(player);

        if (args.length != 1) {
            player.sendMessage(Values.getPrefix() + "Usage: /punish <player>");

            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            player.sendMessage(Values.getPrefix() + "Player not found.");

            return true;
        }

        openPunishGUI(player, target);

        this.sender = commandSender;

        return true;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player punisher = event.getPlayer();

        if (!plugin.getPunishContexts().containsKey(punisher.getUniqueId())) return;

        event.setCancelled(true);

        PunishContext context = plugin.getPunishContexts().get(punisher.getUniqueId());

        boolean warnorkick = (context.getAction().equalsIgnoreCase("warn") || context.getAction().equalsIgnoreCase("kick"));

        if (context.getReason() == null) {
            context.setReason(event.getMessage());

            if (!warnorkick) {
                punisher.sendMessage(Values.getPrefix() + "Please enter a duration or type 'perm' for permanent: ");
            }
        }

        if (!warnorkick) {
            context.setDuration(event.getMessage());
        }

        OfflinePlayer target = Bukkit.getOfflinePlayer(context.getTargetUUID());

        if (!target.hasPlayedBefore()) {
            punisher.sendMessage(Values.getPrefix() + "Player not found.");

            plugin.getPunishContexts().remove(punisher.getUniqueId());
        }

        String command = getCommand(context, target);

        event.getPlayer().getServer().dispatchCommand(sender, command);

        Punishment punishment = new Punishment(
                punisher.getUniqueId(),
                target.getUniqueId(),
                context.getReason(),
                context.getAction(),
                context.getDuration()
        );

        plugin.getPunishments().add(punishment);

        Bukkit.getScheduler().runTaskAsynchronously(plugin, plugin::savePunishments);
    }

    private @NonNull String getCommand(PunishContext context, OfflinePlayer target) {
        String command = "";

        switch (context.getAction()) {
            case "Warn" -> command = "warn -s " + target.getName() + " " + context.getReason();
            case "Mute" -> command = context.getDuration().equalsIgnoreCase("perm") ? "mute -s " + target.getName() + " " + context.getReason() : "tempmute -s " + target.getName() + " " + context.getDuration() + " " + context.getReason();
            case "Kick" -> command = "kick -s " + target.getName();
            case "Ban" -> command = context.getDuration().equalsIgnoreCase("perm") ? "ban -s " + target.getName() + context.getReason() : "tempban -s " + target.getName() + " " + context.getDuration() + " " + context.getReason();
        }
        return command;
    }
}
>>>>>>> 6c8a590 (mmm)
