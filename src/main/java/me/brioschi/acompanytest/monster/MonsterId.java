package me.brioschi.acompanytest.monster;

import me.brioschi.acompanytest.base.AValueObject;

public class MonsterId extends AValueObject {

    private final String id;

    public MonsterId(String id) {
        super(MonsterId.class);
        this.id = id;
    }

    @Override
    protected int valueObjectHashCode() {
        return id.hashCode();
    }

}
