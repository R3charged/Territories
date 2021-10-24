package io.github.R3charged.commands.modify;

import io.github.R3charged.commands.Map;
import io.github.R3charged.commands.ModifyTileCommand;
import io.github.R3charged.enums.Select;
import io.github.R3charged.enums.Status;
import io.github.R3charged.tile.PlayerTile;
import io.github.R3charged.utility.Loc;
import org.bukkit.entity.Player;

public class Unclaim extends MapWrapper {

    public void execute(Player sender, Loc loc, Select select) {
        executor = tile -> {
            if(!tile.isFree()) {
                tile.setStatus(Status.FREE);
                return true;
            }
            return false;
        };
    }


}
