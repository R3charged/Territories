package io.github.R3charged.commands;

import dev.jorel.commandapi.arguments.Argument;
import dev.jorel.commandapi.arguments.CustomArgument;
import io.github.R3charged.enums.Select;
import io.github.R3charged.tile.PlayerTile;
import io.github.R3charged.tile.Tile;
import io.github.R3charged.utility.Chat;
import io.github.R3charged.utility.Loc;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ModifyTileCommand<T extends Tile> extends TileCommand{

    protected Function<T, Boolean> executor;

    public void execute(Player sender, Loc loc, Select select) {
        try {
            switch (select) {
                case ALL:
                    doAll();
                    break;
                case THIS:
                    doThis(sender, loc);
                    break;
                case FILL:
                    doFill(sender, loc, loc.getX(), loc.getZ(), true);
                    break;
            }
        } catch (ClassCastException cce) {
            Chat.error(sender, "This command cannot be used on this chunk.");
        }
    }

    @Override
    public void execute(Player sender, Loc loc) {
        execute(sender, loc, defaultOption());
    }


    private boolean doThis(Player sender, Loc loc) {
        T t = (T) Tile.get(loc);

        if(t.canModify(sender.getUniqueId())) {
            return executor.apply(t);
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

    private boolean doFill(Player sender, Loc loc, int x,int z, boolean origin) {
        T tile;
        try {
            tile = (T) Tile.get(new Loc(x, z, loc.getWorld()));
        } catch (ClassCastException e) {
            return false;
        }

        if(tile != null && tile.canModify(sender.getUniqueId()) && executor.apply(tile)) { //TODO conditions need to be change
            doFill(sender, loc, x+1,z, false);
            doFill(sender, loc, x-1,z, false);
            doFill(sender, loc, x,z+1, false);
            doFill(sender, loc, x,z-1, false);
        }
        else if (!origin && (tile == null || tile.canModify(sender.getUniqueId()))) {
            sender.sendMessage("oopsie");
            doEdge(sender, new Loc(x,z,loc.getWorld()));
        }
        else if(origin) {
            return false;
        }
        return true;
    }

    protected Select defaultOption() {
        return Select.THIS;
    }

    protected void doEdge(Player sender, Loc l) {
        sender.sendMessage("oops");
        //empty
    }


}
