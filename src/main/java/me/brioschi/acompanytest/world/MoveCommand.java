package me.brioschi.acompanytest.world;

import me.brioschi.acompanytest.command.CommandResponseDTO;
import me.brioschi.acompanytest.command.CommandResultMessage;
import me.brioschi.acompanytest.command.GameCommand;

public class MoveCommand extends GameCommand {

    private final Direction direction;

    public MoveCommand(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public CommandResponseDTO execute() {
        Position newPosition = currentPlayer.getCurrentPosition().applyMovement(direction);
        if (worldMap.checkIfTheNewPositionIsValid(newPosition)) {
            currentPlayer.setCurrentPosition(newPosition);
            return new CommandResponseDTO(
                    worldMap.getPlayerVisibleWorld(newPosition)
            );
        } else {
            return new CommandResponseDTO(
                    CommandResultMessage.INVALID_DIRECTION
            );
        }
    }

    public enum Direction {
        NORTH,
        EAST,
        SOUTH,
        WEST
        // TODO
    }

}
