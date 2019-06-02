package me.brioschi.acompanytest.domain.world;

import me.brioschi.acompanytest.domain.monster.MonsterId;

import java.util.Collections;
import java.util.List;

public class WorldItem {

    private final Position position;
    private final WorldItemType worldItemType;
    private final String description;
    private final List<MonsterId> monsterIds;

    public WorldItem(Position position, WorldItemType worldItemType) {
        this(position, worldItemType, null, null);
    }

    public WorldItem(Position position, WorldItemType worldItemType, List<MonsterId> monsterIds) {
        this(position, worldItemType, null, monsterIds);
    }

    public WorldItem(Position position, WorldItemType worldItemType, String description) {
        this(position, worldItemType, description, null);
    }

    public WorldItem(Position position, WorldItemType worldItemType, String description, List<MonsterId> monsterIds) {
        this.position = position;
        this.worldItemType = worldItemType;
        this.description = description;
        this.monsterIds = (monsterIds != null)?Collections.unmodifiableList(monsterIds):Collections.emptyList();
    }

    public Position getPosition() {
        return position;
    }

    public WorldItemType getWorldItemType() {
        return worldItemType;
    }

    public String getDescription() {
        return description;
    }

    public List<MonsterId> getMonsterIds() {
        return monsterIds;
    }

    public enum WorldItemType {
        STREET,
        FLOOR
    }

}
