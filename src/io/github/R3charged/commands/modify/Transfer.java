package io.github.R3charged.commands.modify;

import io.github.R3charged.commands.ModifyTileCommand;
import io.github.R3charged.enums.Select;
import io.github.R3charged.tile.PlayerTile;
import io.github.R3charged.tile.Tile;
import io.github.R3charged.utility.Chat;
import io.github.R3charged.utility.Loc;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.function.Consumer;

public class Transfer extends ModifyTileCommand<PlayerTile> {

    public void execute(Loc loc, Player sender, Select select, Player recipient) {
        executor = tile -> {
            tile.setOwner(recipient.getUniqueId());
            Chat.success(sender, "Tile [" + tile.getLoc().getX() + ", " + tile.getLoc().getZ() + "] transferred.");
            return true;
        };
        super.execute(sender, loc, select);
    }

    public void execute(Loc loc, Player sender, Select select) { //TODO may be unnecessary
        Chat.error(sender, "You must include a recipient.");
    }


}
