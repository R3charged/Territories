package io.github.R3charged;

import io.github.R3charged.Tiles.Tile;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class ContestCollection {
    private static HashMap<Player, Possessable> contestMap = new HashMap<>();

    public boolean inContest(Player player) {
        return contestMap.containsKey(player);
    }
}
