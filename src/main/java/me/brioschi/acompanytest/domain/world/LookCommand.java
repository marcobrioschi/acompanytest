package me.brioschi.acompanytest.domain.world;

import me.brioschi.acompanytest.gameengine.command.CommandResponseDTO;
import me.brioschi.acompanytest.gameengine.command.GameCommand;

public class LookCommand extends GameCommand {

    @Override
    public CommandResponseDTO execute() {
        return new CommandResponseDTO(
                worldMap.getPlayerVisibleWorld(
                    currentPlayer.getCurrentPosition()
                )
        );
    }

}
