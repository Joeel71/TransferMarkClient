package com.transfermark.builders;

import com.transfermark.model.Injury;
import com.transfermark.model.Player;
import org.apache.commons.text.CaseUtils;

import java.util.ArrayList;
import java.util.List;

public class PlayerBuilder {

    private String id;
    private String name;
    private String position;
    private List<Injury> injuries;
    private List<Integer> values;

    public PlayerBuilder() {
        this.injuries = new ArrayList<>();
        this.values = new ArrayList<>();
    }

    public PlayerBuilder addInjury(Injury injury){
        injuries.add(injury);
        return this;
    }

    public PlayerBuilder injury(List<Injury> injuries){
        this.injuries = injuries;
        return this;
    }

    public PlayerBuilder addValue(Integer value){
        values.add(value);
        return this;
    }

    public PlayerBuilder value(List<Integer> values){
        this.values = values;
        return this;
    }

    public PlayerBuilder id(String id) {
        this.id = id;
        return this;
    }

    public PlayerBuilder name(String name) {
        this.name = CaseUtils.toCamelCase(name, true, ' ');
        return this;
    }

    public PlayerBuilder position(String position){
        this.position = position;
        return this;
    }

    public String id(){
        return id;
    }

    public Player build() {
        return new Player(id, name, position, injuries, values);
    }
}
