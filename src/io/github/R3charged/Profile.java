package io.github.R3charged;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

public class Profile {

    private static NamespacedKey adminMode;

    public static void initialize(Territories plugin) {
        adminMode = new NamespacedKey(plugin,"Admin");
    }

    public static int getAccess(Player player) {
        if (getAdminMode(player)) {
            return 5;
        }
        if (isOwner) {
            return 4;
        }
        if (inContest) {
            return 2;
        }
        if (isAlly) {
            return 3;
        }
        return 1;
    }


    public static boolean getAdminMode(Player player) {
        Integer i = player.getPersistentDataContainer().get(adminMode, PersistentDataType.INTEGER);
        if (i == null) {
            setAdminMode(player, false);
        }
        return i == 1;
    }

    public static void setAdminMode(Player player, boolean bool) {
        player.getPersistentDataContainer().set(adminMode, PersistentDataType.INTEGER, bool ? 1 : 0);
    }

}
