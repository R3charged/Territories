package io.github.R3charged.commands;

import io.github.R3charged.tile.Tile;
import io.github.R3charged.TileManager;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Scanner;

public abstract class Command implements CommandExecutor {

    protected int x,z;
    protected String world;

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
        getTokens(String.join(" ",args)); //TODO add incorrect params check
        Tile tile = TileManager.getTile(x,z,world);
        exeCmd();
        return false;
    }

    protected void getTokens(String args) { //TODO add exception catch
        Scanner sc = new Scanner(args);
        if(!sc.hasNextInt()) {
            return;
        }
        x = sc.nextInt();
        if(!sc.hasNextInt()) {
            return;
        }
        z = sc.nextInt();
        if(!sc.hasNext()) {
            return;
        }
        world = sc.next();
    }

}
