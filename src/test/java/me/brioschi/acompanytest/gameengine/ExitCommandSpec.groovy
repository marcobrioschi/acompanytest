package me.brioschi.acompanytest.gameengine

import me.brioschi.acompanytest.command.CommandResponseDTO
import me.brioschi.acompanytest.command.CommandResultMessage
import me.brioschi.acompanytest.world.LookCommand
import me.brioschi.acompanytest.world.Position
import me.brioschi.acompanytest.world.WorldMap
import me.brioschi.acompanytest.world.WorldViewDTO
import spock.lang.Specification

class ExitCommandSpec extends Specification{

    def "When the command exit is given, say hello and terminate"() {

        given:

        WorldMap worldMap = Mock(WorldMap)
        CurrentPlayerStatus currentPlayerStatus = Mock(CurrentPlayerStatus)
        WorldViewDTO worldView = Mock(WorldViewDTO)

        ExitCommand command = new ExitCommand()
        command.setWorldMap(worldMap)
        command.setCurrentPlayerStatus(currentPlayerStatus)

        when:

        CommandResponseDTO result = command.execute()

        then:

        assert result.getCommandResultMessage() == CommandResultMessage.HAVE_A_NICE_DAY

    }

}
