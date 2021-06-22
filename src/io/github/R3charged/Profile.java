package io.github.R3charged;

import io.github.R3charged.collections.ProfileMap;
import io.github.R3charged.utility.Coords;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.UUID;

public class Profile {

    private transient boolean override = false;

    private transient OnlineProfile onlineProfile;

    private ChatColor mapColor;

    private LinkedList<Coords> ownedTiles;
    private HashSet<UUID> friends;

    private LinkedList<ItemStack> wagerWinnings;

    public static Profile get(UUID uuid) {
        return ProfileMap.get().get(uuid);
    }

    public static Profile add(UUID uuid) {
        if(!ProfileMap.get().containsKey(uuid)) {
            ProfileMap.get().put(uuid, new Profile());
        }
        return get(uuid);
    }

    public static boolean areFriends(UUID u, UUID v) {
        return get(u).hasFriended(v) && get(v).hasFriended(u);
    }

    public Profile() {
        mapColor = ChatColor.GREEN;
    }

    /**
     * Returns the online profile of a player. If they are not online, this returns null.
     * @return online profile
     */
    public OnlineProfile getOnline() {
        return onlineProfile;
    }

    public void setOnline() {
        onlineProfile = new OnlineProfile();
    }

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
