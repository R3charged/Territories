package io.github.R3charged.commands.modify;

import io.github.R3charged.commands.Map;
import io.github.R3charged.commands.ModifyTileCommand;
import io.github.R3charged.enums.Select;
import io.github.R3charged.tile.PlayerTile;
import io.github.R3charged.utility.Loc;
import org.bukkit.entity.Player;

public abstract class MapWrapper extends ModifyTileCommand<PlayerTile> {

    public void executeWithMap(Player sender, Loc loc, Select select) {
        new Map().execute(sender, loc);
        execute(sender, loc, select);
    }

}
