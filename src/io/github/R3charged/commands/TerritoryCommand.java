package io.github.R3charged.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.Argument;
import dev.jorel.commandapi.executors.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class TerritoryCommand extends CommandAPICommand{

    protected Player sender;

    public TerritoryCommand(String commandName) {
        super(commandName);
        executes((sender, args) -> {
            if(!(sender instanceof Player)) { //TODO maybe change later
                sender.sendMessage("Only players can use this command.");
                return;
            }
            this.sender = (Player) sender;

            castArgs(args);
            exeCmd();
        });

    }
    /*

    public TerritoryCommand(String cmdName) {
        cmd = new CommandAPICommand(cmdName);
        cmd.executes((sender, args) -> {

            parseArgs(args);
            exeCmd();
        });
    }*/

    protected CommandAPICommand prepend(Argument arg) {
        getArguments().add(0, arg);
        return this;
    }

    protected int of(Argument arg) {
        return getArguments().indexOf(arg);
    }

    protected abstract void castArgs(Object[] args);
    protected abstract boolean exeCmd();
}
