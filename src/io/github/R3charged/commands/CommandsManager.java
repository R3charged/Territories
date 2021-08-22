package io.github.R3charged.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.Argument;
import dev.jorel.commandapi.arguments.CustomArgument;
import dev.jorel.commandapi.wrappers.Location2D;
import io.github.R3charged.LocArgument;
import io.github.R3charged.utility.Chat;
import org.bukkit.Bukkit;
import org.bukkit.World;

public class CommandsManager {

    private static Argument[] locArgs = {new LocArgument("Chunk"), worldArgument("World")};

    public static void register() {
        new Inspect("tile").register();
        new Map("map").register();
    }

    private static Argument worldArgument(String nodeName) {

        // Construct our CustomArgument that takes in a String input and returns a World object
        return new CustomArgument<String>(nodeName, info -> {
            // Parse the world from our input
            return "sd";
        }).replaceSuggestions(sender -> {
            // List of world names on the server
            return Bukkit.getWorlds().stream().map(World::getName).toArray(String[]::new);
        });
    }
}
