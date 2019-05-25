package me.brioschi.acompanytest.command;

import me.brioschi.acompanytest.world.WorldViewDTO;

public class CommandResponseDTO {

    private final WorldViewDTO visibleWorld;
    private final CommandResultMessage commandResultMessage;

    public CommandResponseDTO(WorldViewDTO visibleWorld) {
        this(visibleWorld, null);
    }

    public CommandResponseDTO(CommandResultMessage invalidDirection) {
        this(null, invalidDirection);
    }

    public CommandResponseDTO(
            WorldViewDTO visibleWorld,
            CommandResultMessage commandResultMessage
    ) {
        this.visibleWorld = visibleWorld;
        this.commandResultMessage = commandResultMessage;
    }

    public WorldViewDTO getVisibleWorld() {
        return visibleWorld;
    }

    public CommandResultMessage getCommandResultMessage() {
        return commandResultMessage;
    }

}
