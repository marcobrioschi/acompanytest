package me.brioschi.acompanytest.domain.character;

import me.brioschi.acompanytest.gameengine.command.CommandResponseDTO;
import me.brioschi.acompanytest.gameengine.command.CommandResultMessage;
import me.brioschi.acompanytest.gameengine.command.GameCommand;
import me.brioschi.acompanytest.domain.monster.Experience;

public class CreatePlayerCommand extends GameCommand {

    private final String playerName;

    public CreatePlayerCommand(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    @Override
    public CommandResponseDTO execute() {
        if (playerRepository.load(playerName) == null) {
            Player createdPlayer = new Player(
                    playerName,
                    worldMap.getWorldEntryPoint(),
                    Experience.CALLOW
            );
            playerRepository.save(createdPlayer);
            return new CommandResponseDTO (
                    createdPlayer,
                    CommandResultMessage.PLAYER_CREATED
            );
        } else {
            return new CommandResponseDTO(CommandResultMessage.INVALID_PLAYER_NAME);
        }
    }

}
