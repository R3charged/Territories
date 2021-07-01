package io.github.R3charged.contest;

import io.github.R3charged.tile.PlayerTile;
import org.bukkit.entity.Player;

public class ContestGui {

    public ContestGui(Player player, PlayerTile tile) {
        new Contest(player, tile, 1).start();
    }
}
