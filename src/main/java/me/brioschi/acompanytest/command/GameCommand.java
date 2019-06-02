package me.brioschi.acompanytest.command;

import me.brioschi.acompanytest.character.Player;
import me.brioschi.acompanytest.character.PlayerRepository;
import me.brioschi.acompanytest.monster.MonsterRepository;
import me.brioschi.acompanytest.world.WorldMap;

public abstract class GameCommand {

    protected WorldMap worldMap;
    protected Player currentPlayer;
    protected MonsterRepository monsterRepository;
    protected PlayerRepository playerRepository;

    public void setWorldMap(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setMonsterRepository(MonsterRepository monsterRepository) {
        this.monsterRepository = monsterRepository;
    }

    public void setPlayerRepository(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public abstract CommandResponseDTO execute();

}
