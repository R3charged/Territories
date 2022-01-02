package io.github.R3charged.Tiles;

import org.bukkit.Chunk;

import java.util.HashMap;

public class TileCollection {
    private static HashMap<Chunk, Tile> tileHashMap = new HashMap<>();

    public static HashMap<Chunk, Tile> get() {
        return tileHashMap;
    }
}
