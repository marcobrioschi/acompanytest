package me.brioschi.acompanytest.command

import me.brioschi.acompanytest.gameengine.ExitCommand
import me.brioschi.acompanytest.monster.FightCommand
import me.brioschi.acompanytest.world.LookCommand
import me.brioschi.acompanytest.world.MoveCommand
import spock.lang.Specification
import spock.lang.Unroll

import static me.brioschi.acompanytest.world.MoveCommand.Direction.*

class CommandParserSpec extends Specification {

    def "If not a valid command is recognized, return an empty option"() {

        given:

        CommandParser commandParser = new CommandParser()

        when:

        Optional<GameCommand> result = commandParser.parseCommand("abcdefghijeklm")

        then:

        assert result.isPresent() == false

    }

    @Unroll
    def "If a move command is recognized, return it: #cmdLine -> #direction"(String cmdLine, MoveCommand.Direction direction) {

        given:

        CommandParser commandParser = new CommandParser()

        when:

        Optional<GameCommand> result = commandParser.parseCommand(cmdLine)

        then:

        assert result.get() instanceof MoveCommand
        assert ((MoveCommand)result.get()).getDirection() == direction

        where:

        cmdLine ||  direction
        "north" |   NORTH
        "south" |   SOUTH
        "east"  |   EAST
        "west"  |   WEST

    }

    def "If the look command is recognized, return it"() {

        given:

        CommandParser commandParser = new CommandParser()

        when:

        Optional<GameCommand> result = commandParser.parseCommand("look")

        then:

        assert result.get() instanceof LookCommand

    }

    @Unroll
    def "if the kill command is recognized, return it [#cmdLine > #monsterName]"(String cmdLine, String monsterName) {

        given:

        CommandParser commandParser = new CommandParser()

        when:

        Optional<GameCommand> result = commandParser.parseCommand(cmdLine)

        then:

        assert result.get() instanceof FightCommand
        assert ((FightCommand)result.get()).getMonsterName() == monsterName

        where:

        cmdLine             ||  monsterName
        "kill abaco"        ||  "abaco"
        "kill nome mostro"  ||  "nome mostro"

    }

    def "If the exit is recognized, return it"() {

        given:

        CommandParser commandParser = new CommandParser()

        when:

        Optional<GameCommand> result = commandParser.parseCommand("exit")

        then:

        assert result.get() instanceof ExitCommand

    }

}
