package io.github.R3charged.commands;

import io.github.R3charged.enums.Select;
import io.github.R3charged.tile.Tile;
import io.github.R3charged.utility.Chat;
import io.github.R3charged.utility.Loc;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ModifyTileCommand<T extends Tile> extends ComplexTileCommand<Select> {

    protected Select mode = getDefaultMode();

    private final String MODE_PTRN = "^(\\D+)";


    /**
     * Method for executing command. Returns true when command has successfully executed.
     * Returns false if conditions do not pass.
     * @param tile target tile
     * @return true on sucessful execution
     */
    protected abstract boolean exeCmd(T tile);

    @Override
    protected boolean getArguements(String arg) {
        if(arg.length() == 0) {

        }
        else if(arg.matches(MODE_PTRN)) {
            Matcher m = Pattern.compile(MODE_PTRN).matcher(arg);
            m.find();
            try {
                mode = Select.valueOf(m.group(1).toUpperCase());
            } catch (IllegalArgumentException e) {
                sender.sendMessage("That is not a mode.");
                return false;
            }
        }
        return super.getArguements(arg.replaceFirst(MODE_PTRN,""));
    }

    public final void exeCmd(Player sender, Loc loc, Select mode){
        try {
            switch (mode) {
                case ALL:
                    doAll();
                    break;
                case THIS:
                    doThis();
                    break;
                case FILL:
                    doFill(loc.getX(), loc.getZ(), true);
                    break;
            }
        } catch (ClassCastException cce) {
            Chat.error(sender ,"This command cannot be used on this chunk.");
        }
    }

    private void doThis() {
        T t = (T) Tile.get(loc);

        if(t.canModify(sender.getUniqueId())) {
            exeCmd(t);
        }
    }

    private void doAll() {
        /*
        for(Tile tile: TileManager.getTilesOf(sender.getUniqueId())) {
            exeCmd(tile);
        }

         */
        Chat.debug("doAll is unimplemented.");
    }

    private void doFill(int x,int z, boolean origin) {
        T tile = (T) Tile.get(new Loc(x,z,loc.getWorld()));
        if(tile != null && tile.canModify(sender.getUniqueId()) && exeCmd(tile)) { //TODO conditions need to be change
            doFill(x+1,z, false);
            doFill(x-1,z, false);
            doFill(x,z+1, false);
            doFill(x,z-1, false);
        }
        else if (!origin && (tile == null || tile.canModify(sender.getUniqueId()))) {
            doEdge(new Loc(x,z,loc.getWorld()));
        }
    }

    protected Select getDefaultMode() {
        return Select.THIS;
    }

    protected void doEdge(Loc l) {
        //empty
    }

}
