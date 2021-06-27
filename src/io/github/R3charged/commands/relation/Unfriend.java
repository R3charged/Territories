package io.github.R3charged.commands.relation;

import io.github.R3charged.Profile;
import io.github.R3charged.utility.Chat;
import org.bukkit.Bukkit;

public class Unfriend extends RelCommand{

    protected void exeCmd() { //TODO
        String name = Bukkit.getOfflinePlayer(friend).getName();
        if(!Profile.areFriends(sender.getUniqueId(), friend)) {
            Chat.error(sender, "You are not friends with " + name + ".");
            return;
        } else {
            Chat.success(sender, "You have unfriended " + name + ".");
            notifyFriend(friend, sender.getName() + " has unfriended you.");
        }
        Profile.get(sender.getUniqueId()).removeFriend(friend);
        Profile.get(friend).removeFriend(sender.getUniqueId());
    }
}
