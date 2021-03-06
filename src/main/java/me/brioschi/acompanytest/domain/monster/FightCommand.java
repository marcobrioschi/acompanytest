package me.brioschi.acompanytest.domain.monster;

import me.brioschi.acompanytest.gameengine.command.CommandResponseDTO;
import me.brioschi.acompanytest.gameengine.command.CommandResultMessage;
import me.brioschi.acompanytest.gameengine.command.GameCommand;
import me.brioschi.acompanytest.domain.world.WorldItem;

import java.util.List;
import java.util.Optional;

public class FightCommand extends GameCommand {

    private final String monsterName;

    public FightCommand(String monsterName) {
        this.monsterName = monsterName;
    }

    public String getMonsterName() {
        return monsterName;
    }

    @Override
    public CommandResponseDTO execute() {

        List<MonsterId> monsterIds = findMonstersInTheCurrentPosition();
        if (monsterIds != null) {
            Optional<Monster> opMonster = findTheRightMonster(monsterIds);
            if (opMonster.isPresent()) {
                return fightTheMonster(opMonster.get());
            } else {
                return noMonsterFound();
            }
        } else {
            return noMonsterFound();
        }

    }

    private List<MonsterId> findMonstersInTheCurrentPosition() {
        WorldItem currentWorldItem =
                worldMap.getPlayerCurrentWorldItem(
                        currentPlayer.getCurrentPosition()
                );
        return currentWorldItem.getMonsterIds();
    }

    private Optional<Monster> findTheRightMonster(List<MonsterId> monsterIds) {
        for (MonsterId monsterId : monsterIds) {
            Monster monster = monsterRepository.load(monsterId);
            if ( monster.getName().equals(this.monsterName) ) {
                return Optional.of(monster);
            }
        }
        return Optional.empty();
    }

    private CommandResponseDTO fightTheMonster(Monster currentMonster) {

        Experience monsterExperience = currentMonster.getExperience();
        Experience playerExperience = currentPlayer.getCurrentExperience();

        CommandResultMessage resultMessage;
        if (currentMonster.fight(playerExperience)) {
            currentPlayer.increaseExperience(monsterExperience);
            resultMessage = CommandResultMessage.YOU_WIN_THE_FIGHT;
        } else {
            currentPlayer.decreaseExperience(monsterExperience);
            resultMessage = CommandResultMessage.YOU_LOSE_THE_FIGHT;
        }

        return new CommandResponseDTO(resultMessage);

    }

    private CommandResponseDTO noMonsterFound() {
        return new CommandResponseDTO(
                CommandResultMessage.NO_MONSTER_MATCH
        );
    }

}
