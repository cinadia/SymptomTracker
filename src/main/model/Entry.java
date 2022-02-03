package model;

/**
 * Represents an entry in a log.
 */
public abstract class Entry {

    protected String location;
    protected String date;

    public String getLocation() {
        return this.location;
    }

    public String getDate() {
        return this.date;
    }

    abstract int getSeverity();

    abstract int getDuration();

    abstract String getSensation();

    abstract String getRemedy();


}
