package io.github.R3charged.collections;

import io.github.R3charged.Profile;

import java.util.HashMap;
import java.util.UUID;

public class ProfileMap {

    private static HashMap<UUID, Profile> profileMap = new HashMap<UUID, Profile>();

    public static HashMap<UUID, Profile> get() {
        return profileMap;
    }

}
