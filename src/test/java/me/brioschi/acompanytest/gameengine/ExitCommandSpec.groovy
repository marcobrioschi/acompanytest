package me.brioschi.acompanytest.gameengine

import me.brioschi.acompanytest.domain.character.Player
import me.brioschi.acompanytest.gameengine.command.CommandResponseDTO
import me.brioschi.acompanytest.gameengine.command.CommandResultMessage
import me.brioschi.acompanytest.domain.world.WorldMap
import me.brioschi.acompanytest.domain.world.WorldViewDTO
import spock.lang.Specification

class ExitCommandSpec extends Specification{

    def "When the command exit is given, say hello and terminate"() {

        given:

        WorldMap worldMap = Mock(WorldMap)
        Player currentPlayer = Mock(Player)
        WorldViewDTO worldView = Mock(WorldViewDTO)

        ExitCommand command = new ExitCommand()
        command.setWorldMap(worldMap)
        command.setCurrentPlayer(currentPlayer)

        when:

        CommandResponseDTO result = command.execute()

        then:

        assert result.getCommandResultMessage() == CommandResultMessage.HAVE_A_NICE_DAY

    }

}
