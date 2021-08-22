package io.github.R3charged.commands.modify;

import io.github.R3charged.commands.Map;
import io.github.R3charged.commands.ModifyTileCommand;
import io.github.R3charged.enums.Select;
import io.github.R3charged.enums.Status;
import io.github.R3charged.tile.PlayerTile;
import io.github.R3charged.utility.Chat;
import io.github.R3charged.utility.Loc;
import org.bukkit.Bukkit;

public class Claim extends MapWrapper {

    public Claim(String commandName) {
        super(commandName);
    }

    @Override
    protected boolean exeCmd(PlayerTile tile) {
        if(tile.canClaim(sender.getUniqueId())) {
            Chat.debug("ss");
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
