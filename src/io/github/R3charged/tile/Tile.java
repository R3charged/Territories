package io.github.R3charged.tile;

import io.github.R3charged.utility.Coords;
import io.github.R3charged.utility.Status;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public abstract class Tile {

    private String world;
    private int x,z;

    private String title;

    private boolean explosions;

    public Tile(int x, int z, String world){
        this.x = x;
        this.z = z;
        this.world = world;
    }

    public int getX() {
        return x;
    }

    public int getZ() {
        return z;
    }

    public String getWorld() {
        return world;
    }

    public abstract ChatColor getColor();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int hashCode(){
        return Objects.hash(x,z,world);
    }
    @Override
    public boolean equals(Object obj){
        return hashCode()==obj.hashCode();
    }

}
