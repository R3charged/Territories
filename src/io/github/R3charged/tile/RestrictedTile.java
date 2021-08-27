package io.github.R3charged.tile;

import io.github.R3charged.utility.Loc;
import org.bukkit.ChatColor;

import java.util.UUID;

public class RestrictedTile extends Tile {

    private ChatColor color;
    private boolean openBuilding;

    public RestrictedTile(Loc l) {
        super(l);
        openBuilding = false;
    }

    @Override
    public ChatColor getColor() {
        return color;
    }

    @Override
    public void setColor(ChatColor color) {
        this.color = color;
    }

    @Override
    public boolean canContribute(UUID u) {
        return openBuilding;
    }
}
