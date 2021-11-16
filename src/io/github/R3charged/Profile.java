package io.github.R3charged;

import io.github.R3charged.collections.ProfileMap;
import io.github.R3charged.utility.Coords;
import io.github.R3charged.utility.Loc;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class Profile {

    public final static ChatColor[] ALL_MAP_COLORS =
            {ChatColor.RED, ChatColor.DARK_RED, ChatColor.GOLD, ChatColor.YELLOW, ChatColor.GREEN, ChatColor.DARK_GREEN,
            ChatColor.AQUA, ChatColor.DARK_AQUA, ChatColor.BLUE, ChatColor.DARK_BLUE, ChatColor.LIGHT_PURPLE,
            ChatColor.DARK_PURPLE};

    private transient boolean override = false;

    private transient OnlineProfile onlineProfile;

    private ChatColor mapColor;
    private Date lastLostContest;

    private ArrayList<Loc> ownedTiles = new ArrayList<>();
    private HashSet<UUID> friends = new HashSet<>();

    private ArrayList<ItemStack> wagerWinnings;

    public static Profile get(UUID uuid) {
        return ProfileMap.get().get(uuid);
    }

    public static Profile get(Player player) {return get(player.getUniqueId());}

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
        lastLostContest = new Date(0);
        mapColor = getRandomColor();
    }

    private ChatColor getRandomColor() {
        int random = (int) ((ALL_MAP_COLORS.length - 1) * Math.random());
        return ALL_MAP_COLORS[random];
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

    public boolean setMapColor(ChatColor c) {
        for(ChatColor sample : ALL_MAP_COLORS) {
            if(c.equals(sample)) {
                mapColor = c;
                return true;
            }
        }
        return false;
    }

    public void addTile(Loc l){
        ownedTiles.add(l);
    }
    public void removeTile(Loc l){
        ownedTiles.remove(l);
    }

    public boolean hasFriended(UUID u) {
        return friends.contains(u);
    }

    public boolean hasFriended(Player p) {
        return hasFriended(p.getUniqueId());
    }

    public HashSet<UUID> getFriends() {
        return friends;
    }

    public void addFriend(UUID u) {
        friends.add(u);
    }

    public void removeFriend(UUID u) {
        friends.remove(u);
    }

    public boolean isOverride(){
        return override;
    }

    public void setOffline() {
        onlineProfile = null;
    }
}
