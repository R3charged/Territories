package io.github.R3charged.commands;

import io.github.R3charged.interfaces.Parameter;
import io.github.R3charged.utility.Loc;
import org.bukkit.entity.Player;

public abstract class ComplexTileCommand<E extends Parameter> extends TileCommand {

    public abstract void exeCmd(Player sender, Loc loc, E e);

}
