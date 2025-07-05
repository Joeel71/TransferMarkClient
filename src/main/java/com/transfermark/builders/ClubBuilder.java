package com.transfermark.builders;

import com.transfermark.model.Club;
import com.transfermark.model.Player;

import java.util.ArrayList;
import java.util.List;

public class ClubBuilder {

    private String id;
    private String name;
    private List<Player> players;

    public ClubBuilder() {
        this.players = new ArrayList<>();
    }

    public ClubBuilder id(String id) {
        this.id = id;
        return this;
    }

    public ClubBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ClubBuilder addPlayers(Player player) {
        this.players.add(player);
        return this;
    }

    public ClubBuilder players(List<Player> players){
        this.players = players;
        return this;
    }

    public String id(){
        return id;
    }

    public Club build(){
        return new Club(id, name, players);
    }
}
