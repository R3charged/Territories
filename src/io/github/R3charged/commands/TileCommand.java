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
        withArguments(locArg, worldArg);
    }

    @Override
    protected void castArgs(Object[] args) {
        loc = getLoc(args);
    }

    private Loc getLoc(Object[] args) {
        Location2D location2D = (Location2D) args[of(locArg)];
        Chat.debug("location2D: " + location2D.getX() + " " + location2D.getZ());
        return new Loc(location2D.getChunk().getX(), location2D.getChunk().getZ(), (String)args[of(worldArg)]);
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
