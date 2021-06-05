package io.github.R3charged.commands;

import io.github.R3charged.tile.PlayerTile;
import io.github.R3charged.tile.Tile;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class Inspect extends Command{

    private final String line = "----------------------------";

    @Override
    protected void exeCmd() {

        Tile tile = Tile.get(loc);
        if(tile instanceof PlayerTile){
            PlayerTile ptile = (PlayerTile) tile;
            sender.sendMessage(loc.getX() + " " + loc.getZ() + " " + loc.getWorld() + "\n" +
                    "V: " + ptile.getValue());
            //sender.spigot().sendMessage(message(ptile));
        }



    }

    /*
    private TextComponent message(PlayerTile ptile){
        TextComponent msg = header(ptile);
        msg.addExtra("\n"+line);
        msg.addExtra("\nV: "+ptile.getValue());
        msg.addExtra("\n"+line);

        if(ptile.canModify(sender.getUniqueId())) {
            if(ptile.isFree()) {
                msg.addExtra(button("Claim", "claim"));
            } else {
                msg.addExtra(button("Unclaim", "unclaim"));
                msg.addExtra(button("Transfer", "transfer"));
            }
        } else if(!ptile.isFree()) {
            msg.addExtra(button("Contest", "contest")); //TODO contest has no ALL option
        }
        msg.addExtra("\n"+line);
        return msg;
    }

    private TextComponent header(PlayerTile ptile){
        int x = ptile.getX();
        int z = ptile.getZ();
        TextComponent line = new TextComponent(ptile.getColor()+"\u2b1b"+ChatColor.RESET+ptile.getTitle());
        TextComponent subtitle = new TextComponent(ChatColor.GRAY+"\n["+x+", "+z+"] "+ptile.getOwnerName());
        line.addExtra(subtitle);
        return line;
    }

    private TextComponent button(String label,String command){
        int x = tile.getX();
        int z = tile.getZ();
        String world = tile.getWorld();
        TextComponent line = new TextComponent("\n"+label+": ");
        TextComponent txt = new TextComponent(ChatColor.BOLD+"[THIS]");
        txt.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/"+command+" THIS "+x+" "+z+" "+world));
        line.addExtra(txt);
        txt = new TextComponent(ChatColor.BOLD+"[AREA]");
        txt.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/"+command+" AREA "+x+" "+z+" "+world));
        line.addExtra(txt);
        return line;
    }

    private TextComponent button(String... args) {
        TextComponent line = new TextComponent(args[0]+": ");
        for(int i=1;i< args.length;i++) {
            TextComponent txt = new TextComponent(ChatColor.BOLD+args[i]);
            txt.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,args[i++]));
            line.addExtra(txt);
        }
        return line;
    }

     */
}
