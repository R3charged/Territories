package io.github.R3charged.commands.modify;

import io.github.R3charged.commands.Map;
import io.github.R3charged.commands.ModifyTileCommand;
import io.github.R3charged.enums.Select;
import io.github.R3charged.enums.Status;
import io.github.R3charged.tile.PlayerTile;
import io.github.R3charged.tile.Tile;
import io.github.R3charged.utility.Chat;
import io.github.R3charged.utility.Loc;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Claim extends MapWrapper {

    public void execute(Player sender, Loc loc, Select select) {
        executor = tile -> {
            if(tile.canClaim(sender.getUniqueId())) {
                Chat.debug("ss");
                tile.setStatus(Status.CLAIM);
                return true;
            }
            return false;
        };
        super.execute(sender, loc, select);
    }

    @Override
    protected void doEdge(Player sender, Loc l) {
        PlayerTile tile = (PlayerTile) Tile.get(l, sender, 100);
        if(tile.isFree())
            tile.setStatus(Status.PAD);

    }

    @Override
    protected Select defaultOption() {
        return Select.FILL;
    }

}
