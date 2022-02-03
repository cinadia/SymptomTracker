package model;

import java.util.Calendar;
//import java.util.Date;

/**
 * A Symptom
 */
public class Symptom implements Entry {

    private String location;
    private String sensation;
    private int severity; // between 1 and 5
    private int duration; // > 0
    private String date; // current format is "YYYY-MM-DD"

    // REQUIRES: location and sensation exist, severity in range [1, 5], date is valid, duration > 0
    // EFFECTS: Creates a new Symptom with location, sensation, severity, duration (minutes), and date
    public Symptom(String location, String sensation, int severity, int duration, String date) {
        this.location = location;
        this.sensation = sensation;
        this.severity = severity;
        this.duration = duration;
        this.date = date;
    }

    @Override
    public String getLocation() {
        return this.location;
    }

    public String getSensation() {
        return this.sensation;
    }

    public int getSeverity() {
        return this.severity;
    }

    public int getDuration() {
        return this.duration;
    }

    @Override
    public String getDate() {
        return this.date;
    }

    public String getRemedy() {
        return null;
    }


    /*
    // REQUIRES: location and sensation either exist or are null,
    //           date is either valid or null,
    //           severity is either 1-5 or -1,
    //           duration is either a value in minutes > 0, or -1
    // MODIFIES: this
    // EFFECTS: edits this Symptom with the provided fields.
    //          "invalid" inputs to fields are not edited:
    //          null objects and ints of -1 are disregarded.
    public Symptom editSymptom(String location, String sensation, int severity, int duration, String date) {
        return null;
    }
    */
}
