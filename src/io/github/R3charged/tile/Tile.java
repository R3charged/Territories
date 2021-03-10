package io.github.R3charged.tile;

import io.github.R3charged.utility.Coords;
import io.github.R3charged.utility.Status;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.UUID;

public abstract class Tile {

    private String title;

    private boolean explosions;

    public abstract ChatColor getColor();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



}
