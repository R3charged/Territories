package io.github.R3charged.tile;

import io.github.R3charged.Profile;
import io.github.R3charged.ProfileManager;
import io.github.R3charged.collections.TileMap;
import io.github.R3charged.utility.Coords;
import io.github.R3charged.utility.Loc;
import io.github.R3charged.utility.Status;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Server;

import java.util.HashMap;
import java.util.UUID;

public class PlayerTile extends Tile {

    private UUID owner;//change to more appropriate name
    private long time, contestDecay, estimate, afkDecay; //time in milliseconds spent in chunk. contestDecay is decay caused by contests
    private Status status;

    public static PlayerTile add(Loc i) {
        HashMap<Loc, Tile> map = TileMap.get();
        if(!map.containsKey(i)) {
            map.put(i,new PlayerTile());
        }
        return (PlayerTile) map.get(i);
    }

    public PlayerTile() {
        status = Status.free;
    }
    public PlayerTile(UUID owner, long time) {
        affectTime(owner, time);
    }

    public UUID getOwner() {
        return owner;
    }

    public void setOwner(UUID u) {
        //ProfileManager.getProfile(owner).removeTile(getX(),getZ(),getWorld());
        owner = u;
        //ProfileManager.getProfile(u).addTile(getX(),getZ(),getWorld());
    }

    /**
     * Adds or removes time based on relation to tile owner
     */
    public void affectTime(UUID u) {
        affectTime(u, Profile.get(u).getOnline().updateTimer());
    }

    private void affectTime(UUID u, long ms){ //helper method for actual affectTime
        if(canContribute(u)) {
            time += ms;
        } else if(status.equals(Status.free)) { //extraneous on soft owned
            time -= ms;
        } else if(true) { //contest
            contestDecay += ms;
        }
        if(getValue() < 0 ) { //ownership gets overturned
            time = ms;
            contestDecay = 0;
            afkDecay = 0;
        }
    }


    /**
     * Calculates the net value of the tile.
     * @return Total Value of Tile
     */
    public long getValue() {
        return time + estimate - contestDecay;
    }

    public void evaluate(){

    } //TODO chunk evaluation calculation

    /**
     * Returns true when {@code u} owns the tile,
     * or if they have admin override enabled.
     * @param u who is trying to modify the tile
     * @return true if {@code u} can modify the tile configurations
     */
    public boolean canModify(UUID u) { //when player owns this tile or is admin
        if(u.equals(owner)){
            return true;
        }
        else if(ProfileManager.getProfile(u).isOverride()){
            return true;
        }
        return false;
    }

    /**
     * Returns true when {@code u} is allowed to contribute to the tile.
     * Contributing to the tile is defined as adding value, building, and destroying.
     * @param u who is trying to contribute to the tile.
     * @return true if {@code u} can contribute to the tile.
     */
    public boolean canContribute(UUID u) { //TODO
        return u.equals(owner) || u == null || Profile.areFriends(owner, u);
    }

    public boolean isFree() {
        return true;
    }

    @Override
    public ChatColor getColor() {
        return ProfileManager.getProfile(owner).getMapColor();
    }

    @Override
    public String getTitle(){
        if(super.getTitle() == null) {
            return getOwnerName()+"'s Territory";
        }
        return super.getTitle();
    }

    /**
     * Returns whether the Tile has a set Title.
     * @return true when title not equal to null
     */
    public boolean hasTitle(){
        return super.getTitle() != null;
    }

    public String getOwnerName() {
        return Bukkit.getOfflinePlayer(owner).getName();
    }

}
