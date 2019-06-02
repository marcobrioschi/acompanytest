package me.brioschi.acompanytest.persistence;

import me.brioschi.acompanytest.domain.monster.MonsterId;
import me.brioschi.acompanytest.domain.world.Position;
import me.brioschi.acompanytest.domain.world.WorldItem;
import me.brioschi.acompanytest.domain.world.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class WorldMapRepository {

    private WorldMap worldMap;

    public WorldMap loadCurrentMap() {
        return worldMap;
    }

    public void saveCurrentMap(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

}
