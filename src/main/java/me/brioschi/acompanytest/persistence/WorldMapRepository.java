package me.brioschi.acompanytest.persistence;

import me.brioschi.acompanytest.domain.monster.MonsterId;
import me.brioschi.acompanytest.domain.world.Position;
import me.brioschi.acompanytest.domain.world.WorldItem;
import me.brioschi.acompanytest.domain.world.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class WorldMapRepository {

    // TODO: evolve, use files, method to save

    private WorldItem buildWorldItem(int x, int y) {
        return new WorldItem(
                new Position(x, y),
                WorldItem.WorldItemType.STREET,
                "WI_WALL_STREET"
        );
    }

    public WorldMap loadCurrentMap() {

        WorldMap worldMap = new WorldMap(new Position(0, 0));
        worldMap.addWorldItem(buildWorldItem(0, 0));
        worldMap.addWorldItem(buildWorldItem(1, 0));
        worldMap.addWorldItem(buildWorldItem(2, 0));
        worldMap.addWorldItem(buildWorldItem(3, 0));
        worldMap.addWorldItem(buildWorldItem(4, 0));
        worldMap.addWorldItem(buildWorldItem(4, 1));

        List<MonsterId> monsterIdList = new ArrayList<>();
        monsterIdList.add(new MonsterId("ms_0001"));
        worldMap.addWorldItem(
                new WorldItem(
                        new Position(4, 2),
                        WorldItem.WorldItemType.STREET,
                        "WI_MONSTER_HOUSE",
                        monsterIdList
                )
        );

        return worldMap;
    }

}
