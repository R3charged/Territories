package io.github.R3charged.commands.modify;

import io.github.R3charged.commands.ModifyCommand;
import io.github.R3charged.enums.Select;
import io.github.R3charged.enums.Status;
import io.github.R3charged.tile.PlayerTile;
import io.github.R3charged.tile.Tile;
import io.github.R3charged.utility.Chat;

public class Claim extends ModifyCommand<PlayerTile> {

    @Override
    protected boolean exeCmd(PlayerTile tile) {
        if(tile.canClaim(sender.getUniqueId())) {
            tile.setStatus(Status.CLAIM);
            return true;
        }
        return false;
    }

    @Override
    protected void doEdge(PlayerTile tile) {
        if(tile.isFree())
            tile.setStatus(Status.PAD);

    }

    @Override
    protected Select getDefaultMode() {
        return Select.FILL;
    }

}
