package me.brioschi.acompanytest.domain.world

import me.brioschi.acompanytest.domain.character.Player
import me.brioschi.acompanytest.gameengine.command.CommandResponseDTO
import spock.lang.Specification

class LookCommandSpec extends Specification {

    def "Look at the current map location"() {

        given:

        WorldMap worldMap = Mock(WorldMap)
        Player currentPlayerStatus = Mock(Player)
        Position position = Mock(Position)
        WorldViewDTO worldView = Mock(WorldViewDTO)

        LookCommand command = new LookCommand()
        command.setWorldMap(worldMap)
        command.setCurrentPlayer(currentPlayerStatus)

        when:

        CommandResponseDTO result = command.execute()

        then:

        1 * currentPlayerStatus.getCurrentPosition() >> position
        1 * worldMap.getPlayerVisibleWorld(position) >> worldView

        assert result.getVisibleWorld() == worldView

    }

}
