package io.github.R3charged.commands.modify;

import io.github.R3charged.commands.ModifyTileCommand;
import io.github.R3charged.enums.Select;
import io.github.R3charged.enums.Status;
import io.github.R3charged.tile.PlayerTile;
import io.github.R3charged.utility.Loc;

public class Claim extends ModifyTileCommand<PlayerTile> {

    @Override
    protected boolean exeCmd(PlayerTile tile) {
        if(tile.canClaim(sender.getUniqueId())) {
            tile.setStatus(Status.CLAIM);
            return true;
        }
        return false;
    }

    @Override
    protected void doEdge(Loc l) {
        PlayerTile tile = PlayerTile.add(l);
        if(tile.getOwner() == null)
            tile.setOwner(sender.getUniqueId());
        if(tile.isFree())
            tile.setStatus(Status.PAD);

    }

    @Override
    protected Select defaultOption() {
        return Select.FILL;
    }

}
