package io.github.R3charged.commands.modify;

import io.github.R3charged.enums.Select;
import io.github.R3charged.enums.Status;
import io.github.R3charged.tile.PlayerTile;
import io.github.R3charged.tile.Tile;
import io.github.R3charged.utility.Chat;
import io.github.R3charged.utility.Loc;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;

import java.util.function.Function;

public class Claim extends MapModifier {


    protected Function<PlayerTile, Boolean> getExecutor(Player sender, Chunk chunk, Select select) {
        return tile -> {
            if(tile.canClaim(sender.getUniqueId())) {
                tile.setStatus(Status.CLAIM);
                return true;
            }
            return false;
        };
    }

    @Override
    protected void doEdge(Player sender, Chunk c) {
        PlayerTile tile = (PlayerTile) Tile.get(c, sender, 100);
        if(tile.isFree())
            tile.setStatus(Status.PAD);

    }

    @Override
    protected Select defaultOption() {
        return Select.FILL;
    }

}
