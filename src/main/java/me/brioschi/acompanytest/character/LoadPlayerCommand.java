package me.brioschi.acompanytest.character;

import me.brioschi.acompanytest.command.CommandResponseDTO;
import me.brioschi.acompanytest.command.CommandResultMessage;
import me.brioschi.acompanytest.command.GameCommand;

public class LoadPlayerCommand extends GameCommand {

    private final String playerName;

    public LoadPlayerCommand(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    @Override
    public CommandResponseDTO execute() {
        Player loadedPlayer = this.playerRepository.load(playerName);
        if (loadedPlayer != null) {
            return new CommandResponseDTO(loadedPlayer, CommandResultMessage.PLAYER_LOADED);
        } else {
            return new CommandResponseDTO(CommandResultMessage.INVALID_PLAYER_NAME);
        }
    }

}
