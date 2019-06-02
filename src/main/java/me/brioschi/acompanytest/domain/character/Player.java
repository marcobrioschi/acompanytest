package me.brioschi.acompanytest.domain.character;

import me.brioschi.acompanytest.domain.monster.Experience;
import me.brioschi.acompanytest.domain.world.Position;

public class Player {

    private String name;
    private Position currentPosition;
    private Experience currentExperience;

    public Player(
            String name,
            Position currentPosition,
            Experience currentExperience) {
        this.name = name;
        this.currentPosition = currentPosition;
        this.currentExperience = currentExperience;
    }

    public String getName() {
        return name;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Position currentPosition) {
        this.currentPosition = currentPosition;
    }

    public Experience getCurrentExperience() {
        return currentExperience;
    }

    public void increaseExperience(Experience pExperience) {
        this.currentExperience = currentExperience.increase(pExperience);
    }

    public void decreaseExperience(Experience pExperience) {
        this.currentExperience = currentExperience.decrease(pExperience);
    }

}
