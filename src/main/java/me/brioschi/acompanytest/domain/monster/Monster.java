package me.brioschi.acompanytest.domain.monster;

import java.util.concurrent.ThreadLocalRandom;

public class Monster {

    private MonsterId monsterId;
    private String name;
    private String description;
    private Experience experience;
    private boolean isLooser;

    public Monster(MonsterId monsterId, String name, String description, Experience experience) {
        this(monsterId, name, description, experience, false);
    }

    public Monster(MonsterId monsterId, String name, String description, Experience experience, boolean isLooser) {
        this.monsterId = monsterId;
        this.name = name;
        this.description = description;
        this.experience = experience;
        this.isLooser = isLooser;
    }

    public MonsterId getMonsterId() {
        return monsterId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Experience getExperience() {
        return experience;
    }

    public boolean fight(Experience playerExperience) {
        boolean playerWin;
        if (!isLooser) {
            // The probability to win is proportional to the experience of the fighters
            int totalRange = this.experience.getExperience() + playerExperience.getExperience();
            int randomInt = ThreadLocalRandom.current().nextInt(1, totalRange + 1);
            playerWin = randomInt <= playerExperience.getExperience();
        } else {
            playerWin = true;
        }
        return playerWin;
    }

}
