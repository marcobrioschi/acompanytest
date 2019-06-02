package me.brioschi.acompanytest.domain.character;

import me.brioschi.acompanytest.gameengine.command.CommandResponseDTO;
import me.brioschi.acompanytest.gameengine.command.CommandResultMessage;
import me.brioschi.acompanytest.gameengine.command.GameCommand;

public class SavePlayerCommand extends GameCommand {

    @Override
    public CommandResponseDTO execute() {
        playerRepository.save(currentPlayer);
        return new CommandResponseDTO(CommandResultMessage.PLAYER_SAVED);
    }

}
