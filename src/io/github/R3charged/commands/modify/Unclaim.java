package io.github.R3charged.commands.modify;

import io.github.R3charged.commands.ModifyTileCommand;
import io.github.R3charged.enums.Status;
import io.github.R3charged.tile.PlayerTile;

public class Unclaim extends ModifyTileCommand<PlayerTile> {
    @Override
    protected boolean exeCmd(PlayerTile tile) {
        if(!tile.isFree()) {
            tile.setStatus(Status.FREE);
            return true;
        }
        return false;
    }


}
