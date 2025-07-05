package com.transfermark.model;

import java.io.File;
import java.util.List;

public class League {

    private static final String FILE_EXTENSION = ".tsv";

    private final String id;
    private final String name;
    private final List<Club> clubs;

    public League(String id, String name, List<Club> clubs) {
        this.id = id;
        this.name = name;
        this.clubs = clubs;
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public List<Club> clubs() {
        return clubs;
    }

    public void addClub(Club club){
        clubs.add(club);
    }

    public void save(File path){
        File file = new File(path + File.separator + id + FILE_EXTENSION);
        clubs.forEach(club -> club.save(file));
    }
}
