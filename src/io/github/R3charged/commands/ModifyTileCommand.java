package io.github.R3charged.commands;

import dev.jorel.commandapi.arguments.CustomArgument;
import io.github.R3charged.enums.Select;
import io.github.R3charged.tile.Tile;
import io.github.R3charged.utility.Chat;
import io.github.R3charged.utility.Loc;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ModifyTileCommand<T extends Tile> extends TileCommand{

    private Select select;

    CustomArgument<Select> selectArg = new CustomArgument<Select>("Select", info -> {
        String s = info.input();
        Select select = defaultOption();
        try {
            select = Select.valueOf(s.toUpperCase());
        } catch(Exception e) {
            Chat.error(sender, "Unknown select option.");
        }
        return select;
    });
    {
        selectArg.replaceSuggestions(sender -> {
            return Arrays.asList(Select.values()).stream().toArray(String[]::new);
        });
    }

    public ModifyTileCommand(String commandName) {
        super(commandName);
        prepend(selectArg);

    }

    @Override
    protected void castArgs(Object[] args) {
        super.castArgs(args);
        select = (Select) args[of(selectArg)];
    }

    /**
     * Method for executing command. Returns true when command has successfully executed.
     * Returns false if conditions do not pass.
     * @param tile target tile
     * @return true on sucessful execution
     */
    protected abstract boolean exeCmd(T tile);

    protected boolean exeCmd(){
        try {
            switch (select) {
                case ALL:
                    return doAll();
                case THIS:
                    return doThis();
                case FILL:
                    return doFill(loc.getX(), loc.getZ(), true);
            }
        } catch (ClassCastException cce) {
            Chat.error(sender ,"This command cannot be used on this chunk.");
            return false;
        }
        return true;
    }

    private boolean doThis() {
        T t = (T) Tile.get(loc);

        if(t.canModify(sender.getUniqueId())) {
            return exeCmd(t);
        }
        return false;
    }

    private boolean doAll() {
        /*
        for(Tile tile: TileManager.getTilesOf(sender.getUniqueId())) {
            exeCmd(tile);
        }

         */
        Chat.debug("doAll is unimplemented.");
        return false;
    }

    private boolean doFill(int x,int z, boolean origin) {
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
        else if(origin) {
            return false;
        }
        return true;
    }

    protected Select defaultOption() {
        return Select.THIS;
    }

    protected void doEdge(Loc l) {
        //empty
    }

}
