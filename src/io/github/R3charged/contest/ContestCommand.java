package io.github.R3charged.contest;

import io.github.R3charged.commands.TileCommand;
import io.github.R3charged.tile.PlayerTile;
import io.github.R3charged.tile.Tile;
import io.github.R3charged.utility.Loc;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;

public class ContestCommand extends TileCommand {

    @Override
    public void execute(Player sender, Chunk chunk) {
        Tile t = Tile.get(chunk);
        if (t instanceof PlayerTile) {
            Contest contest = new Contest(sender, (PlayerTile) t, 1);
            // <-- wait timer
            contest.start();
        } else {

        }
    }

}
