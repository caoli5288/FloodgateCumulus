package com.github.caoli5288.floodgate.cumulus.util;

import com.github.caoli5288.floodgate.cumulus.FloodgateCumulus;
import com.google.common.collect.Maps;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class CommandContext {

    private static final Map<String, BiConsumer<Player, List<String>>> COMMAND_MAP = Maps.newHashMap();

    static {
        COMMAND_MAP.put("@form", CommandContext::form);
        COMMAND_MAP.put("@close", CommandContext::close);
    }

    private static void close(Player who, List<String> __) {
        who.closeInventory();
    }

    private static void form(Player who, List<String> list) {
        FloodgateCumulus.open(who, list.get(1));
    }

    public static void execute(Player who, List<String> commands) {
        if (!Utils.isNullOrEmpty(commands)) {
            ConsoleCommandSender console = Bukkit.getConsoleSender();
            for (String command : commands) {
                if (command.charAt(0) == '@') {
                    List<String> list = Arrays.asList(command.split(" "));
                    COMMAND_MAP.get(list.get(0)).accept(who, list);
                } else {
                    String line = PlaceholderAPI.setPlaceholders(who, command);
                    Bukkit.dispatchCommand(console, line);
                }
            }
        }
    }
}
