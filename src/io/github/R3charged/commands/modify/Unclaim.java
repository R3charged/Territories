package io.github.R3charged.commands.modify;

import io.github.R3charged.enums.Select;
import io.github.R3charged.enums.Status;
import io.github.R3charged.tile.PlayerTile;
import io.github.R3charged.utility.Loc;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;

import java.util.function.Function;

public class Unclaim extends MapModifier {

    @Override
    protected Function<PlayerTile, Boolean> getExecutor(Player sender, Chunk chunk, Select select) {
        return tile -> {
            if(!tile.isFree()) {
                tile.setStatus(Status.FREE);
                return true;
            }
            return false;
        };
    }
}
