package io.github.R3charged.commands.modify;

import io.github.R3charged.commands.Map;
import io.github.R3charged.commands.ModifyTileCommand;
import io.github.R3charged.tile.PlayerTile;

public abstract class MapWrapper extends ModifyTileCommand<PlayerTile> {

    private boolean mapBool = true;

    public MapWrapper(String commandName) {
        super(commandName);
    }

    @Override
    protected boolean exeCmd() {
        //update time before
        if(super.exeCmd()) {
            showMap();
            return true;
        }
        return false;
    }

    public void setMap(boolean bool) {
        mapBool = bool;
    }

    private void showMap() {
        if(mapBool) {
            //TODO
        }
    }
}
