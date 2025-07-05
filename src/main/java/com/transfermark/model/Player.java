package com.transfermark.model;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Player {

    public static final int MAX_VALUES = 7;
    private static final String LINE_SEPARATOR = ";";

    private final String id;
    private final String name;
    private final String position;
    private final List<Injury> injuries;
    private final List<Integer> values;

    public Player(String id, String name, String position, List<Injury> injuries, List<Integer> values) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.injuries = injuries;
        this.values = values;
    }


    public void addInjury(Injury injury) {
        this.injuries.add(injury);
    }

    public void addValues(Integer value) {
        this.values.add(value);
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String position() {return position;}

    public List<Injury> injuries() {
        return injuries;
    }

    public List<Integer> values() {
        return values;
    }

    public void save(File file, String line){
        String playerLine = statistics(line);
        injuries.forEach(injury -> injury.saveOnDisk(file, playerLine));
    }

    private String statistics(String line) {
        return new StringBuilder()
                .append(line).append(LINE_SEPARATOR)
                .append(name).append(LINE_SEPARATOR)
                .append(id).append(LINE_SEPARATOR)
                .append(position).append(LINE_SEPARATOR)
                .append(valuesString()).append(LINE_SEPARATOR)
                .toString();
    }

    private String valuesString() {
        return Stream.concat(
                        values.stream().map(String::valueOf),
                        Stream.generate(() -> "").limit(7)
                )
                .limit(7)
                .collect(Collectors.joining(LINE_SEPARATOR));
    }
}
