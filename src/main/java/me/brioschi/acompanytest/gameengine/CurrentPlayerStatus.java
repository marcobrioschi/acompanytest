package me.brioschi.acompanytest.gameengine;

import me.brioschi.acompanytest.world.Position;

public class CurrentPlayerStatus {

    private Position currentPosition;

    public CurrentPlayerStatus(Position currentPosition) {
        this.currentPosition = currentPosition;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Position currentPosition) {
        this.currentPosition = currentPosition;
    }

}
