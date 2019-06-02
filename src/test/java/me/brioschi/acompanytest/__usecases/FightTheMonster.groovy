package me.brioschi.acompanytest.__usecases

import me.brioschi.acompanytest.character.PlayerRepository
import me.brioschi.acompanytest.command.CommandResponseDTO
import me.brioschi.acompanytest.command.CommandResultMessage
import me.brioschi.acompanytest.character.Player
import me.brioschi.acompanytest.gameengine.GameEngine
import me.brioschi.acompanytest.monster.Experience
import me.brioschi.acompanytest.monster.FightCommand
import me.brioschi.acompanytest.monster.Monster
import me.brioschi.acompanytest.monster.MonsterId
import me.brioschi.acompanytest.monster.MonsterRepository
import me.brioschi.acompanytest.world.Position
import me.brioschi.acompanytest.world.WorldItem
import me.brioschi.acompanytest.world.WorldMap
import spock.lang.Specification

class FightTheMonster extends Specification {

    def "Fight a monster in the current position and win"() {

        given:

        Player currentPlayer = new Player("The fighter", new Position(0, 0), Experience.CALLOW)
        List<MonsterId> monsterIds = Arrays.asList(new MonsterId("monsterA"))
        WorldMap worldMap = new WorldMap()
        worldMap.addWorldItem(
                new WorldItem(
                        new Position(0, 0),
                        WorldItem.WorldItemType.STREET,
                        monsterIds
                )
        );
        MonsterRepository monsterRepository = new MonsterRepository();
        monsterRepository.save(
            new Monster(new MonsterId("monsterA"), "First Monster", "The first monster of the map", Experience.CALLOW, true)
        );
        PlayerRepository playerRepository = new PlayerRepository();
        GameEngine gameEngine = new GameEngine(worldMap, monsterRepository, playerRepository)
        FightCommand command = new FightCommand("First Monster")

        when:

        CommandResponseDTO response = gameEngine.enterCommand(currentPlayer, command)

        then:

        assert currentPlayer.getCurrentExperience().getExperience() == 20
        assert response.getCommandResultMessage() == CommandResultMessage.YOU_WIN_THE_FIGHT

    }

}
