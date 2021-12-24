package io.github.R3charged.contest;

import io.github.R3charged.Territories;
import io.github.R3charged.tile.PlayerTile;
import io.github.R3charged.tile.Tile;
import io.github.R3charged.utility.Loc;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.UUID;

public class Contest {

    private final int CONTEST_DURATION = 300; //in seconds

    private PlayerTile tile;
    private Player owner = Bukkit.getPlayer(tile.getOwner());
    private Player player;
    private double takeRate;

    private int time;
    private int taskID;
    private Runnable runnable = () -> {
        sendActionBar();
        if(isPresent(player)) { //contest deduct
            if(tile.addContestDecay(takeRate)) {
                tile.reset(player.getUniqueId());
                end();
            }
        }
        time--;
        if(time <= 0) {
            end();
        }
    };

    
    public Contest(Player player, PlayerTile tile, double takeRate) {
        time = CONTEST_DURATION;
        this.player = player;
        this.tile = tile;
        this.takeRate = takeRate;
    }

    public void start() {
        tile.setInContest(true);
        BukkitScheduler scheduler = Bukkit.getScheduler();
        taskID = scheduler.scheduleSyncRepeatingTask(Territories.get(), runnable, 0, 20);
    }

    public void end() {
        Bukkit.getScheduler().cancelTask(taskID);
        tile.setInContest(false);
    }

    private void sendActionBar() {
        TextComponent text = new TextComponent("V: " + tile.getValue() + " Time: " + time);
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, text);
        owner.spigot().sendMessage(ChatMessageType.ACTION_BAR, text);
    }

    private boolean isPresent(Player p) {
        return Tile.get(p.getLocation()).equals(this);
    }
}
