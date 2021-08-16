package io.github.R3charged.listeners;

import io.github.R3charged.tile.Tile;
import io.github.R3charged.utility.Loc;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityPlaceEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.UUID;

public class ProtectionListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        UUID u = e.getPlayer().getUniqueId();
        Tile t = Tile.get(new Loc(e.getBlock().getChunk()));
        if(t == null) {
            return;
        }
        if(t.canContribute(u)) {
            return;
        }
        e.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        UUID u = e.getPlayer().getUniqueId();
        Tile t = Tile.get(new Loc(e.getBlock().getChunk()));
        if(t == null) {
            return;
        }
        if(t.canContribute(u)) {
            return;
        }
        e.setCancelled(true);
    }

    @EventHandler
    public void onInventoryOpenEvent(InventoryOpenEvent e)//storage protection
    {
        if(e.getInventory().getHolder() == null)
            return;
        if(! (e.getInventory().getType() == InventoryType.CHEST|| e.getInventory().getType() == InventoryType.DISPENSER)) //TODO add more types
            return;
        Tile t = Tile.get(new Loc(e.getInventory().getLocation().getChunk()));
        if(t == null) {
            return;
        }
        if(!t.canContribute(e.getPlayer().getUniqueId()))
        {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.GRAY+"You cannot open this container.");
        }
    }

    //ENTITY OBJECTS
    //Paintings and Itemframes
    @EventHandler
    public void onHangingEntityBreak(HangingBreakByEntityEvent e) {
        UUID u = e.getRemover().getUniqueId();
        if(canInteract(u, e.getEntity())) {
            return;
        }
        e.setCancelled(true);
    }

    @EventHandler
    public void onHangingEntityPlace(HangingPlaceEvent e) {
        UUID u = e.getPlayer().getUniqueId();
        if(canInteract(u, e.getEntity())) {
            return;
        }
        e.setCancelled(true);
    }

    //General Entities
    @EventHandler
    public void onEntityDeath(EntityDamageByEntityEvent e) {
        if(!(e.getEntity() instanceof ArmorStand)) {
            return;
        }
        UUID u = e.getDamager().getUniqueId();
        if(canInteract(u, e.getEntity())) {
            return;
        }
        e.setCancelled(true);
    }

    @EventHandler
    public void onEntityPlace(EntityPlaceEvent e) {
        if(!(e.getEntity() instanceof ArmorStand)) {
            return;
        }
        if(canInteract(e.getPlayer().getUniqueId(), e.getEntity())) {
            return;
        }
        e.setCancelled(true);
    }

    //Interact with Entity
    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent e) {
        UUID u = e.getPlayer().getUniqueId();
        if(!(e.getRightClicked() instanceof ArmorStand || e.getRightClicked() instanceof ItemFrame)) {
            return;
        }
        if(canInteract(u,e.getRightClicked())) {
            return;
        }
        e.setCancelled(true);
    }

    private boolean canInteract(UUID u, Entity interacted) {
        Tile t = Tile.get(new Loc(interacted.getLocation().getChunk()));
        if (t == null) {
            return true;
        }
        return t.canContribute(u);
    }
}
