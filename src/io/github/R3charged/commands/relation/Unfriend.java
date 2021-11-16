package io.github.R3charged.commands.relation;

import io.github.R3charged.Profile;
import io.github.R3charged.utility.Chat;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Unfriend extends RelCommand{


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

    @Override
    public void execute(Player sender, Player friend) {
        Profile sen = Profile.get(sender);
        Profile fren = Profile.get(friend);

        if (sender.equals(friend)) {
            Chat.error(sender, "You cannot be your own friend.");
        }

        if (sen.hasFriended(friend)) {
            if (fren.hasFriended(sender)) { //Both are friends
                Chat.success(sender, "You have unfriended " + friend.getName());
            } else { //Sender has sent a request friend
                Chat.success(sender, "You have revoked your friend request.");
            }
            sen.removeFriend(friend.getUniqueId());
        } else if (fren.hasFriended(sender)) { //Friend has sent a request to Sender

        } else { //Neither has sent friend request

        }
    }
}
