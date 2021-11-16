package io.github.R3charged.tile;

import io.github.R3charged.Profile;
import io.github.R3charged.collections.TileMap;
import io.github.R3charged.utility.Chat;
import io.github.R3charged.utility.Loc;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public abstract class Tile {

    public static Tile get(Loc i) {
        return TileMap.get().get(i);
    }

    public static Tile get(Location l) {
        return TileMap.get().get(new Loc(l.getChunk()));
    }

    public static Tile get(Location l, Player p) {
        return get(new Loc(l.getChunk()), p);
    }
    public static Tile get(Loc l, Player p) {
        Tile tile = Tile.get(l);
        if (tile == null) {
            tile = new PlayerTile(l, p);
            TileMap.get().put(l, tile);
        } else if (tile instanceof PlayerTile) {
            UUID uuid = p.getUniqueId();
            ((PlayerTile) tile).update(uuid);
        }
        return tile;
    }
    public static Tile get(Loc l, Player p, int time) {
        Tile tile = Tile.get(l);
        if (tile == null) {
            tile = new PlayerTile(l, p, time);
            TileMap.get().put(l, tile);
        } else if (tile instanceof PlayerTile) {
            UUID uuid = p.getUniqueId();
            ((PlayerTile) tile).update(uuid, time);
        }
        return tile;
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

    public abstract void setColor(ChatColor color);

    public Loc getLoc() {
        return location;
    }

    public String getTitle() {
        return title;
    }

    public boolean canModify(UUID u) {
        return Profile.get(u).isOverride();
    }

    public abstract boolean canContribute(UUID u);

    public void setTitle(String title) {
        this.title = title;
    }

}
