package me.brioschi.acompanytest.world;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class WorldMap {

    private Map<Position, WorldItem> map;

    public WorldMap() {
        this.map = new Hashtable<>();
    }

    public void addWorldItem(WorldItem worldItem) {
        Position worldItemPosition = worldItem.getPosition();
        if (!map.containsKey(worldItemPosition)) {
            map.put(worldItemPosition, worldItem);
        } else {
            throw new InvalidParameterException(worldItemPosition.toString());
        }
    }

    public boolean checkIfTheNewPositionIsValid(Position newPosition) {
        return map.containsKey(newPosition);
    }

    public WorldItem getPlayerCurrentWorldItem(Position position) {
        if (checkIfTheNewPositionIsValid(position)) {
            return map.get(position);
        } else {
            throw new InvalidParameterException(position.toString());
        }
    }

    public WorldViewDTO getPlayerVisibleWorld(Position observerPosition) {
        List<WorldItem> visibleWorldItems = new ArrayList<>();
        for (int x = -STANDARD_RADIUS; x <= STANDARD_RADIUS; ++x) {
            for (int y = -STANDARD_RADIUS; y <= STANDARD_RADIUS; ++y) {
                Position targetPosition = new Position(
                        observerPosition.getX() + x,
                        observerPosition.getY() + y
                );
                if (map.containsKey(targetPosition)) {
                    visibleWorldItems.add(map.get(targetPosition));
                }
            }
        }
        WorldViewDTO worldViewDTO = new WorldViewDTO(
                observerPosition.getX() - STANDARD_RADIUS,
                observerPosition.getX() + STANDARD_RADIUS,
                observerPosition.getY() - STANDARD_RADIUS,
                observerPosition.getY() + STANDARD_RADIUS,
                visibleWorldItems
        );
        return worldViewDTO;
    }

    private static final int STANDARD_RADIUS = 2;

}
