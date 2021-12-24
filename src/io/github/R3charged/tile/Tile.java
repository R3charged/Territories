package io.github.R3charged.tile;

import io.github.R3charged.PersistentData.TileDataType;
import io.github.R3charged.Profile;
import io.github.R3charged.Territories;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;

import java.io.Serializable;
import java.util.UUID;

public abstract class Tile implements Serializable {

    private static NamespacedKey namespacedKey = new NamespacedKey(Territories.get(),"Tile");
    private static TileDataType tileDataType = new TileDataType();

    public static Tile get(Chunk c) {
        return c.getPersistentDataContainer().get(namespacedKey, tileDataType);
    }

    public static Tile get(Location l) {
        return get(l.getChunk());
    }

    public static Tile get(Chunk c, Player p) {
        Tile tile = Tile.get(c);
        if (tile == null) {
            tile = new PlayerTile(p);
            c.getPersistentDataContainer().set(namespacedKey, tileDataType, tile);
        } else if (tile instanceof PlayerTile) {
            UUID uuid = p.getUniqueId();
            ((PlayerTile) tile).update(uuid);
        }
        return tile;
    }
    public static Tile get(Chunk c, Player p, int time) {
        Tile tile = Tile.get(c);
        if (tile == null) {
            tile = new PlayerTile(p, time);
            c.getPersistentDataContainer().set(namespacedKey, tileDataType, tile);
        } else if (tile instanceof PlayerTile) {
            UUID uuid = p.getUniqueId();
            ((PlayerTile) tile).update(uuid, time);
        }
        return tile;
    }

    private String title;

    private boolean explosions;


    public abstract ChatColor getColor();

    public abstract void setColor(ChatColor color);


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
