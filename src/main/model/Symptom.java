package model;


/**
 * A Symptom
 */
public class Symptom extends Entry {

    private String sensation;
    private int severity; // between 1 and 5
    private int duration; // > 0

    // REQUIRES: location and sensation exist, severity in range [1, 5], date is valid, duration > 0
    // EFFECTS: Creates a new Symptom with location, sensation, severity, duration (minutes), and date
    public Symptom(String location, String sensation, int severity, int duration, String date) {
        this.location = location;
        this.sensation = sensation;
        this.severity = severity;
        this.duration = duration;
        this.date = date;
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

    public String getRemedy() {
        return null;
    }

}
