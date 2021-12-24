package io.github.R3charged.contest;

import io.github.R3charged.Territories;
import io.github.R3charged.tile.PlayerTile;
import io.github.R3charged.utility.Config;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Objects;

public class ContestGui implements Listener {

    //INVENTORY GUI
    private Player player;
    private PlayerTile tile;
    private ItemStack[] contents;

    private Inventory gui;
    private ItemStack startButton;
    private ItemStack confirmButton;
    private boolean playerCloses = true;

    public ContestGui(Player player, PlayerTile tile) {
        this.player = player;
        this.tile = tile;
        gui = Bukkit.createInventory(null,9,"Wager to increase take-rate");

        startButton = new ItemStack(Material.RED_STAINED_GLASS_PANE,1);

        ItemMeta startMeta = startButton.getItemMeta();
        startMeta.setDisplayName("§lStart War");
        startMeta.setLore(Arrays.asList("Click to start war.","Close inventory to cancel."));
        startButton.setItemMeta(startMeta);

        confirmButton = new ItemStack(Material.LIME_STAINED_GLASS_PANE,1);
        ItemMeta confirmMeta=confirmButton.getItemMeta();
        confirmMeta.setDisplayName("§l§bConfirm");
        confirmMeta.setLore(Arrays.asList("Click to confirm.","Close inventory to cancel."));
        confirmButton.setItemMeta(confirmMeta);

        gui.setItem(8, startButton);

    }

    public void openGUI(Player player)
    {
        player.openInventory(gui);
        Bukkit.getServer().getPluginManager().registerEvents(this, Territories.get());
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e)
    {
        if (e.getInventory() != gui) {
            return;
        }
        if (e.getCurrentItem() == null) {
            return;
        }
        e.setCancelled(true);

        if(e.getCurrentItem().equals(startButton))
        {
            gui.setItem(8, confirmButton);
            contents = gui.getStorageContents();
            System.out.println(contents.length);
            updateTitle(contents,e.getCursor(),"Take-Over Rate: "+calcTakeRate(contents)+"/s");

            return;
        }
        if(e.getCurrentItem().equals(confirmButton)) //Confirm Button
        {
            double rate = saveGUI();
            playerCloses=false;
            player.closeInventory();
            HandlerList.unregisterAll(this);
            new Contest(player, tile, rate).start();
            return;
        }
        gui.setItem(8, startButton);


        ItemStack clicked=e.getCurrentItem();
        int num=0;
        switch(e.getClick()){
            case SHIFT_LEFT:
            case LEFT:
                num=clicked.getAmount();
                break;
            case SHIFT_RIGHT:
            case MIDDLE:
                num=e.getCurrentItem().getAmount()/2;
                break;
            case RIGHT:
                num=1;
                break;
            default:
                return;
        }
        if(e.getRawSlot()>8) { //from inventory to gui
            for(ItemStack i:gui.addItem(splitItem(clicked,num)).values())
                e.setCurrentItem(merge(clicked,i));
        }
        else {// gui to inventory
            for(ItemStack i:e.getWhoClicked().getInventory().addItem(splitItem(clicked,num)).values())
                e.setCurrentItem(merge(clicked,i));
        }
        updateTitle(gui.getContents(),null,"Take-Over Rate: "+calcTakeRate(gui.getContents())+"/s");
    }


    private ItemStack splitItem(ItemStack item, int i) //i is amount in output ItemStack
    {
        ItemStack split = item.clone();
        item.setAmount(item.getAmount()-i);
        split.setAmount(i);
        return split;
    }
    private ItemStack merge(ItemStack a,ItemStack b)
    {
        a.setAmount(a.getAmount()+b.getAmount());
        return a;
    }

    private double calcTakeRate(ItemStack[] arr)
    {
        double takeRate=1;
        double multiplier = 1;
        for(int i=0;i<arr.length;i++) {
            if (arr[i] != null) {
                switch(arr[i].getType())
                {
                    case DIAMOND:
                        multiplier+=arr[i].getAmount() * Config.DIAMOND_VALUE;
                        break;
                    case DIAMOND_BLOCK:
                        multiplier+=arr[i].getAmount() * Config.DIAMOND_VALUE * 9;
                        break;
                    case NETHERITE_INGOT:
                        multiplier+=arr[i].getAmount() * Config.NETHERITE_VALUE;
                        break;
                    case NETHERITE_BLOCK:
                        multiplier+=arr[i].getAmount() * Config.NETHERITE_VALUE * 9;
                        break;
                    case NETHERITE_SCRAP:
                        multiplier+=arr[i].getAmount() * Config.NETHERITE_VALUE / 5;
                        break;
                    case ANCIENT_DEBRIS:
                        multiplier+=arr[i].getAmount() * Config.NETHERITE_VALUE / 4;
                        break;
                    default:
                        break;
                }
            }
        }
        takeRate*=multiplier;
        return takeRate;
    }

    @EventHandler
    public void onInvClose(InventoryCloseEvent e)//return items
    {
        if (e.getInventory() != gui || !playerCloses)
            return;
        returnItems();

    }

    public void returnItems()
    {
        saveGUI();
        for(ItemStack excess : player.getInventory().addItem(gui.getStorageContents()).values())
        {
            player.getWorld().dropItem(player.getLocation(), excess);
        }
        HandlerList.unregisterAll(this);
    }

    public double saveGUI()
    {
        double takeRate = calcTakeRate(gui.getContents());
        gui.remove(startButton);
        gui.remove(confirmButton);
        contents=gui.getStorageContents();
        return takeRate;
    }

    private void updateTitle(ItemStack[] postEvent,ItemStack cursor,String title)
    {
        playerCloses=false;

        player.getOpenInventory().setCursor(null);
        Inventory temp=Bukkit.createInventory(null,9,title);
        temp.setStorageContents(postEvent);
        player.openInventory(temp);
        player.getOpenInventory().setCursor(cursor);
        gui=temp;
        playerCloses=true;
    }
}
