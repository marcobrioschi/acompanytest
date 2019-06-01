package me.brioschi.acompanytest;

import me.brioschi.acompanytest.gameengine.CurrentPlayerStatus;
import me.brioschi.acompanytest.gameengine.ExitCommand;
import me.brioschi.acompanytest.gameengine.GameEngine;
import me.brioschi.acompanytest.command.CommandParser;
import me.brioschi.acompanytest.command.CommandResponseDTO;
import me.brioschi.acompanytest.command.GameCommand;
import me.brioschi.acompanytest.io.ScreenManager;
import me.brioschi.acompanytest.monster.Experience;
import me.brioschi.acompanytest.monster.MonsterRepository;
import me.brioschi.acompanytest.world.*;

import java.util.Optional;

public class GameStart {

    public static void main(String[] args) {

        // TODO manage args

        // TODO fix (start point in the map?, how identify the user?)
        Position currentPosition = new Position(0, 0);
        CurrentPlayerStatus currentPlayerStatus = new CurrentPlayerStatus(
                currentPosition,
                Experience.CALLOW
        );
        WorldMapRepository worldMapRepository = new WorldMapRepository();
        MonsterRepository monsterRepository = new MonsterRepository();
        GameEngine gameEngine = new GameEngine(
                currentPlayerStatus,
                worldMapRepository.loadCurrentMap(),
                monsterRepository
        );

        CommandParser commandParser = new CommandParser();
        ScreenManager screenManager = new ScreenManager(System.in, System.out); // java.io.Console doesn't work on IDE!!!

        // Show current map
        screenManager.printCommandResult(
                currentPlayerStatus,
                monsterRepository,
                gameEngine.enterCommand(new LookCommand())
        );

        boolean gameCompleted = false;
        do {

            screenManager.prompt();
            String cmdLine = screenManager.nextLine();

            Optional<GameCommand> optionalGameCommand = commandParser.parseCommand(cmdLine);
            if (optionalGameCommand.isPresent()) {

                GameCommand currentGameCommand = optionalGameCommand.get();
                CommandResponseDTO cmdResult = gameEngine.enterCommand(currentGameCommand);
                screenManager.printCommandResult(currentPlayerStatus, monsterRepository, cmdResult);

                if (currentGameCommand instanceof ExitCommand) {
                    gameCompleted = true;
                }

            } else {
                screenManager.printInvalidCommand(cmdLine);
            }

        } while(!gameCompleted);

    }

}
