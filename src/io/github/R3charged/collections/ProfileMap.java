package io.github.R3charged.collections;

import io.github.R3charged.Profile;
import io.github.R3charged.Territories;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

public class ProfileMap {

    private static File dir = new File(Territories.get().getDataFolder() + "\\profiles");

    private static HashMap<UUID, Profile> profileMap = new HashMap<UUID, Profile>();

    public static HashMap<UUID, Profile> get() {
        return profileMap;
    }

}
