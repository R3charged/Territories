package io.github.R3charged.Tiles;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;

import java.io.Serializable;

public abstract class Tile implements Serializable {

    public static Tile get(Chunk chunk) {
        return TileCollection.get(chunk);
    }

    private String title;
    private boolean doExplosions;

    public abstract ChatColor getColor();
    public abstract void setColor(ChatColor color);

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
