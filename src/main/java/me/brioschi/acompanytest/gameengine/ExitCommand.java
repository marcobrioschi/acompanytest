package me.brioschi.acompanytest.gameengine;

import me.brioschi.acompanytest.command.CommandResponseDTO;
import me.brioschi.acompanytest.command.CommandResultMessage;
import me.brioschi.acompanytest.command.GameCommand;

public class ExitCommand extends GameCommand {

    @Override
    public CommandResponseDTO execute() {
        return new CommandResponseDTO(
                CommandResultMessage.HAVE_A_NICE_DAY
        );
    }

}
