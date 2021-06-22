package io.github.R3charged.commands;

import io.github.R3charged.tile.PlayerTile;
import io.github.R3charged.tile.RestrictedTile;
import io.github.R3charged.tile.Tile;
import io.github.R3charged.utility.Chat;
import io.github.R3charged.utility.Loc;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Map extends Command{

    private final String TILE_CHAR = "\u2b1b";
    private final String EMPTY_CHAR = "\u2b1c";

    private final int MAP_WIDTH = 29;
    private final int MAP_HEIGHT = 13;

    @Override
    protected void exeCmd() {
        TextComponent[][] arr = getMapArray(loc.getX() - (MAP_WIDTH/2),loc.getZ() - (MAP_HEIGHT/2));
        TextComponent message = new TextComponent(ChatColor.UNDERLINE+"Map                                                   ");
        for(int j = 0; j < MAP_HEIGHT; j++) {
            message.addExtra("\n");
            for(int i = 0; i < MAP_WIDTH; i++) {
                message.addExtra(arr[j][i]);
            }
        }
        sender.spigot().sendMessage(message);
    }

    /**
     * Creates an array of TextComponents of Tiles and map elements.
     * @param x furthest left x coord of the map
     * @param z furthest lower z coord of the map.
     * @return
     */
    private TextComponent[][] getMapArray(int x, int z) {
        TextComponent[][] arr = new TextComponent[MAP_HEIGHT][MAP_WIDTH];
        for (int j = 0; j < MAP_HEIGHT; j++) {
            for (int i = 0; i < MAP_WIDTH; i++) {
                arr[j][i] = drawTile(new Loc(x + i,z + j, loc.getWorld()));
            }
        }
        //Center
        arr[MAP_HEIGHT/2][MAP_WIDTH/2].setText("✦");
        //Compass
        addCompass(arr);
        return arr;
    }

    private TextComponent drawTile(Loc l) {
        Tile t = Tile.get(l);
        if(t instanceof PlayerTile) {
            return drawTile((PlayerTile) t);
        } else if(t instanceof RestrictedTile) {
            return drawTile((RestrictedTile) t);
        } else { //null case
            TextComponent empty = new TextComponent(EMPTY_CHAR);
            empty.setColor(net.md_5.bungee.api.ChatColor.DARK_GRAY);
            return empty;
        }
    }

    private TextComponent drawTile(PlayerTile t) {
        TextComponent tile = new TextComponent(TILE_CHAR);

        tile.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT,
                new Text(t.getLoc().getX()+","+t.getLoc().getZ() +"\n" +
                        Bukkit.getOfflinePlayer(t.getOwner()).getName()+ " V: " + t.getValue())));
        tile.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/inspect "+t.getLoc().getX()+" "+t.getLoc().getZ()+" "+loc.getWorld()));
        return tile;
    }

    private TextComponent drawTile(RestrictedTile t) {
        TextComponent tile = new TextComponent();

        return tile;
    }

    private void addCompass(TextComponent[][] arr)
    {
        arr[0][1].setText(ChatColor.DARK_GRAY + "N.");
        arr[1][0].setText(ChatColor.DARK_GRAY + "W.");
        arr[1][1].setText("\u2747");
        arr[1][2].setText(ChatColor.DARK_GRAY + "E.");
        arr[2][1].setText(ChatColor.DARK_GRAY + "S.");
        float yaw = sender.getLocation().getYaw();
        Chat.debug(yaw+"");
        if(yaw>=135||yaw<=-135)
            arr[0][1].setText(ChatColor.WHITE + "N.");
        else if(yaw<=135&&yaw>=45)
            arr[1][0].setText(ChatColor.WHITE + "W.");
        else if(yaw<-45&&yaw>-135)
            arr[1][2].setText(ChatColor.WHITE + "E.");
        else
            arr[2][1].setText(ChatColor.WHITE + "S.");
    }
}
