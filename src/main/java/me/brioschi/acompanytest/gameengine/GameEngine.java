package me.brioschi.acompanytest.gameengine;

import me.brioschi.acompanytest.domain.character.Player;
import me.brioschi.acompanytest.persistence.PlayerRepository;
import me.brioschi.acompanytest.gameengine.command.CommandResponseDTO;
import me.brioschi.acompanytest.gameengine.command.GameCommand;
import me.brioschi.acompanytest.persistence.MonsterRepository;
import me.brioschi.acompanytest.domain.world.WorldMap;

public class GameEngine {

    WorldMap worldMap;
    MonsterRepository monsterRepository;
    PlayerRepository playerRepository;

    public GameEngine(
            WorldMap worldMap,
            MonsterRepository monsterRepository,
            PlayerRepository playerRepository
    ) {
        this.worldMap = worldMap;
        this.monsterRepository = monsterRepository;
        this.playerRepository = playerRepository;
    }

    public CommandResponseDTO enterCommand(Player currentPlayer, GameCommand gameCommand) {
        gameCommand.setCurrentPlayer(currentPlayer);
        gameCommand.setWorldMap(this.worldMap);
        gameCommand.setMonsterRepository(this.monsterRepository);
        gameCommand.setPlayerRepository(this.playerRepository);
        return gameCommand.execute();
    }

}
