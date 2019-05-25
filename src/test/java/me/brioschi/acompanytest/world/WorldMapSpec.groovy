package me.brioschi.acompanytest.world


import spock.lang.Specification
import spock.lang.Unroll

import java.security.InvalidParameterException

class WorldMapSpec extends Specification {

    def "You can't add two times the same position to a world"() {

        given:

        Position testPosition = new Position(1, 1)
        WorldMap worldMap = new WorldMap()

        when:

        worldMap.addWorldItem(new WorldItem(testPosition, WorldItem.WorldItemType.FLOOR))

        then:

        notThrown InvalidParameterException

        when:

        worldMap.addWorldItem(new WorldItem(testPosition, WorldItem.WorldItemType.STREET))

        then:

        thrown InvalidParameterException

    }

    private static WorldItem buildWorldItem(int x, int y) {
        return new WorldItem(new Position(x, y), WorldItem.WorldItemType.FLOOR);
    }

    @Unroll
    def "If the destination point isn't valid (#x, #y), return #isValid"(int x, int y, boolean isValid) {

        given:

        WorldMap worldMap = new WorldMap()

        worldMap.addWorldItem(buildWorldItem(1, 1))
        worldMap.addWorldItem(buildWorldItem(0, 1))
        worldMap.addWorldItem(buildWorldItem(2, 1))
        worldMap.addWorldItem(buildWorldItem(1, 0))
        worldMap.addWorldItem(buildWorldItem(1, 2))

        Position startPosition = Mock(Position)
        Position endPosition = new Position(x, y)

        when:

        boolean result = worldMap.checkIfTheNewPositionIsValid(endPosition)

        then:

        assert result == isValid

        where:

        x   |   y   ||  isValid
        0   |   0   ||  false
        0   |   1   ||  true
        0   |   2   ||  false
        1   |   0   ||  true
        1   |   1   ||  true
        1   |   2   ||  true
        2   |   0   ||  false
        2   |   1   ||  true
        2   |   2   ||  false

    }

}
