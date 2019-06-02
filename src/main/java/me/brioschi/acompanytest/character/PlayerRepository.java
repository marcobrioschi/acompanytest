package me.brioschi.acompanytest.character;

import java.util.Hashtable;
import java.util.Map;

public class PlayerRepository {

    Map<String, Player> players = new Hashtable<>();

    public Player load(String playerName) {
        return players.get(playerName);
    }

    public void save(Player player) {
        players.put(player.getName(), player);
    }

}
