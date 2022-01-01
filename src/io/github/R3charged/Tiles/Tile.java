package io.github.R3charged.Tiles;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;

public abstract class Tile {

    private String title;
    private boolean doExplosions;

    public abstract ChatColor getColor();
    public abstract void setColor(ChatColor color);



    public static Tile get(Chunk chunk) {
        Tile tile = TileCollection.get().get(chunk);
        if (tile == null) {

        }

        return tile;
    }
}
