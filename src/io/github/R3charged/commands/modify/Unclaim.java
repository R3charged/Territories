package io.github.R3charged.commands.modify;

import io.github.R3charged.commands.ModifyCommand;
import io.github.R3charged.enums.Status;
import io.github.R3charged.tile.PlayerTile;
import io.github.R3charged.tile.Tile;

public class Unclaim extends ModifyCommand<PlayerTile> {
    @Override
    protected boolean exeCmd(PlayerTile tile) {
        if(!tile.isFree()) {
            tile.setStatus(Status.FREE);
            return true;
        }
        return false;
    }


}
