package me.brioschi.acompanytest;

import me.brioschi.acompanytest.domain.monster.Experience;
import me.brioschi.acompanytest.domain.monster.Monster;
import me.brioschi.acompanytest.domain.monster.MonsterId;
import me.brioschi.acompanytest.domain.world.WorldMap;
import me.brioschi.acompanytest.persistence.MonsterRepository;
import me.brioschi.acompanytest.persistence.WorldMapBuilder;
import me.brioschi.acompanytest.persistence.WorldMapRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DemoRepositoryCreator {

    private List<Monster> monsterList;
    private WorldMap worldMap;

    public DemoRepositoryCreator() {
        generateMonsters();
        generateWorldMap();
    }

    private void generateMonsters() {
        monsterList = new ArrayList<>();
        generateMonster("mns1", "Guardian", "The defender of Kalma", 10, false);
        generateMonster("mns2", "Mad", "The killer of Nobur", 20, false);
        generateMonster("mns3", "Mad", "The killer of Nobur", 25, false);
        generateMonster("mns4", "Alo", "The destructor of the humanity", 60, false);
        generateMonster("mns5", "Android", "An evil machine", 10, true);
        generateMonster("mns6", "Android", "An evil machine", 10, true);
    }

    private void generateMonster(String id, String name, String description, int experienceValue, boolean isLoser) {
        monsterList.add(new Monster(
                new MonsterId(id),
                name,
                description,
                new Experience(experienceValue),
                isLoser
            )
        );
    }

    private void generateWorldMap() {
        String streetDescription = "You are on the street of damn, all people can kill you in a moment ...";
        String RoomDescription1 = "In this room the evil usually speak with the soul of the dead people.";
        String RoomDescription2 = "This is the house of the Aon, be quiet to do not wake him!";
        String RoomDescription3 = "In the shadow the enemies are creating the weapons for Aon, the master of the infern ... keep attention!!!";
        String RoomDescription4 = "Don't be ingenuous, also the walls are looking to you ... you are not alone ... run !!!";
        String RoomDescription5 = "In this room you hear the sound of the water ... but it's really water?";
        worldMap = new WorldMapBuilder(7, 1)
                .addHorizontalStreet(3, 9, 1, streetDescription)
                .addHorizontalStreet(13, 15, 1, streetDescription)
                .addVerticalStreet(4, 2, 5, streetDescription)
                .addVerticalStreet(11, 3, 5, streetDescription)
                .addRoom(0, 0, 2, 2, RoomDescription1)
                .addRoom(10, 0, 12, 2, RoomDescription2)
                .addRoom(16, 0, 18, 2, RoomDescription3)
                .addRoom(3, 6, 5, 8, RoomDescription4)
                .addRoom(10, 6, 12, 8, RoomDescription5)
                .addMonsterId(0, 0, "mns5")
                .addMonsterId(0, 2, "mns6")
                .addMonsterId(4, 8, "mns3")
                .addMonsterId(11, 1, "mns1")
                .addMonsterId(11, 7, "mns4")
                .addMonsterId(18, 0, "mns2")
                .build();
    }

    public WorldMapRepository getWorldMapRepository() {
        WorldMapRepository worldMapRepository = new WorldMapRepository();
        worldMapRepository.saveCurrentMap(worldMap);
        return worldMapRepository;
    }

    public MonsterRepository getMonsterRepository() {
        MonsterRepository monsterRepository = new MonsterRepository();
        for (Monster monster : monsterList) {
            monsterRepository.save(monster);
        }
        return monsterRepository;
    }

}
