package io.github.R3charged.tile;

import net.md_5.bungee.api.ChatColor;

public class RestrictedTile extends Tile {

    private ChatColor color;

    @Override
    public ChatColor getColor() {
        return color;
    }
}
