package io.github.R3charged;

import io.github.R3charged.utility.Saveable;

import java.util.HashMap;
import java.util.UUID;

public class ProfileManager implements Saveable {
    private static HashMap<UUID,Profile> profileMap;

    /**
     *  Returns a profile for the indicated player. If the player does not have a profile,
     *  then a new one is created.
     * @param uuid of the player
     * @return Profile for the player.
     */
    public static Profile getProfile(UUID uuid) {
        if(!profileMap.containsKey(uuid)) { //when map doesn't contain key
            Profile profile = new Profile();
            profileMap.put(uuid,profile);
            return profile;
        }
        return profileMap.get(uuid);
    }

    /**
     * Loads profile data from file.
     */
    public static void initialize() {

    }

    /**
     * Writes current profile data to file.
     */
    public static void write() {

    }
}
