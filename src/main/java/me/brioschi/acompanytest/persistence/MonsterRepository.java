package me.brioschi.acompanytest.persistence;

import me.brioschi.acompanytest.domain.monster.Experience;
import me.brioschi.acompanytest.domain.monster.Monster;
import me.brioschi.acompanytest.domain.monster.MonsterId;

import java.util.Hashtable;
import java.util.Map;

public class MonsterRepository {

    private Map<MonsterId, Monster> monsters;

    public MonsterRepository() {
        monsters = new Hashtable<>();
        monsters.put(
                new MonsterId("ms_0001"),
                new Monster(
                        new MonsterId("ms_0001"),
                        "The Guardian",
                        "The guardian is the child of Kabin",
                        Experience.CALLOW
                )
        );
    }

    public Monster load(MonsterId monsterId) {
        return monsters.get(monsterId);
    }

    public void save(Monster monster) {
        monsters.put(monster.getMonsterId(), monster);
    }

}
