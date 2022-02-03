package model;

import java.util.Calendar;

/**
 * A Remedy
 */
public class Remedy extends Entry {

    //private String location;
    private String remedy;
   // private String date;

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

    /*
    // REQUIRES: location and remedy either exist or are null,
    //           date is either valid or null
    // MODIFIES: this
    // EFFECTS: edits the Remedy at the given index in this log with the provided fields.
    //          "invalid" inputs to fields are not edited:
    //          null objects disregarded.
    public void editRemedy(String location, String remedy, String date) {

    }
    */
}
