package io.github.R3charged;

import io.github.R3charged.utility.Coords;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

public class Profile {


    transient private boolean override = false;

    private ChatColor mapColor;

    private ArrayList<Coords> ownedTiles;
    private HashSet<UUID> friends;

    private ArrayList<ItemStack> wagerWinnings;

    public ChatColor getMapColor(){
        return mapColor;
    }

    public void addTile(int x, int z, String world){

    }
    public void removeTile(int x, int z, String world){

    }

    public boolean hasFriended(UUID u) {
        return friends.contains(u);
    }

    public void addFriend(UUID u) {
        friends.add(u);
    }

    public boolean isOverride(){
        return override;
    }
}
