package io.github.R3charged.listeners;

import io.github.R3charged.collections.TileMap;
import io.github.R3charged.utility.Chat;
import io.github.R3charged.utility.Loc;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

public class MapListener implements Listener {

    @EventHandler
    public void onChunkLoad(ChunkLoadEvent e) {
        TileMap.deserialize(new Loc(e.getChunk()));
    }
}
