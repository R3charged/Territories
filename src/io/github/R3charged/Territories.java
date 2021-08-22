package io.github.R3charged;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.Argument;
import dev.jorel.commandapi.arguments.CustomArgument;
import dev.jorel.commandapi.arguments.StringArgument;
import dev.jorel.commandapi.wrappers.Location2D;
import io.github.R3charged.collections.ProfileMap;
import io.github.R3charged.collections.TileMap;
import io.github.R3charged.commands.CommandsManager;
import io.github.R3charged.commands.Inspect;
import io.github.R3charged.commands.Map;
import io.github.R3charged.commands.modify.Claim;
import io.github.R3charged.commands.modify.Unclaim;
import io.github.R3charged.commands.relation.Friend;
import io.github.R3charged.commands.relation.Unfriend;
import io.github.R3charged.contest.ContestCommand;
import io.github.R3charged.listeners.MapListener;
import io.github.R3charged.listeners.ProtectionListener;
import io.github.R3charged.listeners.TileListener;
import io.github.R3charged.tile.Tile;
import io.github.R3charged.utility.Chat;
import io.github.R3charged.utility.Config;
import io.github.R3charged.utility.Coords;
import io.github.R3charged.utility.Loc;
import org.bukkit.Bukkit;
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
        Config.initialize();
        TileMap.makeDirectories();
        TileMap.deserialize();
        ProfileMap.deserialize();
        CommandsManager.register();
        registerListeners();
    }
    @Override
    public void onDisable(){
        //Fired when the server enables the plugin
        TileMap.serialize();
        ProfileMap.serialize();
    }

    public void registerListeners(){
        getServer().getPluginManager().registerEvents(new TileListener(), this);
        getServer().getPluginManager().registerEvents(new MapListener(), this);
        getServer().getPluginManager().registerEvents(new ProtectionListener(), this);
    }



}
