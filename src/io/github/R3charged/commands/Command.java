package io.github.R3charged.commands;

import io.github.R3charged.tile.Tile;
import io.github.R3charged.utility.Loc;
import org.bukkit.Chunk;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class Command implements CommandExecutor{

    protected Loc loc;

    protected Player sender;
    /**
     * Performs command action.
     */
    protected abstract void exeCmd();

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if(!(sender instanceof Player)) { //TODO maybe change later
            sender.sendMessage("Only players can use this command.");
            return true;
        }
        this.sender = (Player) sender;
        if(!getTokens(args)){

            return false;
        }
        exeCmd();
        return true;
    }


    protected boolean getTokens(String[] args) {
        if(args.length==0){ //
            Chunk chunk = sender.getLocation().getChunk();
            loc = new Loc(chunk.getX(),chunk.getZ(),chunk.getWorld().getName());
        }
        try{
            int x = Integer.parseInt(args[0]);
            int z = Integer.parseInt(args[1]);
            String world = args[2]; //TODO cast to world name and catch statement for failure

            loc = new Loc(x,z,world);
        } catch(NumberFormatException|ArrayIndexOutOfBoundsException e){
            return false;
        }
        return true;
    }

}
