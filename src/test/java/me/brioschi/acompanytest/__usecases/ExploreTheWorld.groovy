package me.brioschi.acompanytest.__usecases

import me.brioschi.acompanytest.command.CommandResponseDTO
import me.brioschi.acompanytest.gameengine.CurrentPlayerStatus
import me.brioschi.acompanytest.monster.Experience
import me.brioschi.acompanytest.monster.MonsterRepository
import me.brioschi.acompanytest.world.MoveCommand
import me.brioschi.acompanytest.gameengine.GameEngine
import me.brioschi.acompanytest.world.Position
import me.brioschi.acompanytest.world.WorldItem
import me.brioschi.acompanytest.world.WorldMap
import spock.lang.Ignore
import spock.lang.Specification

class ExploreTheWorld extends Specification {

    def "I move to north and I receive a new map"() {

        // TODO: rifare salendo di livello nell'interfaccia

        given:
        CurrentPlayerStatus currentPlayerStatus = new CurrentPlayerStatus(
                new Position(0, 0),
                Experience.CALLOW
        );
        WorldMap worldMap = new WorldMap()
        worldMap.addWorldItem(
                new WorldItem(new Position(0, 0), WorldItem.WorldItemType.FLOOR)
        );
        worldMap.addWorldItem(
                new WorldItem(new Position(1, 0), WorldItem.WorldItemType.FLOOR)
        );
        worldMap.addWorldItem(
                new WorldItem(new Position(0, 1), WorldItem.WorldItemType.FLOOR)
        );
        worldMap.addWorldItem(
                new WorldItem(new Position(1, 1), WorldItem.WorldItemType.FLOOR)
        );
        MonsterRepository monsterRepository = new MonsterRepository()
        GameEngine gameEngine = new GameEngine(currentPlayerStatus, worldMap, monsterRepository)
        MoveCommand command = new MoveCommand(MoveCommand.Direction.NORTH)

        when:

        CommandResponseDTO response = gameEngine.enterCommand(command)

        then:

        /*
        This is the expected view:

        .....
        .....
        ..##.
        ..##.
        .....

         */

        assert response.getVisibleWorld().getWorldItem(2, 1).getWorldItemType() == WorldItem.WorldItemType.FLOOR
        assert response.getVisibleWorld().getWorldItem(2, 2).getWorldItemType() == WorldItem.WorldItemType.FLOOR
        assert response.getVisibleWorld().getWorldItem(3, 1).getWorldItemType() == WorldItem.WorldItemType.FLOOR
        assert response.getVisibleWorld().getWorldItem(3, 2).getWorldItemType() == WorldItem.WorldItemType.FLOOR

    }

}
