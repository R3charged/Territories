package io.github.R3charged.listeners;

import io.github.R3charged.tile.Tile;
import io.github.R3charged.utility.Loc;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;

import java.util.UUID;

public class ProtectionListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        UUID u = e.getPlayer().getUniqueId();
        if(Tile.get(new Loc(e.getBlock().getChunk())).canContribute(u)) {
            return;
        }
        e.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        UUID u = e.getPlayer().getUniqueId();
        if(Tile.get(new Loc(e.getBlock().getChunk())).canContribute(u)) {
            return;
        }
        e.setCancelled(true);
    }

    @EventHandler
    public void onInventoryOpenEvent(InventoryOpenEvent e)//storage protection
    {
        if(e.getInventory().getHolder() == null)
            return;
        if(e.getInventory().getType() == InventoryType.PLAYER)
            return;
        if(!Tile.get(new Loc(e.getInventory().getLocation().getChunk())).canContribute(e.getPlayer().getUniqueId()))
        {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.GRAY+"You cannot open this container.");
        }
    }
}
