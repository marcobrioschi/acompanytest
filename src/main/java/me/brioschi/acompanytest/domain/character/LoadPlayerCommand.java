package me.brioschi.acompanytest.domain.character;

import me.brioschi.acompanytest.gameengine.command.CommandResponseDTO;
import me.brioschi.acompanytest.gameengine.command.CommandResultMessage;
import me.brioschi.acompanytest.gameengine.command.GameCommand;

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
