package me.brioschi.acompanytest.world;

public class WorldMapRepository {

    // TODO: evolve, use files, method to save

    private WorldItem buildWorldItem(int x, int y) {
        return new WorldItem(new Position(x, y), WorldItem.WorldItemType.STREET);
    }

    public WorldMap loadCurrentMap() {
        WorldMap worldMap = new WorldMap();
        worldMap.addWorldItem(buildWorldItem(0, 0));
        worldMap.addWorldItem(buildWorldItem(1, 0));
        worldMap.addWorldItem(buildWorldItem(2, 0));
        worldMap.addWorldItem(buildWorldItem(3, 0));
        worldMap.addWorldItem(buildWorldItem(4, 0));
        worldMap.addWorldItem(buildWorldItem(4, 1));
        worldMap.addWorldItem(buildWorldItem(4, 2));
        return worldMap;
    }

}
