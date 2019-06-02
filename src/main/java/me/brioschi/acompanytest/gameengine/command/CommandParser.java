package me.brioschi.acompanytest.gameengine.command;

import me.brioschi.acompanytest.domain.character.CreatePlayerCommand;
import me.brioschi.acompanytest.domain.character.LoadPlayerCommand;
import me.brioschi.acompanytest.domain.character.SavePlayerCommand;
import me.brioschi.acompanytest.domain.monster.FightCommand;
import me.brioschi.acompanytest.domain.world.LookCommand;
import me.brioschi.acompanytest.domain.world.MoveCommand;
import me.brioschi.acompanytest.gameengine.ExitCommand;

import java.util.Optional;

public class CommandParser {

    public Optional<GameCommand> parseCommand(String cmdLine, boolean onlyInitCommands) {

        String[] cmdLineParams = cmdLine.split(" ");
        String firstParam = cmdLineParams[0];

        GameCommand result = parseInitCommand(firstParam, cmdLine);
        if ( !onlyInitCommands && (result == null) ) {
            result = parseCommonCommand(firstParam, cmdLine);
        }

        if (result != null) {
            return Optional.of(result);
        } else {
            return Optional.empty();
        }

    }

    private GameCommand parseInitCommand(String firstParam, String cmdLine) {
        GameCommand result;
        switch(firstParam) {
            case CREATE:
                result = new CreatePlayerCommand(
                        cmdLine.replaceFirst(CREATE, "").trim()
                );
                break;
            case LOAD:
                result = new LoadPlayerCommand(
                        cmdLine.replaceFirst(LOAD, "").trim()
                );
                break;
            default:
                result = null;
                break;
        }
        return result;
    }

    private GameCommand parseCommonCommand(String firstParam, String cmdLine) {
        GameCommand result;
        switch(firstParam) {
            case MOVE_NORTH:
                result = new MoveCommand(MoveCommand.Direction.NORTH);
                break;
            case MOVE_SOUTH:
                result = new MoveCommand(MoveCommand.Direction.SOUTH);
                break;
            case MOVE_EAST:
                result = new MoveCommand(MoveCommand.Direction.EAST);
                break;
            case MOVE_WEST:
                result = new MoveCommand(MoveCommand.Direction.WEST);
                break;
            case LOOK_MAP:
                result = new LookCommand();
                break;
            case KILL:
                result = new FightCommand(
                        cmdLine.replaceFirst(KILL, "").trim()
                );
                break;
            case SAVE:
                result = new SavePlayerCommand();
                break;
            case EXIT:
                result = new ExitCommand();
                break;
            default:
                result = null;
                break;
        }
        return result;
    }

    private static final String MOVE_NORTH = "north";
    private static final String MOVE_SOUTH = "south";
    private static final String MOVE_EAST = "east";
    private static final String MOVE_WEST = "west";
    private static final String LOOK_MAP = "look";
    private static final String KILL = "kill";
    private static final String CREATE = "create";
    private static final String LOAD = "load";
    private static final String SAVE = "save";
    private static final String EXIT = "exit";

}
