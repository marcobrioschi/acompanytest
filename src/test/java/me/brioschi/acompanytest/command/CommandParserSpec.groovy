package me.brioschi.acompanytest.command

import me.brioschi.acompanytest.character.CreatePlayerCommand
import me.brioschi.acompanytest.character.LoadPlayerCommand
import me.brioschi.acompanytest.character.SavePlayerCommand
import me.brioschi.acompanytest.gameengine.ExitCommand
import me.brioschi.acompanytest.monster.FightCommand
import me.brioschi.acompanytest.world.LookCommand
import me.brioschi.acompanytest.world.MoveCommand
import spock.lang.Specification
import spock.lang.Unroll

import static me.brioschi.acompanytest.world.MoveCommand.Direction.*

class CommandParserSpec extends Specification {

    CommandParser commandParser

    def setup() {
        commandParser = new CommandParser()
    }

    def "If not a valid command is recognized, return an empty option"() {

        when:

        Optional<GameCommand> result = commandParser.parseCommand("abcdefghijeklm", false)

        then:

        assert result.isPresent() == false

    }

    @Unroll
    def "If a move command is recognized, return it: #cmdLine -> #direction"(String cmdLine, MoveCommand.Direction direction) {

        when:

        Optional<GameCommand> result = commandParser.parseCommand(cmdLine, false)

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

        when:

        Optional<GameCommand> result = commandParser.parseCommand("look", false)

        then:

        assert result.get() instanceof LookCommand

    }

    @Unroll
    def "if the kill command is recognized, return it [#cmdLine > #monsterName]"(String cmdLine, String monsterName) {

        when:

        Optional<GameCommand> result = commandParser.parseCommand(cmdLine, false)

        then:

        assert result.get() instanceof FightCommand
        assert ((FightCommand)result.get()).getMonsterName() == monsterName

        where:

        cmdLine             ||  monsterName
        "kill abaco"        ||  "abaco"
        "kill nome mostro"  ||  "nome mostro"

    }

    def "if the create player command is recognized, return it"() {

        when:

        Optional<GameCommand> result = commandParser.parseCommand("create mike the fist", false)

        then:

        assert result.get() instanceof CreatePlayerCommand
        assert ((CreatePlayerCommand)result.get()).getPlayerName() == "mike the fist"
    }

    def "If the load command is recognized, return it"() {

        when:

        Optional<GameCommand> result = commandParser.parseCommand("load the player", false)

        then:

        assert result.get() instanceof LoadPlayerCommand
        assert ((LoadPlayerCommand)result.get()).getPlayerName() == "the player"

    }

    def "If the save is recognized, return it"() {

        when:

        Optional<GameCommand> result = commandParser.parseCommand("save", false)

        then:

        assert result.get() instanceof SavePlayerCommand

    }

    def "If the exit is recognized, return it"() {

        when:

        Optional<GameCommand> result = commandParser.parseCommand("exit", false)

        then:

        assert result.get() instanceof ExitCommand

    }

}
