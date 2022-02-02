package model;

import java.util.Date;

/**
 * A Remedy
 */
public class Remedy {

    // REQUIRES: location and remedy exist, date is valid
    // EFFECTS: creates a new Remedy with location, remedy, and date
    public Remedy(String location, String remedy, Date date) {

    }

    // REQUIRES: location and remedy either exist or are null,
    //           Date is either valid or null
    // MODIFIES: this
    // EFFECTS: edits the Remedy at the given index in this log with the provided fields.
    //          "invalid" inputs to fields are not edited:
    //          null objects disregarded.
    public void editRemedyByIndex(int index, String location, String remedy, Date date) {

    }

    public String getLocation() {
        return null;
    }

    public String getRemedy() {
        return null;
    }

    public Date getDate() {
        return null;
    }
}
