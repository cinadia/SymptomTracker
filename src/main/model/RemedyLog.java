package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A collection of Remedies added by the user
 */
public class RemedyLog implements Log {

    // EFFECTS: Creates an empty collection of Remedies (a log)
    public RemedyLog() {

    }

    // MODIFIES: this
    // EFFECTS: adds a Remedy to this log
    public void add(Entry rem) {

    }

    // REQUIRES: index >= 0
    // MODIFIES: this
    // EFFECTS: replaces the Symptom at the given index in this log with rem
    public void editLogByIndex(int index, Entry rem) {
        // get index
        // use series of if statements and fields of rem to make a new rem to set at the index
    }

    // REQUIRES: index >= 0
    // MODIFIES: this
    // EFFECTS: removes the Remedy at the given index from this log.
    //          all remedies after index are shifted down one index
    @Override
    public void delete(int index){

    }

    public List<Entry> getLog() {
        return new ArrayList<Entry>();
    }

     /*
    // REQUIRES: index >= 0,
    //           location and remedy either exist or are null,
    //           Date is either valid or null
    // MODIFIES: this
    // EFFECTS: edits the Remedy at the given index in this log with the provided fields.
    //          "invalid" inputs to fields are not edited:
    //          null objects disregarded.
    public void editRemedyByIndex(int index, String location, String remedy, String date) {
        // get index
        // pass arguments to editRemedy in Remedy class
    }
     */

    /*
    // EFFECTS: show all Remedies logged and their index
    @Override
    public String viewAllLogs() {
        return null;
    }

    // REQUIRES: start and end are valid dates such that:
    //           start is before end, and
    //           end is before or equal to today
    // EFFECTS: show info on the Remedies logged within the given date bounds, empty panel if none
    @Override
    public String viewLogRange(String start, String end) {
        return null;
    }
    */

}
