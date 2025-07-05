package com.transfermark.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Injury {

    private static final String LINE_SEPARATOR = ";";

    private final String injuryName;
    private final Date from;
    private final Date to;
    private final int daysMissed;

    public Injury(String injuryName, Date from, Date to, int daysMissed, int gamesMissed) {
        this.injuryName = injuryName;
        this.from = from;
        this.to = to;
        this.daysMissed = daysMissed;
        this.gamesMissed = gamesMissed;
    }

    private final int gamesMissed;

    public String injuryName() {
        return injuryName;
    }

    public Date from() {
        return from;
    }

    public Date to() {
        return to;
    }

    public int daysMissed() {
        return daysMissed;
    }

    public int gamesMissed() {
        return gamesMissed;
    }

    public void saveOnDisk(File file, String line){
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(injuryLine(line) + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String injuryLine(String line) {
        return new StringBuilder()
                .append(line)
                .append(injuryName).append(LINE_SEPARATOR)
                .append(from).append(LINE_SEPARATOR)
                .append(to).append(LINE_SEPARATOR)
                .append(daysMissed).append(LINE_SEPARATOR)
                .append(gamesMissed)
                .toString();
    }
}
