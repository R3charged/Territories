package io.github.R3charged.gui;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

public class GuiListener implements Listener {

    Inventory gui;

    public GuiListener(Inventory inv) {

    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        if (e.getInventory() != gui) {
            return;
        }

    }

    @EventHandler
    public void onInvClose(InventoryCloseEvent e) {
        if (e.getInventory() != gui) {
            return;
        }
    }
}
