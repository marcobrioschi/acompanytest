package me.brioschi.acompanytest.gameengine.command;

import me.brioschi.acompanytest.domain.character.Player;
import me.brioschi.acompanytest.domain.world.WorldViewDTO;

public class CommandResponseDTO {

    private final WorldViewDTO visibleWorld;
    private final Player currentPlayer;
    private final CommandResultMessage commandResultMessage;

    public CommandResponseDTO(WorldViewDTO visibleWorld) {
        this(visibleWorld, null, null);
    }

    public CommandResponseDTO(CommandResultMessage commandResultMessage) {
        this(null, null, commandResultMessage);
    }

    public CommandResponseDTO(Player loadedPlayer, CommandResultMessage commandResultMessage) {
        this(null, loadedPlayer, commandResultMessage);
    }

    public CommandResponseDTO(
            WorldViewDTO visibleWorld,
            Player currentPlayer,
            CommandResultMessage commandResultMessage
    ) {
        this.visibleWorld = visibleWorld;
        this.currentPlayer = currentPlayer;
        this.commandResultMessage = commandResultMessage;
    }

    public WorldViewDTO getVisibleWorld() {
        return visibleWorld;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public CommandResultMessage getCommandResultMessage() {
        return commandResultMessage;
    }

}
