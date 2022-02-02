package model;

import java.util.Date;
import java.util.List;

/**
 * A collection of Remedies added by the user
 */
public class RemedyLog {

    // EFFECTS: Creates an empty collection of Remedies (a log)
    public RemedyLog() {

    }

    // REQUIRES: location and remedy exist, date is valid
    // MODIFIES: this
    // EFFECTS: adds a Remedy to this log with location, remedy, and date
    public void addRemedy(String location, String remedy, Date date) {

    }

    // EFFECTS: show all Remedies logged and their index
    public List<Remedy> viewAllRemedies() {
        return null;
    }

    // REQUIRES: start and end are valid dates such that start is before end, and end is before or equal to today
    // EFFECTS: show info on the Remedies logged within the given date bounds, empty panel if none
    public List<Remedy> viewSelectRemedies(Date start, Date end) {
        return null;
    }

    // REQUIRES: index >= 0,
    //           location and remedy either exist or are null,
    //           Date is either valid or null
    // MODIFIES: this
    // EFFECTS: edits the Remedy at the given index in this log with the provided fields.
    //          "invalid" inputs to fields are not edited:
    //          null objects disregarded.
    public void editRemedyByIndex(int index, String location, String remedy, Date date) {
        // get index
        // pass arguments to editRemedy in Remedy class
    }

    // REQUIRES: index >= 0
    // MODIFIES: this
    // EFFECTS: removes the Remedy at the given index from this log. all remedies after index are shifted down
    //          one index
    public void deleteRemedy(int index){

    }

    public Symptom getRemedy() {
        return null;
    }


}
