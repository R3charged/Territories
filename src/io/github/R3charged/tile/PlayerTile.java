package io.github.R3charged.tile;

import io.github.R3charged.ProfileManager;
import io.github.R3charged.utility.Status;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Server;

import java.util.UUID;

public class PlayerTile extends Tile{

    private UUID owner;//change to more appropriate name
    private long time, contestDecay, estimate; //time in milliseconds spent in chunk. contestDecay is decay caused by contests
    private Status status;

    public UUID getOwner() {
        return owner;
    }

    /**
     * Adds or removes time based on relation to tile owner
     */
    public void affectTime(int ms){ //TODO or maybe grab from the hashmap of times

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
     * @return true if {@code u} can modify the tile
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
