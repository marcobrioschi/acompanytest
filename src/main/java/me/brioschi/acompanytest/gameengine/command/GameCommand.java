package me.brioschi.acompanytest.gameengine.command;

import me.brioschi.acompanytest.domain.character.Player;
import me.brioschi.acompanytest.persistence.PlayerRepository;
import me.brioschi.acompanytest.persistence.MonsterRepository;
import me.brioschi.acompanytest.domain.world.WorldMap;

public abstract class GameCommand {

    protected Player currentPlayer;
    protected WorldMap worldMap;
    protected MonsterRepository monsterRepository;
    protected PlayerRepository playerRepository;

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setWorldMap(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public void setMonsterRepository(MonsterRepository monsterRepository) {
        this.monsterRepository = monsterRepository;
    }

    public void setPlayerRepository(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public abstract CommandResponseDTO execute();

}
