package io.github.R3charged.collections;

import com.google.gson.*;
import io.github.R3charged.Territories;
import io.github.R3charged.tile.Tile;
import io.github.R3charged.utility.Loc;
import org.bukkit.Chunk;
import org.bukkit.World;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.function.BiConsumer;

public class TileMap {

    private static File dir = new File(Territories.get().getDataFolder() + "\\saves");
    private static Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Tile.class, new InterfaceAdapter<Tile>()).create();



    private static HashMap<Loc, Tile> tileMap = new HashMap<Loc, Tile>();

    /**
     * Returns tilemap hash map.
     * @return tile map
     */
    public static HashMap<Loc, Tile> get() {
        return tileMap;
    }

    public static void makeDirectories() {
        dir.mkdir();
        for(World w : Territories.get().getServer().getWorlds()) { //mk world dirs
            new File(dir + "\\" + w.getName()).mkdir();
            System.out.println(w.getName());
        }
    }

    public static void deserialize() {
        for(Chunk c :Territories.get().getServer().getWorld("world").getLoadedChunks()) {
            deserialize(new Loc(c));
        }
    }

    /**
     * Reads tile data from files.
     */
    public static void deserialize(Loc l) {
        Tile t = read(l);
        if(t == null) {
            return;
        }
        tileMap.put(l, t);
    }

    public static Tile read(Loc l) {
        File path = getPath(l);

        try {
            FileReader reader = new FileReader(path); //might be making the file, which would be bad
            Tile tile = gson.fromJson(reader, Tile.class);
            tile.setLoc(l);
            return tile;

        } catch(FileNotFoundException | NullPointerException e) {
            return null;
        }
    }

    /**
     * Writes tile data to files.
     */
    public static void serialize() {

        BiConsumer<Loc, Tile> iterator = (l, t) -> {
            try {
                File dest = getPath(l); // 1,1.json
                FileWriter writer = new FileWriter(dest);
                gson.toJson(t, Tile.class, writer);
                writer.close();
            } catch (Exception e) {
                System.out.println("Unable to save tile data.");
            }
        };

        tileMap.forEach(iterator);
    }

    private static File getPath(Loc l) {
        return new File(dir + l.getPath());
    }

    private static class InterfaceAdapter<T> implements JsonSerializer<T>, JsonDeserializer<T> {
        public JsonElement serialize(T object, Type interfaceType, JsonSerializationContext context) {
            JsonObject wrapper = new JsonObject();
            wrapper.addProperty("type", object.getClass().getName());
            wrapper.add("data", context.serialize(object));
            return wrapper;
        }

        public T deserialize(JsonElement elem, Type interfaceType, JsonDeserializationContext context) {
            JsonObject wrapper = (JsonObject) elem;
            JsonElement typeName = get(wrapper, "type");
            JsonElement data = get(wrapper, "data");
            Type actualType = typeForName(typeName);
            return context.deserialize(data, actualType);
        }

        private Type typeForName(JsonElement typeElem) {
            try {
                return Class.forName(typeElem.getAsString());
            } catch (ClassNotFoundException e) {
                throw new JsonParseException(e);
            }
        }

        private JsonElement get(JsonObject wrapper, String memberName) {
            JsonElement elem = wrapper.get(memberName);
            if (elem == null)
                throw new JsonParseException("no " + memberName + " member found");
            return elem;
        }
    }
}
