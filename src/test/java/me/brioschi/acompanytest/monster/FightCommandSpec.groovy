package me.brioschi.acompanytest.monster

import me.brioschi.acompanytest.command.CommandResponseDTO
import me.brioschi.acompanytest.command.CommandResultMessage
import me.brioschi.acompanytest.gameengine.CurrentPlayerStatus
import me.brioschi.acompanytest.world.Position
import me.brioschi.acompanytest.world.WorldItem
import me.brioschi.acompanytest.world.WorldMap
import spock.lang.Specification

class FightCommandSpec extends Specification {

    Position position
    WorldItem worldItem
    WorldMap worldMap

    MonsterId monsterId1, monsterId2
    List<MonsterId> monsterIdList
    Monster monster1, monster2
    MonsterRepository monsterRepository = Mock(MonsterRepository)

    Experience playerExperience
    Experience monsterExperience

    CurrentPlayerStatus currentPlayerStatus

    def setup() {

        position = new Position(0, 0)
        worldItem = Mock(WorldItem)
        worldMap = Mock(WorldMap) {
            getPlayerCurrentWorldItem(position) >> worldItem
        }

        monsterId1 = new MonsterId("first monster")
        monsterId2 = new MonsterId("second monster")
        monster1 = Mock(Monster) {
            getName() >> "Monster Surname"
        }
        monsterExperience = Mock(Experience)
        monster2 = Mock(Monster) {
            getName() >> "Monster Name"
            getExperience() >> monsterExperience
        }
        monsterIdList = Arrays.asList(monsterId1, monsterId2)
        monsterRepository = Mock(MonsterRepository) {
            load(monsterId1) >> monster1
            load(monsterId2) >> monster2
        }

        playerExperience = Mock(Experience)
        currentPlayerStatus = Mock(CurrentPlayerStatus) {
            getCurrentPosition() >> position
            getCurrentExperience() >> playerExperience
        }

    }

    private FightCommand buildAFightCommand(String monsterName) {

        FightCommand command = new FightCommand(monsterName)

        command.setCurrentPlayerStatus(currentPlayerStatus)
        command.setWorldMap(worldMap)
        command.setMonsterRepository(monsterRepository)

        return command

    }

    def "If aren't monster in the location raise an error"() {

        given:

        FightCommand command = buildAFightCommand("Monster Name")

        when:

        CommandResponseDTO result = command.execute()

        then:

        _ * worldItem.getMonsterIds() >> null

        assert result.commandResultMessage == CommandResultMessage.NO_MONSTER_MATCH

    }

    def "If the monster isn't in the list raise an error"() {

        given:

        FightCommand command = buildAFightCommand("Monster Name Surname")

        when:

        CommandResponseDTO result = command.execute()

        then:

        _ * worldItem.getMonsterIds() >> monsterIdList

        assert result.commandResultMessage == CommandResultMessage.NO_MONSTER_MATCH

    }

    def "If you win with the monster you will gain his experience"() {

        given:

        FightCommand command = buildAFightCommand("Monster Name")

        when:

        CommandResponseDTO result = command.execute()

        then:

        _ * worldItem.getMonsterIds() >> monsterIdList
        1 * monster2.fight(playerExperience) >> true
        1 * currentPlayerStatus.increaseExperience(monsterExperience)

        assert result.commandResultMessage == CommandResultMessage.YOU_WIN_THE_FIGHT

    }

    def "if you lose with the monster you will lose his experience value"() {

        given:

        FightCommand command = buildAFightCommand("Monster Name")

        when:

        CommandResponseDTO result = command.execute()

        then:

        _ * worldItem.getMonsterIds() >> monsterIdList
        1 * monster2.fight(playerExperience) >> false
        1 * currentPlayerStatus.decreaseExperience(monsterExperience)

        assert result.commandResultMessage == CommandResultMessage.YOU_LOSE_THE_FIGHT

    }

}
