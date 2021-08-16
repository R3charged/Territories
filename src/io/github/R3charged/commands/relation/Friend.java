package io.github.R3charged.commands.relation;

import io.github.R3charged.Profile;
import io.github.R3charged.utility.Chat;
import org.bukkit.Bukkit;

public class Friend extends RelCommand {

    protected boolean exeCmd() {
        String name = Bukkit.getOfflinePlayer(friend).getName();
        if(sender.getUniqueId().equals(friend)) {
            Chat.error(sender, "You cannot be your own friend.");
            return false;
        }
        if(Profile.areFriends(sender.getUniqueId(), friend)) {
            Chat.error(sender, "You are already friends with " + name + ".");
            return false;
        } else if (Profile.get(friend).hasFriended(sender.getUniqueId())){
            Chat.success(sender, "You are now friends with " + name + ".");
            notifyFriend(friend, sender.getName() + " has accepted your friend request.");
        } else {
            Chat.success(sender, "Friend request sent to " + name + ".");
            notifyFriend(friend, sender.getName() + " has sent you a friend request.");
        }
        Profile.get(sender.getUniqueId()).addFriend(friend);
        return true;
    }
}