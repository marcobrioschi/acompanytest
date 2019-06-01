package me.brioschi.acompanytest.monster;

import me.brioschi.acompanytest.base.AValueObject;

import java.security.InvalidParameterException;

public class Experience extends AValueObject {

    private final Integer experience;

    public Experience(int experience) {
        super(Experience.class);
        if (experience >= CALLOW_VALUE) {
            this.experience = experience;
        } else {
            throw new InvalidParameterException("The experience must not be less than callow [" + experience + "]");
        }
    }

    public int getExperience() {
        return experience;
    }

    @Override
    protected int valueObjectHashCode() {
        return experience.hashCode();
    }

    public Experience increase(Experience param) {
        return new Experience(this.experience + param.getExperience());
    }

    public Experience decrease(Experience param) {
        if (this.experience - param.getExperience() >= CALLOW_VALUE) {
            return new Experience(this.experience - param.getExperience());
        } else {
            return CALLOW;
        }
    }

    public final static int CALLOW_VALUE = 10;
    public final static Experience CALLOW = new Experience(CALLOW_VALUE);
    
}
