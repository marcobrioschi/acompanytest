package me.brioschi.acompanytest.domain.world;

import me.brioschi.acompanytest.gameengine.command.CommandResponseDTO;
import me.brioschi.acompanytest.gameengine.command.CommandResultMessage;
import me.brioschi.acompanytest.gameengine.command.GameCommand;

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
    }

}
