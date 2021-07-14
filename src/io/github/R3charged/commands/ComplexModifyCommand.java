package io.github.R3charged.commands;

import io.github.R3charged.enums.Select;
import io.github.R3charged.interfaces.Parameter;
import io.github.R3charged.utility.Loc;
import org.bukkit.entity.Player;

public abstract class ComplexModifyCommand<E extends Parameter> extends ComplexTileCommand<Select>{
    public abstract void exeCmd(Player sender, Loc loc, Select select, E e);
}
