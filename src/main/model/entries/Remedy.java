package model.entries;

import model.entries.Entry;
import org.json.JSONObject;

/**
 * Represents a Remedy
 */
public class Remedy extends Entry {

    private String remedy;

    // REQUIRES: location and remedy exist, date is valid
    // EFFECTS: creates a new Remedy with location, remedy, and date
    public Remedy(String location, String remedy, String date) {
        this.location = location;
        this.remedy = remedy;
        this.date = date;
    }

    @Override
    // EFFECTS: returns this Remedy as JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("location", location);
        json.put("remedy", remedy);
        json.put("date", date);

        return json;
    }

    @Override
    public String getRemedy() {
        return this.remedy;
    }

    @Override
    public int getSeverity() {
        return -1;
    }

    @Override
    public int getDuration() {
        return -1;
    }

    @Override
    public String getSensation() {
        return null;
    }

    @Override
    public int getScore() {
        return -1;
    }


}
