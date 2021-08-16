package io.github.R3charged.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class TerritoryCommand implements CommandExecutor {

    protected Player sender;

    protected abstract boolean exeCmd();
}
