package io.github.R3charged.tile;

import io.github.R3charged.Profile;
import io.github.R3charged.collections.TileMap;
import io.github.R3charged.utility.Loc;
import net.md_5.bungee.api.ChatColor;

import java.util.UUID;

public abstract class Tile {

    public static Tile get(Loc i) {
        return TileMap.get().get(i);
    }

    transient private Loc location;

    private String title;

    private boolean explosions;

    public Tile(Loc l) {
        location = l;
    }

    public void setLoc(Loc l) {
        if(location == null)
        location = l;
    }

    public abstract ChatColor getColor();

    public Loc getLoc() {
        return location;
    }

    public String getTitle() {
        return title;
    }

    public boolean canModify(UUID u) {
        return Profile.get(u).isOverride();
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
