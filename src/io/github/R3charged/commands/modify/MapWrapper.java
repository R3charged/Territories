package io.github.R3charged.commands.modify;

import io.github.R3charged.commands.Map;
import io.github.R3charged.commands.ModifyTileCommand;
import io.github.R3charged.tile.PlayerTile;

public abstract class MapWrapper extends ModifyTileCommand<PlayerTile> {
    @Override
    protected void exeCmd() {
        //update time before
        super.exeCmd();

    }

    protected void showMap() {
        new Map().onCommand(sender, ""); //TODO
    }
}
