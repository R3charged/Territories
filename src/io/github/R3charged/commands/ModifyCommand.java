package io.github.R3charged.commands;

import io.github.R3charged.tile.PlayerTile;
import io.github.R3charged.tile.Tile;
import io.github.R3charged.TileManager;

public abstract class ModifyCommand extends Command{

    private enum Mode {
        FILL, THIS, ALL
    }
    private Mode mode = Mode.THIS;

    protected abstract void exeCmd(Tile tile);

    @Override
    protected void getTokens(String args) {
        super.getTokens(args);
    }

    protected final void exeCmd(){
        switch(mode){
            case ALL:
                doAll();
                break;
            case THIS:
                doThis();
                break;
            case FILL:
                doFill(x,z);
                break;
        }
    }

    private void doThis() {
        PlayerTile tile = (PlayerTile) TileManager.getTile(x, z, world);
        if(tile.canModify(sender.getUniqueId())) {
            exeCmd(tile);
        }
    }

    private void doAll() {
        for(Tile tile: TileManager.getTilesOf(sender.getUniqueId())) {
            exeCmd(tile);
        }
    }

    private void doFill(int x,int z) {
        PlayerTile tile = (PlayerTile) TileManager.getTile(x,z,world);
        if(tile.canModify(sender.getUniqueId())) {
            exeCmd(tile);
            doFill(x+1,z);
            doFill(x-1,z);
            doFill(x,z+1);
            doFill(x,z-1);
        }
        else if(tile.isFree()) {
            doEdge(tile);
        }
    }

    protected void doEdge(Tile tile) {
        //empty
    }
}
