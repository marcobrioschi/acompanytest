package me.brioschi.acompanytest.world


import spock.lang.Specification
import spock.lang.Unroll

class PositionSpec extends Specification {

    @Unroll
    def "The position can be updated from a MoveCommand #direction"(MoveCommand.Direction direction, int newX, int newY) {

        given:

        Position oldPosition = new Position(0, 0)

        when:

        Position newPosition = oldPosition.applyMovement(direction)

        then:

        assert newPosition.getX() == newX
        assert newPosition.getY() == newY

        where:

        direction                       |   newX    |   newY
        MoveCommand.Direction.NORTH     |   0       |   1
        MoveCommand.Direction.EAST      |   1       |   0
        MoveCommand.Direction.SOUTH     |   0       |   -1
        MoveCommand.Direction.WEST      |   -1      |   0

    }

    def "All the direction values are managed"() {

        given:

        Position p = new Position(0, 0)

        expect:

        for (MoveCommand.Direction testDirection : MoveCommand.Direction.values()) {
            p.applyMovement(testDirection)
        }

    }

}
