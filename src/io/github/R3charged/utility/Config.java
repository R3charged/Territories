package io.github.R3charged.utility;

import io.github.R3charged.Territories;
import org.bukkit.configuration.file.FileConfiguration;

public class Config {
    private static FileConfiguration config;

    public static final boolean GSON_STORAGE = false;

    public static final double DIAMOND_VALUE = 1;
    public static final double NETHERITE_VALUE = 8;

    public static void initialize() {
        config = Territories.get().getConfig();
        Territories.get().saveDefaultConfig();
    }

    public static int getInt(String path) {
        return config.getInt(path);
    }

    public static boolean getBoolean(String path) {
        return config.getBoolean(path);
    }
}
