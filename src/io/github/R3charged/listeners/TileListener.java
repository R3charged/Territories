package io.github.R3charged.listeners;

import io.github.R3charged.Profile;
import io.github.R3charged.collections.TileMap;
import io.github.R3charged.commands.modify.Claim;
import io.github.R3charged.enums.Status;
import io.github.R3charged.tile.PlayerTile;
import io.github.R3charged.tile.Tile;
import io.github.R3charged.utility.Chat;
import io.github.R3charged.utility.Loc;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class TileListener implements Listener {

    /**
     * When player moves to a different chunk, time in old chunk is stored.
     * @param e
     */
    @EventHandler
    public void onTileChange(PlayerMoveEvent e)
    {
        if(e.getFrom().getChunk() != e.getTo().getChunk())
        {
            PlayerTile ptile = PlayerTile.add(new Loc(e.getFrom().getChunk()));
            ptile.affectTime(e.getPlayer().getUniqueId());
            if(ptile.getStatus().equals(Status.PAD)) {
                Chunk from = e.getFrom().getChunk();
                //claim.onCommand(e.getPlayer(), from.getX() + " " + from.getZ(), from.getWorld().getName());
            }
            Chat.debug("Left " + ptile.getValue());
            TileMap.serialize();
            /*
            Player p= e.getPlayer();
            getTileAdd(e.getFrom()).update(p.getUniqueId());
            chunkMessage(e);
            Chat.debug("From: "+getTileAdd(e.getFrom()).getTime());
            Chat.debug(p.getName()+" left ["+e.getFrom().getChunk().getX()+", "+e.getFrom().getChunk().getZ()+"] for ["+e.getTo().getChunk().getX()+", "+e.getTo().getChunk().getZ()+"]");
       */
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Profile p = Profile.add(e.getPlayer().getUniqueId());
        p.setOnline();

    }
}
