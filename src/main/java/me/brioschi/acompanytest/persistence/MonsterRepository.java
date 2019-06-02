package me.brioschi.acompanytest.persistence;

import me.brioschi.acompanytest.domain.monster.Experience;
import me.brioschi.acompanytest.domain.monster.Monster;
import me.brioschi.acompanytest.domain.monster.MonsterId;

import java.util.Hashtable;
import java.util.Map;

public class MonsterRepository {

    private Map<MonsterId, Monster> monsters = new Hashtable<>();

    public Monster load(MonsterId monsterId) {
        return monsters.get(monsterId);
    }

    public void save(Monster monster) {
        monsters.put(monster.getMonsterId(), monster);
    }

}
