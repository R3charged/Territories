package io.github.R3charged.commands.relation;

import io.github.R3charged.Profile;
import io.github.R3charged.utility.Chat;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Unfriend {


    protected boolean exeCmd(Player sender, Player friend) { //TODO
        String name = friend.getName();
        if(!Profile.areFriends(sender.getUniqueId(), friend.getUniqueId())) {
            Chat.error(sender, "You are not friends with " + name + ".");
            return false;
        } else {
            Chat.success(sender, "You have unfriended " + name + ".");
            friend.sendMessage(sender.getName() + " has unfriended you."); //TODO
        }
        Profile.get(sender.getUniqueId()).removeFriend(friend.getUniqueId());
        Profile.get(friend.getUniqueId()).removeFriend(sender.getUniqueId());
        return true;
    }
}
