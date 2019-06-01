package me.brioschi.acompanytest.world;

import me.brioschi.acompanytest.monster.MonsterId;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

// TODO Rimuovere?

public class WorldMapBuilder {

    private Map<Position, WorldItem> items = new Hashtable<>();

    public WorldMapBuilder addStreet(int x, int y) {
        Position key = new Position(x, y);
        items.put(key, new WorldItem(key, WorldItem.WorldItemType.STREET));
        return this;
    }

    public WorldMapBuilder addFloor(int x, int y) {
        Position key = new Position(x, y);
        items.put(key, new WorldItem(key, WorldItem.WorldItemType.FLOOR));
        return this;
    }

    public WorldMapBuilder addDescription(int x, int y, String description) {
        Position key = new Position(x, y);
        WorldItem oldItem = items.get(key);
        items.put(
                key,
                new WorldItem(
                        key,
                        oldItem.getWorldItemType(),
                        description,
                        oldItem.getMonsterIds()
                )
        );
        return this;
    }

    public WorldMapBuilder addMonsterId(int x, int y, MonsterId monsterId) {
        Position key = new Position(x, y);
        WorldItem oldItem = items.get(key);
        List<MonsterId> monsterIds = (oldItem.getMonsterIds() != null)?oldItem.getMonsterIds():new ArrayList<MonsterId>();
        monsterIds.add(monsterId);
        items.put(
                key,
                new WorldItem(
                        key,
                        oldItem.getWorldItemType(),
                        oldItem.getDescription(),
                        monsterIds
                )
        );
        return this;
    }

    public WorldMap build() {
        WorldMap worldMap = new WorldMap();
        for (WorldItem item : items.values()) {
            worldMap.addWorldItem(item);
        }
        return worldMap;
    }

}
