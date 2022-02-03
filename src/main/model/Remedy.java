package model;

/**
 * A Remedy
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

}
