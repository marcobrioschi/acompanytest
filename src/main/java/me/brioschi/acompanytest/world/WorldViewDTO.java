package me.brioschi.acompanytest.world;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class WorldViewDTO {

    private int minX = 0;
    private int maxX = 0;
    private int minY = 0;
    private int maxY = 0;
    private Map<Position, WorldItem> map = new Hashtable<>();

    // TODO: merge the 2 logics?
    public WorldViewDTO(int minX, int maxX, int minY, int maxY, List<WorldItem> items) {
        if ( (items != null) && (items.size() > 0) ) {
            for (int i = 0; i < items.size(); ++i) {
                WorldItem currentWorldItem = items.get(i);
                Position currentPosition = currentWorldItem.getPosition();
                map.put(currentPosition, currentWorldItem);
            }
        }
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    public WorldViewDTO(List<WorldItem> items) {
        if ( (items != null) && (items.size() > 0) ) {
            WorldItem firstWorldItem = items.get(0);
            Position firstItemPosition = firstWorldItem.getPosition();
            map.put(firstItemPosition, firstWorldItem);
            minX = maxX = firstItemPosition.getX();
            minY = maxY = firstItemPosition.getY();
            for (int i = 1; i < items.size(); ++i) {
                WorldItem currentWorldItem = items.get(i);
                Position currentPosition = currentWorldItem.getPosition();
                map.put(currentPosition, currentWorldItem);
                int currentX = currentPosition.getX();
                int currentY = currentPosition.getY();
                minX = Math.min(minX, currentX);
                maxX = Math.max(maxX, currentX);
                minY = Math.min(minY, currentY);
                maxY = Math.max(maxY, currentY);
            }
        }
    }

    public int getXDim() {
        return maxX - minX;
    }

    public int getYDim() {
        return maxY - minY;
    }

    // TODO the original position object isn't the right key
    public WorldItem getWorldItem(int x, int y) {
        Position requiredPosition = new Position(x + minX, y + minY);
        return map.get(requiredPosition);
    }

}

