package io.github.R3charged.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.*;
import io.github.R3charged.commands.modify.Claim;
import io.github.R3charged.commands.modify.Settings;
import io.github.R3charged.commands.modify.Transfer;
import io.github.R3charged.contest.ContestCommand;
import io.github.R3charged.enums.Select;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandsManager {

    private static Argument x = new IntegerArgument("X");
    private static Argument z = new IntegerArgument("Z");
    private static Argument world = worldArgument("World");

    private static Argument select = new CustomArgument<Select>("Select", info -> {
        String s = info.input();
        try {
            return Select.valueOf(s.toUpperCase());

        } catch(Exception e) {

        }
        return null;
    }).replaceSuggestions(sender -> {
        return Arrays.asList(Select.values()).stream().toArray(String[]::new);
    });;


    public static void register() {

        //**************************************************
        //TILE COMMANDS
        //**************************************************
        List<CommandAPICommand> cmds = new ArrayList<CommandAPICommand>();
        cmds.add(new Map("map").withArguments(x,z,world));
        cmds.add(new Map("map"));


        new Inspect("tile").withArguments(x,z,world).register();
        CommandAPICommand master = new Inspect("tile");


        new Map("map").withArguments(x,z,world).register();
        new Map("map").register();

        new ContestCommand("challenge").register();

        //**************************************************
        //MODIFY COMMANDS
        //**************************************************
        new Claim("claim").withArguments(select,x,z,world).register();
        new Claim("claim").withArguments(x,z,world).register();
        new Claim("claim").withArguments(select).register();
        new Claim("claim").register();

        of((ModifyTileCommand) new Transfer("transfer")
                .withArguments(new PlayerArgument("Recipient")));

        //**************************************************
        //CONFIG COMMAND
        //**************************************************
        List<CommandAPICommand> settings = new ArrayList<>();
        settings.addAll(of(new Settings("Explosions")
                .withArguments(new BooleanArgument("Boolean"))));
        settings.addAll(of(new Settings("Color")
                .withArguments(new ChatColorArgument("Color"))));

        CommandAPICommand config = new CommandAPICommand("set");
        config.setSubcommands(settings);
        cmds.add(config);

        //**************************************************
        //TERRITORY MASTER COMMAND
        //**************************************************
        master.setSubcommands(cmds);
        master.register();

        //**************************************************
        //RELATION COMMANDS
        //**************************************************

    }

    private static void registerAllOptions(ModifyTileCommand cmd) {
        for(CommandAPICommand command : of(cmd)) {
            command.register();
        }
    }

    private static List<CommandAPICommand> of(ModifyTileCommand cmd) {
        ArrayList<CommandAPICommand> list = new ArrayList<>();
        try {
            list.add(((ModifyTileCommand) cmd.duplicate()).withArguments(select,x,z,world));
            list.add(((ModifyTileCommand) cmd.duplicate()).withArguments(x,z,world));
            list.add(((ModifyTileCommand) cmd.duplicate()).withArguments(select));
            list.add(cmd);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return list;
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
