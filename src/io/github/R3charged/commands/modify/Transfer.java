package io.github.R3charged.commands.modify;

import io.github.R3charged.commands.ModifyTileCommand;
import io.github.R3charged.tile.PlayerTile;
import io.github.R3charged.tile.Tile;
import io.github.R3charged.utility.Chat;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Transfer extends ModifyTileCommand<PlayerTile> {

    private Player recipient;
    @Override
    protected void castArgs(HashMap<String, Object> map) {
        super.castArgs(map);
        recipient = (Player) map.get("Recipient");
    }

    public Transfer(String commandName) {
        super(commandName);
    }




    @Override
    protected boolean exeCmd(PlayerTile tile) { //TODO in Contest check
        tile.setOwner(recipient.getUniqueId());
        Chat.success(sender, "Tile [" + tile.getLoc().getX() + ", " + tile.getLoc().getZ() + "] transferred.");
        return false;
    }

}
