package io.github.R3charged;

import io.github.R3charged.utility.Coords;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class Profile {


    transient private boolean override = false;

    private ChatColor mapColor;

    private List<Coords> ownedTiles;
    private HashSet<UUID> friends;

    private List<ItemStack> wagerWinnings;

    public ChatColor getMapColor(){
        return mapColor;
    }

    public boolean isOverride(){
        return override;
    }
}
