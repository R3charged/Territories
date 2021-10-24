package io.github.R3charged.commands.relation;

import io.github.R3charged.Profile;
import io.github.R3charged.commands.TerritoryCommand;
import io.github.R3charged.utility.Chat;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public abstract class RelCommand extends TerritoryCommand {

    public abstract void execute(Player sender, Player friend);

    public void execute(Player sender) {
        String msg = "";
        for (UUID u : Profile.get(sender).getFriends()) {
            msg += Bukkit.getOfflinePlayer(u).getName() + "\n";
        }
        sender.sendMessage(msg);
    }
}
