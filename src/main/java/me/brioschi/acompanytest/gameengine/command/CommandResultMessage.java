package me.brioschi.acompanytest.gameengine.command;

public enum CommandResultMessage {

    INVALID_DIRECTION("You tried an invalid direction"),
    YOU_WIN_THE_FIGHT("You won the monster!"),
    YOU_LOSE_THE_FIGHT("You lost the fight ..."),
    NO_MONSTER_MATCH("I can't find the monster you've named"),
    PLAYER_CREATED("The new player was created"),
    PLAYER_LOADED("The player was loaded"),
    PLAYER_SAVED("The player was saved"),
    INVALID_PLAYER_NAME("The player name isn't valid"),
    HAVE_A_NICE_DAY("Have a nice day :-)")
    ;

    private final String description;

    private CommandResultMessage(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
