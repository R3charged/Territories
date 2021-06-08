package io.github.R3charged.utility;

import org.bukkit.Chunk;
import org.bukkit.Location;

import java.util.Objects;

public class Loc { //location

    final private String world;
    final private int x,z;

    public Loc(int x, int z, String world) {
        this.x = x;
        this.z = z;
        this.world = world;
    }

    public Loc(Chunk chunk) {
        x = chunk.getX();
        z = chunk.getZ();
        world = chunk.getWorld().getName();
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

    @Override
    public int hashCode() {
        return Objects.hash(x,z,world);
    }

    @Override
    public boolean equals(Object obj) {
        return hashCode() == obj.hashCode();
    }

    public String getPath() {
        return "\\" + world + "\\" + x + "," + z + ".json";
    }
}
