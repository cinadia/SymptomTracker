package model.entries;

import org.json.JSONObject;
import persistence.Writable;

/**
 * Represents an entry in a log.
 */
public abstract class Entry implements Writable {

    protected String location;
    protected String date;

    public String getLocation() {
        return this.location;
    }

    public String getDate() {
        return this.date;
    }

    public abstract int getSeverity();

    public abstract int getDuration();

    public abstract String getSensation();

    public abstract String getRemedy();

    public abstract int getScore();

    // EFFECTS: returns this Entry as JSON object
    public abstract JSONObject toJson();
}
