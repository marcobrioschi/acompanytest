package me.brioschi.acompanytest.domain.world

import me.brioschi.acompanytest.domain.character.Player
import me.brioschi.acompanytest.domain.monster.FightCommand
import me.brioschi.acompanytest.domain.world.MoveCommand
import me.brioschi.acompanytest.domain.world.Position
import me.brioschi.acompanytest.domain.world.WorldMap
import me.brioschi.acompanytest.domain.world.WorldViewDTO
import me.brioschi.acompanytest.gameengine.GameEngine
import me.brioschi.acompanytest.gameengine.command.CommandResponseDTO
import me.brioschi.acompanytest.gameengine.command.CommandResultMessage
import spock.lang.Specification

class MoveCommandSpec extends Specification {

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

    private MoveCommand buildAMoveCommand(MoveCommand.Direction direction) {

        MoveCommand command = new MoveCommand(direction)

        command.setCurrentPlayer(currentPlayer)
        command.setWorldMap(worldMap)

        return command

    }

    def "If the movement is valid the position will be updated"() {

        given:

        MoveCommand moveCommand = buildAMoveCommand(MoveCommand.Direction.NORTH)

        when:

        CommandResponseDTO response = moveCommand.execute()

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

        MoveCommand moveCommand = buildAMoveCommand(MoveCommand.Direction.SOUTH)

        when:

        CommandResponseDTO response = moveCommand.execute()

        then:

        1 * currentPlayer.getCurrentPosition() >> startPosition
        1 * startPosition.applyMovement(MoveCommand.Direction.SOUTH) >> endPosition
        1 * worldMap.checkIfTheNewPositionIsValid(endPosition) >> false

        assert response.getCommandResultMessage() == CommandResultMessage.INVALID_DIRECTION

    }

}
