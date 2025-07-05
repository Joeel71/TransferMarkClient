package com.transfermark.model;

import java.io.File;
import java.util.List;

public class Club {

    private final String id;
    private final String name;
    private final List<Player> players;

    public Club(String id, String name, List<Player> players) {
        this.id = id;
        this.name = name;
        this.players = players;
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public List<Player> players() {
        return players;
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public void save(File file){
        players.forEach(player -> player.save(file, name));
    }
}
