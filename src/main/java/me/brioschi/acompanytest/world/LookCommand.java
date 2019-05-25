package me.brioschi.acompanytest.world;

import me.brioschi.acompanytest.command.CommandResponseDTO;
import me.brioschi.acompanytest.command.GameCommand;

public class LookCommand extends GameCommand {

    @Override
    public CommandResponseDTO execute() {
        return new CommandResponseDTO(
                worldMap.getPlayerVisibleWorld(
                    currentPlayerStatus.getCurrentPosition()
                )
        );
    }

}
