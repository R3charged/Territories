package io.github.R3charged.utility;

import io.github.R3charged.Territories;
import org.bukkit.configuration.file.FileConfiguration;

public class Config {
    private static FileConfiguration config;

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
