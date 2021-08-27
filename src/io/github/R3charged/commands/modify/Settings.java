package io.github.R3charged.commands.modify;

import io.github.R3charged.commands.ModifyTileCommand;
import io.github.R3charged.tile.Tile;
import org.bukkit.ChatColor;

import java.util.HashMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class Settings extends ModifyTileCommand {

    //(tile, obj) -> tile.setSomething(obj);
    private static HashMap<String, BiConsumer<Tile, Object>> settings = new HashMap<>();
    {
        settings.put("Explosions", (T, O) -> {sender.sendMessage("boop!"); });
        settings.put("Color", (T, O) -> { T.setColor((ChatColor) O); });
    }

    public Settings(String commandName) {
        super(commandName);
    }

    @Override
    protected boolean exeCmd(Tile tile) {
        settings.get(getName()).accept(tile, getArguments().get(0));
        return false;
    }


    //Explosions: True False
    //Color: Red, Blue, Dark Red, etc
    //Title: String



}
