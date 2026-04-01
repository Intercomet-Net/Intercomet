package net.intercomet.iccore.commands.complete;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;

public class PunishTabCompleter implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NonNull [] args) {
        List<String> completions = new ArrayList<>();

        String current = args[args.length - 1].toLowerCase();

        if (args.length == 1) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                completions.add(player.getName());
            }
        }

        if (args.length == 2) {
            completions.add("ban");
            completions.add("warn");
            completions.add("kick");
            completions.add("mute");
        }

        return completions.stream().filter(str -> str.toLowerCase().startsWith(current)).toList();
    }
}
