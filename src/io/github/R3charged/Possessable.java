package io.github.R3charged;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public interface Possessable {

    public boolean canContribute(Player player);
    public boolean canModify(Player player);

    public boolean contribute(int time);
}
