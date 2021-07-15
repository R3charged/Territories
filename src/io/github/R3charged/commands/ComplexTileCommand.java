package io.github.R3charged.commands;

import io.github.R3charged.enums.Select;
import io.github.R3charged.interfaces.Parameter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ComplexTileCommand <E extends Enum<E>> extends TileCommand {
    protected E e;
    protected final String ENUM_PTRN = "^(\\D+)";

    @Override
    protected boolean getArguements(String args) {
        e = defaultOption();
        String a = getOption(args);
        if(a != null) {
            try {
                e = E.valueOf(e.getDeclaringClass(), args);
            } catch (IllegalArgumentException e) { //TODO error message
                return false;
            }
        }
        return super.getArguements(eraseOption(args));
    }



    protected abstract E defaultOption();

    /**
     * Finds option arguement from list of arguements
     * @param args
     * @return retrieved Option arguement
     */
    protected String getOption(String args) {
        if(args.length() == 0) {

        }
        else if(args.matches(ENUM_PTRN)) {
            Matcher m = Pattern.compile(ENUM_PTRN).matcher(args);
            try {
                if(m.find()) {
                    return m.group(1).toUpperCase();
                }
            } catch (Exception e) {

            }
        }
        return null;
    }
    protected String eraseOption(String args) {
        return args.replaceFirst(ENUM_PTRN,"");
    }
}
