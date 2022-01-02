package io.github.R3charged;

import io.github.R3charged.Tiles.Tile;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TimeRoutine {

    private Runnable task = () -> {
        addTime(Config.TICK_INTERVAL/20);
    };

    public void initialize(Territories plugin) {
        int taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, task, 20, Config.TICK_INTERVAL);

    }

    private void addTime(int time) {
        for (Player p: Bukkit.getOnlinePlayers()) {
            Tile t = Tile.get(p.getLocation().getChunk());
            if (t instanceof Possessable) {
                ((Possessable) t).contribute(time);
            }
        }
    }
}
