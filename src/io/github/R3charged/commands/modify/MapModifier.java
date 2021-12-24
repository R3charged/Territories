package io.github.R3charged.commands.modify;

import io.github.R3charged.commands.CommandsManager;
import io.github.R3charged.commands.Map;
import io.github.R3charged.commands.ModifyTileCommand;
import io.github.R3charged.enums.Select;
import io.github.R3charged.tile.PlayerTile;
import io.github.R3charged.utility.Loc;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;

import java.util.function.Function;

public abstract class MapModifier extends ModifyTileCommand<PlayerTile> {

    public void execute(Player sender, Chunk chunk, Select select) {
        executor = getExecutor(sender, chunk, select);
        super.execute(sender, chunk, select);
        CommandsManager.getMap().execute(sender, chunk);
    }

    public void executeWithoutMap(Player sender, Chunk chunk, Select select) {
        executor = getExecutor(sender, chunk, select);
        super.execute(sender, chunk, select);
    }

    protected abstract Function<PlayerTile, Boolean> getExecutor(Player sender, Chunk chunk, Select select);



}
