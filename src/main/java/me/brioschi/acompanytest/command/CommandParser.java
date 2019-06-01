package me.brioschi.acompanytest.command;

import me.brioschi.acompanytest.gameengine.ExitCommand;
import me.brioschi.acompanytest.monster.FightCommand;
import me.brioschi.acompanytest.world.LookCommand;
import me.brioschi.acompanytest.world.MoveCommand;

import java.util.Optional;

public class CommandParser {

    public Optional<GameCommand> parseCommand(String cmdLine) {

        GameCommand result;

        String[] cmdLineParams = cmdLine.split(" ");
        switch(cmdLineParams[0]) {
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
            case EXIT:
                result = new ExitCommand();
                break;
            default:
                result = null;
                break;

        }

        if (result != null) {
            return Optional.of(result);
        } else {
            return Optional.empty();
        }

    }

    // TODO move to a file (i18n)
    private static final String MOVE_NORTH = "north";
    private static final String MOVE_SOUTH = "south";
    private static final String MOVE_EAST = "east";
    private static final String MOVE_WEST = "west";
    private static final String LOOK_MAP = "look";
    private static final String KILL = "kill";
    private static final String EXIT = "exit";

}
