package me.brioschi.acompanytest.gameengine;

import me.brioschi.acompanytest.monster.Experience;
import me.brioschi.acompanytest.world.Position;

public class CurrentPlayerStatus {

    private Position currentPosition;
    private Experience currentExperience;

    public CurrentPlayerStatus(
            Position currentPosition,
            Experience currentExperience) {
        this.currentPosition = currentPosition;
        this.currentExperience = currentExperience;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public Experience getCurrentExperience() {
        return currentExperience;
    }

    public void setCurrentPosition(Position currentPosition) {
        this.currentPosition = currentPosition;
    }

    public void setCurrentExperience(Experience currentExperience) {
        this.currentExperience = currentExperience;
    }

    public void increaseExperience(Experience pExperience) {
        this.currentExperience = currentExperience.increase(pExperience);
    }

    public void decreaseExperience(Experience pExperience) {
        this.currentExperience = currentExperience.decrease(pExperience);
    }

}
