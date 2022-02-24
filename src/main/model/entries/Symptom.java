package model.entries;


import model.entries.Entry;
import org.json.JSONObject;

/**
 * A Symptom
 */
public class Symptom extends Entry {

    private String sensation;
    private int severity; // integer between 1 and 5
    private int duration; // integer > 0
    private int score; // severity * duration

    // REQUIRES: location and sensation exist, severity as an integer in range [1, 5],
    //           date is valid, duration as an integer > 0
    // EFFECTS: Creates a new Symptom with location, sensation, severity, duration (minutes), and date
    public Symptom(String location, String sensation, int severity, int duration, String date) {
        this.location = location;
        this.sensation = sensation;
        this.severity = severity;
        this.duration = duration;
        this.date = date;
        this.score = severity * duration;
    }

    @Override
    // EFFECTS: returns this Symptom as JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("location", location);
        json.put("sensation", sensation);
        json.put("severity", severity);
        json.put("duration", duration);
        json.put("score", score);
        json.put("date", date);

        return json;
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

    public int getScore() {
        return this.score;
    }

    public String getRemedy() {
        return null;
    }

}
