package io.github.R3charged;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.github.R3charged.collections.ProfileMap;
import io.github.R3charged.collections.TileMap;
import io.github.R3charged.commands.Inspect;
import io.github.R3charged.commands.Map;
import io.github.R3charged.listeners.TileListener;
import io.github.R3charged.tile.Tile;
import io.github.R3charged.utility.Coords;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Territories extends JavaPlugin {

    private static Territories i;
    {
        i = this;
    }

    public static Territories get() {
        return i;
    }

    @Override
    public void onEnable(){
        this.getDataFolder().mkdir();
        TileMap.makeDirectories();
        ProfileMap.deserialize();
        registerCommands();
        registerListeners();
    }
    @Override
    public void onDisable(){
        //Fired when the server enables the plugin
        TileMap.serialize();
        ProfileMap.serialize();
    }

    public void registerCommands(){
        this.getCommand("inspect").setExecutor(new Inspect());
        this.getCommand("map").setExecutor(new Map());
    }
    public void registerListeners(){
        getServer().getPluginManager().registerEvents(new TileListener(), this);
    }

}
