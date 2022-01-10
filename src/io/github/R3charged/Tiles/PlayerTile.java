package io.github.R3charged.Tiles;

import io.github.R3charged.Config;
import io.github.R3charged.Perm;
import io.github.R3charged.Possessable;

import java.util.HashMap;

public class PlayerTile extends Tile implements Possessable {

    private transient static HashMap<Perm, Integer> perms = Config.getPlayertilePerms();



}
