package io.github.R3charged.commands;

import io.github.R3charged.tile.PlayerTile;
import io.github.R3charged.tile.Tile;
import io.github.R3charged.utility.Loc;

public abstract class ModifyCommand extends Command{

    private enum Mode {
        FILL, THIS, ALL
    }
    private Mode mode;

    protected abstract void exeCmd(Tile tile);

    @Override
    protected boolean getTokens(String[] args) {
        if(args.length==0){ //args is empty
            mode = Mode.THIS;
        }
        else {
            try{ //moves it to tile parsing if element is a number
                Integer.parseInt(args[0]);
                mode = Mode.THIS;
            } catch(NumberFormatException e){ //if not a number
                try{ //if is a proper mode
                    mode = Mode.valueOf(args[0]);
                    args=trimFirst(args);
                } catch(IllegalArgumentException ila){
                    return false;
                }

            }
        }
        super.getTokens(args);
        return true;
    }
    protected String[] trimFirst(String[] arr){
        String[] str = new String[arr.length-1];
        for(int i=1;i<arr.length;i++){
            str[i-1]=arr[i];
        }
        return str;
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
                doFill(loc.getX(),loc.getZ());
                break;
        }
    }

    private void doThis() {
        PlayerTile ptile = (PlayerTile) Tile.get(loc);
        if(ptile.canModify(sender.getUniqueId())) {
            exeCmd(ptile);
        }
    }

    private void doAll() {
        /*
        for(Tile tile: TileManager.getTilesOf(sender.getUniqueId())) {
            exeCmd(tile);
        }

         */
    }

    private void doFill(int x,int z) {
        PlayerTile tile = (PlayerTile) Tile.get(new Loc(x,z,loc.getWorld()));
        if(tile.canModify(sender.getUniqueId())) { //TODO conditions need to be changed
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
