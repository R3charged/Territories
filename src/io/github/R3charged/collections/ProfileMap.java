package io.github.R3charged.collections;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import io.github.R3charged.Profile;
import io.github.R3charged.Territories;

import java.io.*;
import java.util.HashMap;
import java.util.UUID;
import java.util.function.BiConsumer;

public class ProfileMap {

    private static File dir = new File(Territories.get().getDataFolder() + "\\profiles.json");

    private static FileWriter writer;
    static {
        try {
            writer = new FileWriter(dir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Gson gson = new GsonBuilder().setPrettyPrinting().enableComplexMapKeySerialization().create();

    private static HashMap<UUID, Profile> profileMap = new HashMap<UUID, Profile>();

    public static HashMap<UUID, Profile> get() {
        return profileMap;
    }

    public static void deserialize() {

        try {
            FileReader reader = new FileReader(dir);
            gson.fromJson( reader, new TypeToken<HashMap<UUID, Profile>>(){}.getType() );
            reader.close();
        } catch (IOException e) {

        }

    }

    public static void serialize() {
        gson.toJson(profileMap, writer);
        try {
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
