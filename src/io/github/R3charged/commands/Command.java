package io.github.R3charged.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public abstract class Command implements CommandExecutor {

    protected int x,z;
    protected String world;

    /**
     * Performs command action.
     */
    protected abstract void exeCmd();

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        exeCmd();
        return false;
    }


}
