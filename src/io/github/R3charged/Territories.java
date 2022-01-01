package io.github.R3charged;

import io.github.R3charged.collections.ProfileMap;
import io.github.R3charged.commands.CommandsManager;
import io.github.R3charged.listeners.MapListener;
import io.github.R3charged.listeners.ProtectionListener;
import io.github.R3charged.listeners.TileListener;
import io.github.R3charged.utility.Config;
import org.bukkit.plugin.java.JavaPlugin;

public class Territories extends JavaPlugin {

    @Override
    public void onEnable(){
        this.getDataFolder().mkdir();
        Config.initialize();
        ProfileMap.deserialize();
        new CommandsManager().register();
        registerListeners();
    }
    @Override
    public void onDisable(){
        //Fired when the server enables the plugin
        ProfileMap.serialize();
    }

    public void registerListeners(){

    }



}
