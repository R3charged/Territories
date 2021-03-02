package io.github.R3charged;

import io.github.R3charged.utility.Coords;
import io.github.R3charged.utility.Status;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.UUID;

public class Tile {
    private static HashMap<String,HashMap<Coords,Tile>> tileMap=new HashMap<String,HashMap<Coords, Tile>>();

    /**
     * Returns a tile from the Tile Map.
     * @param x X Coordinate of Chunk
     * @param z Z Coordinate of Chunk
     * @param world World of Chunk
     * @return tile
     */
    public static Tile getTile(int x,int z,String world){
        return tileMap.get(world).get(new Coords(x,z));
    }
    public static Tile getTile(Location loc){
        return getTile(loc.getChunk().getX(),loc.getChunk().getZ(),loc.getWorld().getName());
    }
//FORGET SERIALIZATION AND STORAGE FOR NOW
    private int x,y; //TODO
    private String world;

    private String title;
    private UUID owner;//change to more appropriate name
    private long time, contestDecay, estimate; //time in milliseconds spent in chunk. contestDecay is decay caused by contests
    private Status status;

    public Tile(){
        status=Status.free;
        time=0;
        contestDecay=0;
        estimate=0;
    }

    /**
     * Adds or removes time based on relation to tile owner
     */
    public void affectTime(int ms){ //TODO or maybe grab from the hashmap of times

    }
    /**
     * Calculates the net value of the tile.
     * @return Total Value of Tile
     */
    public long getValue() {
        return time + estimate - contestDecay;
    }

    public void evaluate(){

    } //TODO chunk evaluation calculation
}
