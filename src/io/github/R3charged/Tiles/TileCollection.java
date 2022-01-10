package io.github.R3charged.Tiles;

import io.github.R3charged.Territories;
import io.github.R3charged.TileDataType;
import org.bukkit.Chunk;
import org.bukkit.NamespacedKey;

import java.util.HashMap;

public class TileCollection {

    private static NamespacedKey namespacedKey;
    private static TileDataType tileDataType;

    private static HashMap<Chunk, Tile> tileHashMap;

    public static Tile get(Chunk chunk) {
        Tile tile = tileHashMap.get(chunk);
        if (tile == null) {
            tile = chunk.getPersistentDataContainer().get(namespacedKey, tileDataType);
        }
        if (tile == null) {
            tile = new PlayerTile();
        }
        return tile;
    }

    public static void initialize(Territories plugin) {
        namespacedKey = new NamespacedKey(plugin,"Tile");
        tileDataType = new TileDataType();
        tileHashMap = new HashMap<Chunk, Tile>();
    }


}
