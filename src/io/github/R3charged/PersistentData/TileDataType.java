package io.github.R3charged.PersistentData;

import io.github.R3charged.tile.Tile;
import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.SerializationUtils;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class TileDataType implements PersistentDataType<byte[], Tile> {

    @Override
    public Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @Override
    public Class<Tile> getComplexType() {
        return Tile.class;
    }

    @Override
    public byte[] toPrimitive(Tile tile, PersistentDataAdapterContext persistentDataAdapterContext) {
        return SerializationUtils.serialize(tile);
    }

    @Override
    public Tile fromPrimitive(byte[] bytes, PersistentDataAdapterContext persistentDataAdapterContext) {
        try {
            InputStream is = new ByteArrayInputStream(bytes);
            ObjectInputStream o = new ObjectInputStream(is);
            return (Tile) o.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
