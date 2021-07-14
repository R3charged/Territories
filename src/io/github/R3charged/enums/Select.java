package io.github.R3charged.enums;

import io.github.R3charged.interfaces.Parameter;

public enum Select implements Parameter<Select> {
    FILL, ALL, THIS;

    @Override
    public Select fromString(String arg) {
        return valueOf(arg.toUpperCase());
    }
}
