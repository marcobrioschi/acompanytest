package me.brioschi.acompanytest.gameengine;

import me.brioschi.acompanytest.command.CommandResponseDTO;
import me.brioschi.acompanytest.command.GameCommand;
import me.brioschi.acompanytest.monster.MonsterRepository;
import me.brioschi.acompanytest.world.Position;
import me.brioschi.acompanytest.world.WorldMap;

public class GameEngine {

    CurrentPlayerStatus currentPlayerStatus;
    WorldMap worldMap;
    MonsterRepository monsterRepository;

    public GameEngine(
            CurrentPlayerStatus currentPlayerStatus,
            WorldMap worldMap,
            MonsterRepository monsterRepository
    ) {
        this.currentPlayerStatus = currentPlayerStatus;
        this.worldMap = worldMap;
        this.monsterRepository = monsterRepository;
    }

    public CommandResponseDTO enterCommand(GameCommand gameCommand) {
        gameCommand.setCurrentPlayerStatus(this.currentPlayerStatus);
        gameCommand.setWorldMap(this.worldMap);
        gameCommand.setMonsterRepository(this.monsterRepository);
        return gameCommand.execute();
    }

}
