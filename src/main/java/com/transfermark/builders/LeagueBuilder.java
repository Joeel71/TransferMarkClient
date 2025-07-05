package com.transfermark.builders;

import com.transfermark.model.Club;
import com.transfermark.model.League;

import java.util.ArrayList;
import java.util.List;

public class LeagueBuilder {

    private String id;
    private String name;
    private final List<Club> clubs;

    public LeagueBuilder() {
        this.clubs = new ArrayList<>();
    }

    public LeagueBuilder id(String id){
        this.id = id;
        return this;
    }

    public LeagueBuilder name(String name){
        this.name = name;
        return this;
    }

    public LeagueBuilder addClub(Club club){
        clubs.add(club);
        return this;
    }

    public League build(){
        return new League(id, name, clubs);
    }
}
