package me.brioschi.acompanytest.world

import me.brioschi.acompanytest.command.CommandResponseDTO
import me.brioschi.acompanytest.gameengine.CurrentPlayerStatus
import spock.lang.Specification

class LookCommandSpec extends Specification {

    def "Look at the current map location"() {

        given:

        WorldMap worldMap = Mock(WorldMap)
        CurrentPlayerStatus currentPlayerStatus = Mock(CurrentPlayerStatus)
        Position position = Mock(Position)
        WorldViewDTO worldView = Mock(WorldViewDTO)

        LookCommand command = new LookCommand()
        command.setWorldMap(worldMap)
        command.setCurrentPlayerStatus(currentPlayerStatus)

        when:

        CommandResponseDTO result = command.execute()

        then:

        1 * currentPlayerStatus.getCurrentPosition() >> position
        1 * worldMap.getPlayerVisibleWorld(position) >> worldView

        assert result.getVisibleWorld() == worldView

    }

}
