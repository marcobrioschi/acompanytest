package me.brioschi.acompanytest.domain.world

import me.brioschi.acompanytest.WorldMapBuilder
import spock.lang.Specification
import spock.lang.Unroll

import java.security.InvalidParameterException

class WorldMapSpec extends Specification {

    def "You can't add two times the same position to a world"() {

        given:

        Position testPosition = new Position(1, 1)
        WorldMap worldMap = new WorldMap( new Position(0 ,0 ))

        when:

        worldMap.addWorldItem(new WorldItem(testPosition, WorldItem.WorldItemType.FLOOR))

        then:

        notThrown InvalidParameterException

        when:

        worldMap.addWorldItem(new WorldItem(testPosition, WorldItem.WorldItemType.STREET))

        then:

        thrown InvalidParameterException

    }

    @Unroll
    def "If the destination point isn't valid (#x, #y), return #isValid"(int x, int y, boolean isValid) {

        given:

        WorldMap worldMap = new WorldMapBuilder(0 ,0)
                .addFloor(1, 1)
                .addFloor(0, 1)
                .addFloor(0, 1)
                .addFloor(2, 1)
                .addFloor(1, 0)
                .addFloor(1, 2)
                .build();

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
