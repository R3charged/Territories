package io.github.R3charged.commands;

import dev.jorel.commandapi.arguments.Argument;
import dev.jorel.commandapi.arguments.CustomArgument;
import dev.jorel.commandapi.arguments.StringArgument;
import dev.jorel.commandapi.wrappers.Location2D;
import io.github.R3charged.LocArgument;
import io.github.R3charged.tile.Tile;
import io.github.R3charged.utility.Chat;
import io.github.R3charged.utility.Loc;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class TileCommand extends TerritoryCommand {

    protected Loc loc;

    private Argument locArg = new LocArgument("Chunk");

    private Argument worldArg = new StringArgument("World").replaceSuggestions(sender -> {
        // List of world names on the server
        return Bukkit.getWorlds().stream().map(World::getName).toArray(String[]::new);
    });

    public TileCommand(String commandName) {
        super(commandName);
        addDefault("X", sender.getLocation().getChunk().getX());
        addDefault("Z", sender.getLocation().getChunk().getZ());
        addDefault("World", sender.getLocation().getChunk().getWorld().getName());
    }

    @Override
    protected void castArgs(HashMap<String, Object> map) {
        loc = new Loc((int)map.get("X"), (int)map.get("Z"), (String)map.get("World"));
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
