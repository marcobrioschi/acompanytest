package me.brioschi.acompanytest.character;

import me.brioschi.acompanytest.command.CommandResponseDTO;
import me.brioschi.acompanytest.command.CommandResultMessage;
import me.brioschi.acompanytest.command.GameCommand;
import me.brioschi.acompanytest.monster.Experience;
import me.brioschi.acompanytest.world.Position;

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
        return new CommandResponseDTO (
                new Player(
                        playerName,
                        new Position(0, 0),
                        Experience.CALLOW
                ),
                CommandResultMessage.PLAYER_CREATED
        );
    }

}
