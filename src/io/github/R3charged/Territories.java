package io.github.R3charged;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.github.R3charged.collections.TileMap;
import io.github.R3charged.commands.Inspect;
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
        registerCommands();
        registerListeners();
    }
    @Override
    public void onDisable(){
        //Fired when the server enables the plugin
        TileMap.serialize();
    }

    public void registerCommands(){
        this.getCommand("inspect").setExecutor(new Inspect());
    }
    public void registerListeners(){
        getServer().getPluginManager().registerEvents(new TileListener(), this);
    }
    /* static File dir= this.getDataFolder();
    public static HashMap<String,HashMap<Coords, Tile>> loadTiles(){
        HashMap<String,HashMap<Coords,Tile>> tileMap=new HashMap<String,HashMap<Coords,Tile>>();
        Gson gson=new Gson();
        List<World> worlds= new Territories().getServer().getWorlds();
        if(dir.mkdir())
            System.out.println("Directory Created.");
        for(World w:worlds) {
            String world=w.getName();
            File path = new File(dir+ "\\"+world+".json");
            try {
                if(path.createNewFile()) {//if a new file was created
                    System.out.println(world+".json created.");
                    tileMap.put(world,new HashMap<Coords,Tile>());
                }
                else {
                    tileMap.put(world,gson.fromJson(new FileReader(path), new TypeToken<HashMap<Coords, Tile>>(){}.getType()));
                    System.out.println(world+" tilemap loaded.");
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return tileMap;
    }
    public static void saveTiles(){

    }

    public HashMap<UUID, Profile> loadProfiles(){
        HashMap<UUID,Profile> userMap=new HashMap<UUID,Profile>();
        File path=new File(dir+"\\users.json");
        Gson gson = new Gson();
        try {
            if(path.createNewFile())
                System.out.println("users.json created.");
            else {
                userMap = (gson.fromJson(new FileReader(path), new TypeToken<HashMap<UUID, Profile>>(){}.getType()));
                System.out.println("Loaded User Data");
            }
        }
        catch(Exception e1) {
            e1.printStackTrace();
        }
        return userMap;
    }
    public static void saveProfiles(){

    }*/
}
