package io.github.R3charged;

import java.util.HashMap;

public class Config {

    public final static int TICK_INTERVAL = 20;

    //CONTEST
    public final static int DEFAULT_TAKE_RATE = 1;
    public final static boolean ENEMY_CAN_BUILD = true;



    // Admin = 5    Owner = 4   Officer = 3   Friend/Ally = 2    Contest = 1     Neutral = 0
    private final static HashMap<Perm, Integer> PLAYERTILE_PERMS = new HashMap<>();
    {
        PLAYERTILE_PERMS.put(Perm.BUILDING, 2);
        PLAYERTILE_PERMS.put(Perm.SETTINGS, 4);
    }


    public static HashMap<Perm, Integer> getPlayertilePerms() {
        return (HashMap<Perm, Integer>) PLAYERTILE_PERMS.clone();
    }

    public static void initialize() {

    }
}
