package io.github.R3charged.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.*;
import dev.jorel.commandapi.executors.CommandExecutor;
import io.github.R3charged.commands.modify.Claim;
import io.github.R3charged.commands.modify.MapModifier;
import io.github.R3charged.commands.modify.Settings;
import io.github.R3charged.enums.Select;
import io.github.R3charged.utility.Loc;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandsManager {

    private static Argument x = new IntegerArgument("X");
    private static Argument z = new IntegerArgument("Z");
    private static Argument world = worldArgument("World");

    private static Map map = new Map();
    private static Inspect inspect = new Inspect();

    private static Claim claim = new Claim();

    private static Argument select = new CustomArgument<Select>("Select", info -> {
        String s = info.input();
        try {
            return Select.valueOf(s.toUpperCase());

        } catch(Exception e) {

        }
        return null;
    }).replaceSuggestions(sender -> {
        return new String[] {"This","Fill", "All"};
    });


    public static void register() {
        CommandAPICommand master = create((TerritoryCommand) inspect, "territory");

        ArrayList<CommandAPICommand> subCmds = new ArrayList<>();

        //**************************************************
        //TILE COMMANDS
        //**************************************************
        create(inspect, "territory").register();
        createWithoutWorld(inspect, "territory").register();
        addCmd(subCmds, map, "map");

        //**************************************************
        //MODIFY COMMANDS
        //**************************************************


        //**************************************************
        //TERRITORY MASTER COMMAND
        //**************************************************
        master.setSubcommands(subCmds);
        master.register();

        //**************************************************
        //RELATION COMMANDS
        //**************************************************


        //**************************************************


    }


    private static CommandAPICommand create(String name, CommandExecutor executor, Argument... args) {
        return new CommandAPICommand(name).executes(executor).withArguments(args);
    }

    private static CommandAPICommand create(TerritoryCommand cmd, String name) {
        return new CommandAPICommand(name).executes((sender, args) -> {
            cmd.execute((Player) sender);
        });
    }

    private static CommandAPICommand create(TileCommand cmd, String name) {
        return new CommandAPICommand(name).executes((sender, args) -> {
            cmd.execute((Player) sender, getLoc(args[0], args[1], args[2]));
        }).withArguments(x,z,world);
    }


    private static CommandAPICommand createWithoutWorld(TileCommand cmd, String name) {
        return new CommandAPICommand(name).executes((sender, args) -> {
            cmd.execute((Player) sender, getLoc(args[0], args[1], ((Player) sender).getWorld().getName()));
        }).withArguments(x,z);
    }

    private static void addCmd(List<CommandAPICommand> list, TerritoryCommand cmd, String name) {
        list.add(create(cmd, name));
    }

    private static void addCmd(List<CommandAPICommand> list, TileCommand cmd, String name) {
        list.add(create(cmd, name));
        list.add(createWithoutWorld(cmd, name));
        addCmd(list, (TerritoryCommand) cmd, name);
    }

    public static void addCmd(List<CommandAPICommand> list, ModifyTileCommand cmd, String name) {
        list.add(create(name, (S, A) -> {
            cmd.execute((Player) S, getLoc(A[1], A[2], A[3]), (Select) A[0]);
        }, select, x, z, world));
        list.add(create(name, (S, A) -> {
            cmd.execute((Player) S, getLoc(A[1], A[2], ((Player) S).getWorld().getName()), (Select) A[0]);
        }, select, x, z));
        addCmd(list, (TileCommand) cmd, name);
    }

    public static void addCmd(List<CommandAPICommand> list, Settings cmd, String name) {
        list.add(create(name, (S, A) -> {
            cmd.execute((Player) S, getLoc(A[1], A[2], A[3]), (Select) A[0]);
        }, select, x, z, world));
    }


    private static Chunk getLoc(Object x, Object z, Object world) {
        return Bukkit.getWorld((String) world).getChunkAt((int) x, (int) z);
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

    public static Claim getClaim() {
        return claim;
    }

    public static Map getMap() {
        return map;
    }
}
