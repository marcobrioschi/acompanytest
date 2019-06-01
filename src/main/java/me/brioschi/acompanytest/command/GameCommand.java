package me.brioschi.acompanytest.command;

import me.brioschi.acompanytest.gameengine.CurrentPlayerStatus;
import me.brioschi.acompanytest.monster.MonsterRepository;
import me.brioschi.acompanytest.world.WorldMap;

public abstract class GameCommand {

    protected WorldMap worldMap;
    protected CurrentPlayerStatus currentPlayerStatus;
    protected MonsterRepository monsterRepository;

    public void setWorldMap(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public void setCurrentPlayerStatus(CurrentPlayerStatus currentPlayerStatus) {
        this.currentPlayerStatus = currentPlayerStatus;
    }

    public void setMonsterRepository(MonsterRepository monsterRepository) {
        this.monsterRepository = monsterRepository;
    }

    public abstract CommandResponseDTO execute();

}
