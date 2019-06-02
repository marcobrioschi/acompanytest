package me.brioschi.acompanytest.__usecases

import me.brioschi.acompanytest.persistence.WorldMapBuilder
import me.brioschi.acompanytest.domain.character.Player
import me.brioschi.acompanytest.domain.monster.Experience
import me.brioschi.acompanytest.domain.monster.FightCommand
import me.brioschi.acompanytest.domain.monster.Monster
import me.brioschi.acompanytest.domain.monster.MonsterId
import me.brioschi.acompanytest.domain.world.Position
import me.brioschi.acompanytest.domain.world.WorldMap
import me.brioschi.acompanytest.gameengine.GameEngine
import me.brioschi.acompanytest.gameengine.command.CommandResponseDTO
import me.brioschi.acompanytest.gameengine.command.CommandResultMessage
import me.brioschi.acompanytest.persistence.MonsterRepository
import me.brioschi.acompanytest.persistence.PlayerRepository
import spock.lang.Specification

class FightTheMonster extends Specification {

    def "Fight a monster in the current position and win"() {

        given:

        Player currentPlayer = new Player("The fighter", new Position(0, 0), Experience.CALLOW)

        MonsterRepository monsterRepository = new MonsterRepository();
        MonsterId monsterId = new MonsterId("monsterA")
        monsterRepository.save(
            new Monster(monsterId, "First Monster", "The first monster of the map", Experience.CALLOW, true)
        );

        WorldMap worldMap = new WorldMapBuilder(0, 0)
                .addStreet(0, 0)
                .addMonsterId(0, 0, monsterId)
                .build()

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
