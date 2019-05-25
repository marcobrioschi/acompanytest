package me.brioschi.acompanytest.gameengine;

import me.brioschi.acompanytest.command.CommandResponseDTO;
import me.brioschi.acompanytest.command.GameCommand;
import me.brioschi.acompanytest.world.Position;
import me.brioschi.acompanytest.world.WorldMap;

public class GameEngine {

    CurrentPlayerStatus currentPlayerStatus;
    WorldMap worldMap;

    public GameEngine(
            CurrentPlayerStatus currentPlayerStatus,
            WorldMap worldMap
    ) {
        this.currentPlayerStatus = currentPlayerStatus;
        this.worldMap = worldMap;
    }

    public CommandResponseDTO enterCommand(GameCommand gameCommand) {
        gameCommand.setCurrentPlayerStatus(this.currentPlayerStatus);
        gameCommand.setWorldMap(this.worldMap);
        return gameCommand.execute();
    }

}
