package io.github.R3charged.commands.relation;

import io.github.R3charged.Profile;
import io.github.R3charged.commands.TerritoryCommand;
import io.github.R3charged.utility.Chat;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public abstract class RelCommand extends TerritoryCommand {

    protected UUID friend;

    public RelCommand(String commandName) {
        super(commandName);
    }

    /*
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        try {
            sender = (Player) commandSender;
            friend = Bukkit.getOfflinePlayer(strings[0]).getUniqueId();
            exeCmd();
        } catch (Exception e) {
            Chat.error(commandSender, "That is not a valid player.");
        }
        return true;
    }*/

    protected void notifyFriend(UUID u, String msg) {
        try {
            Chat.success(Bukkit.getPlayer(u), msg);
        } catch(NullPointerException e) {
            //do nothing
        }
    }


}
