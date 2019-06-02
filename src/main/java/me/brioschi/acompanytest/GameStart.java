package me.brioschi.acompanytest;

import me.brioschi.acompanytest.character.Player;
import me.brioschi.acompanytest.character.PlayerRepository;
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

        Player currentPlayer = null;
        WorldMapRepository worldMapRepository = new WorldMapRepository();
        MonsterRepository monsterRepository = new MonsterRepository();
        PlayerRepository playerRepository = new PlayerRepository();
        GameEngine gameEngine = new GameEngine(
                worldMapRepository.loadCurrentMap(),
                monsterRepository,
                playerRepository
        );

        CommandParser commandParser = new CommandParser();
        ScreenManager screenManager = new ScreenManager(System.in, System.out); // java.io.Console doesn't work on IDE!!!

        boolean gameCompleted = false;
        do {

            screenManager.prompt(currentPlayer);
            String cmdLine = screenManager.nextLine();

            Optional<GameCommand> optionalGameCommand = commandParser.parseCommand(cmdLine, (currentPlayer == null));
            if (optionalGameCommand.isPresent()) {

                GameCommand currentGameCommand = optionalGameCommand.get();
                CommandResponseDTO cmdResult = gameEngine.enterCommand(currentPlayer, currentGameCommand);
                if (cmdResult.getCurrentPlayer() != null) {
                    currentPlayer = cmdResult.getCurrentPlayer();
                }
                screenManager.printCommandResult(currentPlayer, monsterRepository, cmdResult);

                if (currentGameCommand instanceof ExitCommand) {
                    gameCompleted = true;
                }

            } else {
                screenManager.printInvalidCommand(cmdLine);
            }

        } while(!gameCompleted);

    }

}
