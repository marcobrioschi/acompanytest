package me.brioschi.acompanytest.persistence;

import me.brioschi.acompanytest.domain.monster.MonsterId;
import me.brioschi.acompanytest.domain.world.Position;
import me.brioschi.acompanytest.domain.world.WorldItem;
import me.brioschi.acompanytest.domain.world.WorldMap;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class WorldMapBuilder {

    private Map<Position, WorldItem> items = new Hashtable<>();
    private Position worldEntryPoint;

    public WorldMapBuilder(int x, int y) {
        this.worldEntryPoint = new Position(x, y);
    }

    public WorldMapBuilder addStreet(int x, int y) {
        Position key = new Position(x, y);
        items.put(key, new WorldItem(key, WorldItem.WorldItemType.STREET));
        return this;
    }

    public WorldMapBuilder addVerticalStreet(int x, int y1, int y2, String description) {
        for (int y = y1; y <= y2; ++y) {
            addStreet(x, y);
            addDescription(x, y, description);
        }
        return this;
    }

    public WorldMapBuilder addHorizontalStreet(int x1, int x2, int y, String description) {
        for (int x = x1; x <= x2; ++x) {
            addStreet(x, y);
            addDescription(x, y, description);
        }
        return this;
    }

    public WorldMapBuilder addFloor(int x, int y) {
        Position key = new Position(x, y);
        items.put(key, new WorldItem(key, WorldItem.WorldItemType.FLOOR));
        return this;
    }

    public WorldMapBuilder addRoom(int x1, int y1, int x2, int y2, String description) {
        for (int x = x1; x <= x2; ++x) {
            for (int y = y1; y <= y2; ++y) {
                addFloor(x, y);
                addDescription(x, y, description);
            }
        }
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

    public WorldMapBuilder addMonsterId(int x, int y, String monsterId) {
        return addMonsterId(x, y, new MonsterId(monsterId));
    }

    public WorldMapBuilder addMonsterId(int x, int y, MonsterId monsterId) {
        Position key = new Position(x, y);
        WorldItem oldItem = items.get(key);
        List<MonsterId> monsterIds = new ArrayList<>(oldItem.getMonsterIds());
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
        WorldMap worldMap = new WorldMap(worldEntryPoint);
        for (WorldItem item : items.values()) {
            worldMap.addWorldItem(item);
        }
        return worldMap;
    }

}
