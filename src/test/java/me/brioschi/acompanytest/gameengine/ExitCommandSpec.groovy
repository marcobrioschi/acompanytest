package me.brioschi.acompanytest.gameengine

import me.brioschi.acompanytest.character.Player
import me.brioschi.acompanytest.command.CommandResponseDTO
import me.brioschi.acompanytest.command.CommandResultMessage
import me.brioschi.acompanytest.world.WorldMap
import me.brioschi.acompanytest.world.WorldViewDTO
import spock.lang.Specification

class ExitCommandSpec extends Specification{

    def "When the command exit is given, say hello and terminate"() {

        given:

        WorldMap worldMap = Mock(WorldMap)
        Player currentPlayerStatus = Mock(Player)
        WorldViewDTO worldView = Mock(WorldViewDTO)

        ExitCommand command = new ExitCommand()
        command.setWorldMap(worldMap)
        command.setCurrentPlayer(currentPlayerStatus)

        when:

        CommandResponseDTO result = command.execute()

        then:

        assert result.getCommandResultMessage() == CommandResultMessage.HAVE_A_NICE_DAY

    }

}
