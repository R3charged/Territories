package io.github.R3charged.collections;

import io.github.R3charged.tile.Tile;
import io.github.R3charged.utility.Loc;

import java.util.HashMap;

public class TileMap {

    private static HashMap<Loc, Tile> tileMap = new HashMap<Loc, Tile>();

    /**
     * Returns tilemap hash map.
     * @return tile map
     */
    public static HashMap<Loc, Tile> get() {
        return tileMap;
    }

    /**
     * Reads tile data from files.
     */
    public static void deserialize() {

    }

    /**
     * Writes tile data to files.
     */
    public static void serialize() {

    }

}
