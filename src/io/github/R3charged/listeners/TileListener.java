package io.github.R3charged.listeners;

import io.github.R3charged.Profile;
import io.github.R3charged.commands.CommandsManager;
import io.github.R3charged.enums.Select;
import io.github.R3charged.enums.Status;
import io.github.R3charged.tile.PlayerTile;
import io.github.R3charged.tile.RestrictedTile;
import io.github.R3charged.tile.Tile;
import io.github.R3charged.utility.Chat;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Chunk;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class TileListener implements Listener {

    /**
     * When player moves to a different chunk, time in old chunk is stored.
     * @param e
     */
    @EventHandler
    public void onTileChange(PlayerMoveEvent e) {
        if(e.getFrom().getChunk() == e.getTo().getChunk()) {
            return;
        }
        Tile temp = Tile.get(e.getFrom().getChunk(), e.getPlayer());
        if (temp instanceof PlayerTile) {
            PlayerTile tile = (PlayerTile) temp;
            if(tile.getStatus().equals(Status.PAD)) { // AUTO EXPANSION
                Chunk from = e.getFrom().getChunk();
                CommandsManager.getClaim().executeWithoutMap(e.getPlayer(), e.getFrom().getChunk(), Select.THIS);
            }


            // Chunk Message
            Tile to = Tile.get(e.getTo());
            if (to == null) {
                if (!tile.isFree()) {
                    chunkMessage(e, "Exiting " + tile.getTitle());
                }
            } else if (to instanceof PlayerTile) {
                if (((PlayerTile) to).isFree()) {
                    if (!tile.isFree()) {
                        chunkMessage(e, "Exiting " + tile.getTitle());
                    }
                } else if (tile.isFree() || (!tile.getOwner().equals(((PlayerTile) to).getOwner()))) {
                        chunkMessage(e, "Entering " + to.getTitle());
                }
            }

            Chat.debug("Left " + tile.getValue());
        } else if (temp instanceof RestrictedTile) {

        }

        /*
           Player p= e.getPlayer();
            getTileAdd(e.getFrom()).update(p.getUniqueId());
            chunkMessage(e);
            Chat.debug("From: "+getTileAdd(e.getFrom()).getTime());
            Chat.debug(p.getName()+" left ["+e.getFrom().getChunk().getX()+", "+e.getFrom().getChunk().getZ()+"] for ["+e.getTo().getChunk().getX()+", "+e.getTo().getChunk().getZ()+"]");
       */
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Profile p = Profile.add(e.getPlayer().getUniqueId());
        p.setOnline();

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Profile p = Profile.get(e.getPlayer().getUniqueId());
        p.setOffline();
    }

    private void chunkMessage(PlayerMoveEvent e, String s) {
        e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(s));
    }
}
