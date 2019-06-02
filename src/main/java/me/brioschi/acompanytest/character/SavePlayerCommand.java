package me.brioschi.acompanytest.character;

import me.brioschi.acompanytest.command.CommandResponseDTO;
import me.brioschi.acompanytest.command.CommandResultMessage;
import me.brioschi.acompanytest.command.GameCommand;

public class SavePlayerCommand extends GameCommand {

    @Override
    public CommandResponseDTO execute() {
        playerRepository.save(currentPlayer);
        return new CommandResponseDTO(CommandResultMessage.PLAYER_SAVED);
    }

}
