package io.github.R3charged.commands;

import io.github.R3charged.tile.Tile;
import io.github.R3charged.utility.Chat;
import io.github.R3charged.utility.Loc;
import org.bukkit.Chunk;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class TileCommand extends TerritoryCommand {

    protected Loc loc;

    private static final Pattern LOC_PTRN = Pattern.compile("(-?\\d+)\\s(-?\\d+)\\s(\\D+)");

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if(!(sender instanceof Player)) { //TODO maybe change later
            sender.sendMessage("Only players can use this command.");
            return true;
        }
        this.sender = (Player) sender;
        if(!getArguements(String.join(" ", args))) {
            Chat.debug("Command tokens error");
            return false;
        }
        exeCmd();
        return true;
    }


    protected boolean getArguements(String arg) {

        if(arg.length()==0){ //
            Chunk chunk = sender.getLocation().getChunk();
            loc = new Loc(chunk.getX(),chunk.getZ(),chunk.getWorld().getName());
            return true;
        }
        Matcher m = LOC_PTRN.matcher(arg);
        if(m.matches()) {
            loc = new Loc(Integer.valueOf(m.group(1)), Integer.valueOf(m.group(2)), m.group(3));
            return true;
        }
        return false;
    }

}
