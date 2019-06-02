package me.brioschi.acompanytest.__usecases

import me.brioschi.acompanytest.persistence.WorldMapBuilder
import me.brioschi.acompanytest.domain.character.Player
import me.brioschi.acompanytest.domain.monster.Experience
import me.brioschi.acompanytest.domain.world.MoveCommand
import me.brioschi.acompanytest.domain.world.Position
import me.brioschi.acompanytest.domain.world.WorldItem
import me.brioschi.acompanytest.domain.world.WorldMap
import me.brioschi.acompanytest.gameengine.GameEngine
import me.brioschi.acompanytest.gameengine.command.CommandResponseDTO
import me.brioschi.acompanytest.persistence.MonsterRepository
import me.brioschi.acompanytest.persistence.PlayerRepository
import spock.lang.Specification

class ExploreTheWorld extends Specification {

    def "I move to north and I receive a new map"() {

        given:

        Player currentPlayer = new Player("The walker", new Position(0, 0), Experience.CALLOW)

        MonsterRepository monsterRepository = new MonsterRepository();

        WorldMap worldMap = new WorldMapBuilder(0, 0)
                .addFloor(0, 0)
                .addFloor(1, 0)
                .addFloor(0, 1)
                .addFloor(1, 1)
                .build()

        PlayerRepository playerRepository = new PlayerRepository();

        GameEngine gameEngine = new GameEngine(worldMap, monsterRepository, playerRepository)
        MoveCommand command = new MoveCommand(MoveCommand.Direction.NORTH)

        when:

        CommandResponseDTO response = gameEngine.enterCommand(currentPlayer, command)

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
