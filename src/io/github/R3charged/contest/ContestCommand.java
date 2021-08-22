package io.github.R3charged.contest;

import io.github.R3charged.commands.TileCommand;
import io.github.R3charged.tile.PlayerTile;
import io.github.R3charged.tile.Tile;

public class ContestCommand extends TileCommand {
    public ContestCommand(String commandName) {
        super(commandName);
    }

    @Override
    protected boolean exeCmd() {
        if(Tile.get(loc) instanceof PlayerTile) {
            PlayerTile tile = (PlayerTile) Tile.get(loc);
            new ContestGui(sender, tile);
        }
        return true;
    }
}
