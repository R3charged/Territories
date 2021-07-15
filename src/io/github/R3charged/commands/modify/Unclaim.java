package io.github.R3charged.commands.modify;

import io.github.R3charged.commands.Map;
import io.github.R3charged.commands.ModifyTileCommand;
import io.github.R3charged.enums.Status;
import io.github.R3charged.tile.PlayerTile;

public class Unclaim extends MapWrapper {
    @Override
    protected boolean exeCmd(PlayerTile tile) {
        if(!tile.isFree()) {
            tile.setStatus(Status.FREE);
            showMap();
            return true;
        }
        return false;
    }


}
