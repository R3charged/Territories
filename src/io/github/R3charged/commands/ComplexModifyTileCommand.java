package io.github.R3charged.commands;

import io.github.R3charged.tile.Tile;

public abstract class ComplexModifyTileCommand<T extends Tile, F extends Enum<F>> extends ModifyTileCommand<T>{

    protected F f;
    @Override
    protected boolean getArguements(String args) {

        f = defaultOptionTwo();
        String a = getOption(args);
        if(a != null) {
            try {
                f = F.valueOf(f.getDeclaringClass(), args);
            } catch (IllegalArgumentException e) { //TODO error message
                return false;
            }
        }
        return super.getArguements(eraseOption(args));
    }

    protected abstract F defaultOptionTwo();
}
