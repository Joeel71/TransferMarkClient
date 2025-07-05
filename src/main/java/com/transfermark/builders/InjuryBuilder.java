package com.transfermark.builders;

import com.transfermark.model.Injury;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class InjuryBuilder {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    private String injuryName;
    private Date from;
    private Date to;
    private int daysMissed;
    private int gamesMissed;


    public InjuryBuilder injuryName(String injuryName) {
        this.injuryName = injuryName;
        return this;
    }

    public InjuryBuilder from(String from) {
        this.from = castDate(from);
        return this;
    }

    public InjuryBuilder to(String to) {
        this.to = castDate(to);
        return this;
    }

    public InjuryBuilder daysMissed(String daysMissed) {
        this.daysMissed = Integer.parseInt(daysMissed);
        return this;
    }

    public InjuryBuilder gamesMissed(String gamesMissed) {
        this.gamesMissed = Integer.parseInt(gamesMissed);
        return this;
    }

    public Injury build() {
        return new Injury(injuryName, from, to, daysMissed, gamesMissed);
    }

    private Date castDate(String date) {
        try {
            return date != null ? DATE_FORMAT.parse(date) : null;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
