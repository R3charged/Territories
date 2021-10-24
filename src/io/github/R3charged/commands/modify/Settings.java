package io.github.R3charged.commands.modify;

import io.github.R3charged.commands.ModifyTileCommand;
import io.github.R3charged.enums.Select;
import io.github.R3charged.tile.Tile;
import io.github.R3charged.utility.Loc;
import io.github.R3charged.utility.Setter;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Settings extends ModifyTileCommand {

    //(tile, obj) -> tile.setSomething(obj);
    private HashMap<String, Setter<Tile, Player, Object>> settings = new HashMap<>();
    {
        settings.put("Explosions", (T, S, O) -> {S.sendMessage("boop!"); });
        settings.put("Color", (T, S, O) -> { T.setColor((ChatColor) O); });
    }


    public void execute(Player sender, Loc loc, Select select, String setting, Object arg) {
        Function<Tile, Boolean> function = t -> {
            if (t.canModify(sender.getUniqueId())) {
                settings.get(setting).execute(t, sender, arg);
                return true;
            }
            return false;
        };
        executor = function;
        super.execute(sender, loc, select);
    }

    public void execute(Player sender, Loc loc, Select select) {
        sender.sendMessage("Please indicate what setting to change.");
    }


    //Explosions: True False
    //Color: Red, Blue, Dark Red, etc
    //Title: String



}
