package me.brioschi.acompanytest.world;

import me.brioschi.acompanytest.base.AValueObject;
import me.brioschi.acompanytest.monster.MonsterId;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class WorldItem  extends AValueObject {

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
        super(WorldItem.class);
        this.position = position;
        this.worldItemType = worldItemType;
        this.description = description;
        this.monsterIds = (monsterIds != null)?Collections.unmodifiableList(monsterIds):null;
    }

    @Override
    protected int valueObjectHashCode() {
        return Objects.hash(position, worldItemType, description, monsterIds);
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
        // TODO: ELEVATOR, DOOR
    }

}
