package me.brioschi.acompanytest.world;

import me.brioschi.acompanytest.base.AValueObject;

import java.security.InvalidParameterException;
import java.util.Objects;

public class Position extends AValueObject {

    private final int x;
    private final int y;

    public Position(int x, int y) {
        super(Position.class);
        this.x = x;
        this.y = y;
    }

    @Override
    protected int valueObjectHashCode() {
        return Objects.hash(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position applyMovement(MoveCommand.Direction direction) {

        int newX = x;
        int newY = y;

        switch (direction) {
            case NORTH:
                newY += 1;
                break;
            case EAST:
                newX += 1;
                break;
            case SOUTH:
                newY -= 1;
                break;
            case WEST:
                newX -= 1;
                break;
            default:
                throw new InvalidParameterException(direction.toString());
        }

        return new Position(newX, newY);

    }

}

