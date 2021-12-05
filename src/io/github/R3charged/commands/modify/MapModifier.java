package io.github.R3charged.commands.modify;

import io.github.R3charged.commands.CommandsManager;
import io.github.R3charged.commands.Map;
import io.github.R3charged.commands.ModifyTileCommand;
import io.github.R3charged.enums.Select;
import io.github.R3charged.tile.PlayerTile;
import io.github.R3charged.utility.Loc;
import org.bukkit.entity.Player;

import java.util.function.Function;

public abstract class MapModifier extends ModifyTileCommand<PlayerTile> {

    public void execute(Player sender, Loc loc, Select select) {
        executor = getExecutor(sender, loc, select);
        super.execute(sender, loc, select);
        CommandsManager.getMap().execute(sender, loc);
    }

    public void executeWithoutMap(Player sender, Loc loc, Select select) {
        executor = getExecutor(sender, loc, select);
        super.execute(sender, loc, select);
    }

    protected abstract Function<PlayerTile, Boolean> getExecutor(Player sender, Loc loc, Select select);



}
