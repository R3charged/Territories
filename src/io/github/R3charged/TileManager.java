package io.github.R3charged;

import io.github.R3charged.tile.PlayerTile;
import io.github.R3charged.tile.Tile;
import io.github.R3charged.utility.Coords;
import io.github.R3charged.utility.Saveable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public final class TileManager implements Saveable {

    private static HashMap<String,HashMap<Coords, Tile>> tileMap;

    /**
     * Returns a tile from loaded data. If tile does not exist in the data, adds and returns new tile.
     * @param x coordinate of the Minecraft Chunk
     * @param z coordinate of the Minecraft Chunk
     * @param world name where the Minecraft Chunk is present.
     * @return Tile
     */
    public static Tile getTile(int x, int z, String world) {
        Coords coords = new Coords(x,z);
        if(!tileMap.get(world).containsKey(coords)) { //when map doesn't contain this tile.
            Tile tile = new PlayerTile(x, z, world);
            tileMap.get(world).put(coords, tile);
            return tile;
        }
        return tileMap.get(world).get(coords);
    }

    public static List<Tile> getTilesOf(UUID uuid) {
        List<Tile> list = new ArrayList<Tile>();
        for(HashMap<Coords,Tile> dim:tileMap.values()) {
            for(Tile tile: dim.values()) {
                if(tile instanceof PlayerTile) {
                    if(((PlayerTile) tile).getOwner().equals(uuid)) {
                        list.add(tile);
                    }
                }
            }
        }
        return list;
    }

    /**
     * Loads tile data from files.
     */
    public static void initialize() {

    }

    /**
     * Writes current tile data to files.
     */
    public static void write() {

    }
}
