package me.brioschi.acompanytest.gameengine

import me.brioschi.acompanytest.character.Player
import me.brioschi.acompanytest.command.CommandResponseDTO
import me.brioschi.acompanytest.command.CommandResultMessage
import me.brioschi.acompanytest.world.MoveCommand
import me.brioschi.acompanytest.world.Position
import me.brioschi.acompanytest.world.WorldMap
import me.brioschi.acompanytest.world.WorldViewDTO
import spock.lang.Specification

class GameEngineSpec extends Specification {

    WorldMap worldMap
    Player currentPlayer
    Position startPosition
    Position endPosition
    WorldViewDTO worldView
    GameEngine gameEngine

    def setup() {
        worldMap = Mock(WorldMap)
        currentPlayer = Mock(Player)
        startPosition = Mock(Position)
        endPosition = Mock(Position)
        worldView = Mock(WorldViewDTO)
        gameEngine = new GameEngine(worldMap, null, null) // TODO: use a mock?
    }

    // TODO: mote these tests on MoveCommancSpec
    def "If the movement is valid the position will be updated"() {

        given:

        MoveCommand moveCommand = new MoveCommand(MoveCommand.Direction.NORTH)

        when:

        CommandResponseDTO response = gameEngine.enterCommand(currentPlayer, moveCommand)

        then:

        1 * currentPlayer.getCurrentPosition() >> startPosition
        1 * startPosition.applyMovement(MoveCommand.Direction.NORTH) >> endPosition
        1 * worldMap.checkIfTheNewPositionIsValid(endPosition) >> true
        1 * currentPlayer.setCurrentPosition(endPosition)
        1 * worldMap.getPlayerVisibleWorld(endPosition) >> worldView

        assert response.getVisibleWorld() == worldView

    }

    def "If the movement isn't valid, the position don't change and it return a message"() {

        given:

        MoveCommand moveCommand = new MoveCommand(MoveCommand.Direction.SOUTH)

        when:

        CommandResponseDTO response = gameEngine.enterCommand(currentPlayer, moveCommand)

        then:

        1 * currentPlayer.getCurrentPosition() >> startPosition
        1 * startPosition.applyMovement(MoveCommand.Direction.SOUTH) >> endPosition
        1 * worldMap.checkIfTheNewPositionIsValid(endPosition) >> false

        assert response.getCommandResultMessage() == CommandResultMessage.INVALID_DIRECTION

    }

}
