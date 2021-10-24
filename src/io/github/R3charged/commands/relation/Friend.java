package io.github.R3charged.commands.relation;

import io.github.R3charged.Profile;
import io.github.R3charged.utility.Chat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Friend extends RelCommand {


    protected boolean exeCmd(Player sender, Player friend) {
        String name = friend.getName();
        if(sender.getUniqueId().equals(friend)) {
            Chat.error(sender, "You cannot be your own friend.");
            return false;
        }
        if(Profile.areFriends(sender.getUniqueId(), friend.getUniqueId())) {
            Chat.error(sender, "You are already friends with " + name + ".");
            return false;
        } else if (Profile.get(friend.getUniqueId()).hasFriended(sender.getUniqueId())){
            Chat.success(sender, "You are now friends with " + name + ".");
            //notifyFriend(friend, sender.getName() + " has accepted your friend request.");
        } else {
            Chat.success(sender, "Friend request sent to " + name + ".");
            //notifyFriend(friend, sender.getName() + " has sent you a friend request.");
        }
        Profile.get(sender.getUniqueId()).addFriend(friend.getUniqueId());
        return true;
    }

    @Override
    public void execute(Player sender, Player friend) {
        Profile sen = Profile.get(sender);
        Profile fren = Profile.get(friend);

        if (sender.equals(friend)) {
            Chat.error(sender, "You cannot be your own friend.");
        }

        if (sen.hasFriended(friend)) {
            if (fren.hasFriended(sender)) { //Both are friends

            } else { //Sender has sent a request friend

            }
        } else if (fren.hasFriended(sender)) { //Friend has sent a request to Sender

        } else { //Neither has sent friend request

        }
    }
}