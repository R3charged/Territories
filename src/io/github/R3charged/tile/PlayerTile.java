package io.github.R3charged.tile;

import io.github.R3charged.Profile;
import io.github.R3charged.enums.Status;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Date;
import java.util.UUID;

public class PlayerTile extends Tile {

    private final long CLAIM_VALUE = 30000;

    private UUID owner;//change to more appropriate name
    private long time, contestDecay, estimate; //time in milliseconds spent in chunk. contestDecay is decay caused by contests
    private Status status;
    private Date lastVisited;

    private transient boolean inContest = false;

    public PlayerTile(Player p) {
        owner = p.getUniqueId();
        setTitle(getOwnerName()+"'s Territory");
        time = Profile.get(p).getOnline().updateTimer();
        status = Status.FREE;
        lastVisited = new Date();
    }

    public PlayerTile(Player p, int time) {
        owner = p.getUniqueId();
        setTitle(getOwnerName()+"'s Territory");
        this.time = time;
        status = Status.FREE;
        lastVisited = new Date();
    }

    public UUID getOwner() {
        return owner;
    }

    public void setOwner(UUID u) {
        owner = u;
        setTitle(getOwnerName()+"'s Territory");
    }

    /**
     * Adds or removes time based on relation to tile owner
     */
    public void update(UUID u) {
        update(u, Profile.get(u).getOnline().updateTimer());
    }

    void update(UUID u, long ms){ //helper method for actual affectTime
        if(inContest) {
            return;
        }
        if(canContribute(u)) {
            time += ms;
            updateLastVisited();
        } else if(status.equals(Status.FREE)) { //extraneous on soft owned
            time -= ms;
        }
        if(getValue() < 0 ) { //ownership gets overturned
            time = ms;
            contestDecay = 0;
            setOwner(u);
        }
    }

    public void reset(UUID u) {
        time = 10000;
        contestDecay = 0;
        setOwner(u);
    }

    public void setInContest(boolean inContest) {
        this.inContest = inContest;
    }

    public boolean addContestDecay(double take) {
        contestDecay += take * 1000; //sec to ms
        if(getValue() <= 0) {
            return true;
        }
        return false;
    }

    private void updateLastVisited() {
        lastVisited = new Date();
    }

    private long getAfkDecay() {
        //afkDecay = formula TODO
        return 0;
    }


    /**
     * Calculates the net value of the tile.
     * @return Total Value of Tile
     */
    public long getValue() {
        return time + estimate - contestDecay - getAfkDecay();
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
        else if(Profile.get(u).isOverride()){
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
        if(owner == null) {
            setOwner(u);
        }
        if(status.equals(Status.FREE)) {
            return true;
        }
        return u.equals(owner) || Profile.areFriends(owner, u) || Profile.get(u).isOverride();
    }

    public Status getStatus() {
        return status;
    }

    public boolean isFree() {
        return status == Status.FREE;
    }

    public boolean canClaim(UUID u) {
        return u.equals(owner) && getValue() > CLAIM_VALUE && !status.equals(Status.CLAIM);
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public ChatColor getColor() {
        return Profile.get(owner).getMapColor();
    }

    @Override
    public void setColor(ChatColor color) {
        Profile.get(owner).setMapColor(color);
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
