package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A collection of Remedies added by the user
 */
public class RemedyLog implements Log {

    private ArrayList<Entry> log;

    // EFFECTS: Creates an empty collection of Remedies (a log)
    public RemedyLog() {
        log  = new ArrayList<Entry>();

    }

    // MODIFIES: this
    // EFFECTS: adds a Remedy to this log
    @Override
    public void add(Entry rem) {
        log.add(rem);

    }

    // REQUIRES: index >= 0
    // MODIFIES: this
    // EFFECTS: replaces the Symptom at the given index in this log with rem
    @Override
    public void editLogByIndex(int index, Entry rem) {
        Entry editing = log.get(index);

        String location = rem.getLocation();
        String remedy = rem.getRemedy();
        String date = rem.getDate();

        String finalLocation;
        String finalRemedy;
        String finalDate;

        if (location == null) {
            finalLocation = editing.getLocation(); // previous location
        } else {
            finalLocation = location;
        }

        if (remedy == null) {
            finalRemedy = editing.getRemedy(); // previous sensation
        } else {
            finalRemedy = remedy;
        }

        if (date == null) {
            finalDate = editing.getDate();
        } else {
            finalDate = date;
        }

        log.set(index, new Remedy(finalLocation, finalRemedy, finalDate));

    }

    // REQUIRES: index >= 0
    // MODIFIES: this
    // EFFECTS: removes the Remedy at the given index from this log.
    //          all remedies after index are shifted down one index
    @Override
    public void delete(int index) {
        log.remove(index);
    }

    public List<Entry> getLog() {
        return log;
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
