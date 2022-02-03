package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.time.*;

/**
 * A collection of Symptoms added by the user
 */
// TODO: change date type
// TODO: change to abstract class!
public class SymptomLog implements Log {

    private ArrayList<Entry> log;

    // EFFECTS: Creates an empty collection of Symptoms (a log)
    public SymptomLog() {
        log  = new ArrayList<Entry>();
    }

    // MODIFIES: this
    // EFFECTS: adds Symptom sym to this log
    @Override
    public void add(Entry sym) {
        log.add(sym);
    }

    // REQUIRES: index >= 0
    // MODIFIES: this
    // EFFECTS: replaces the Symptom at the given index in this log with sym
    @Override //  TODO: can I use @SuppressWarnings("methodlength") here?
    public void editLogByIndex(int index, Entry sym) {
        Entry editing = log.get(index);

        String location = sym.getLocation();
        String sensation = sym.getSensation();
        int severity = sym.getSeverity();
        int duration = sym.getDuration();
        String date = sym.getDate();

        String finalLocation;
        String finalSensation;
        int finalSeverity;
        int finalDuration;
        String finalDate;

        if (location == null) {
            finalLocation = editing.getLocation(); // previous location
        } else {
            finalLocation = location;
        }

        if (sensation == null) {
            finalSensation = editing.getSensation(); // previous sensation
        } else {
            finalSensation = sensation;
        }

        if (severity == -1) {
            finalSeverity = editing.getSeverity();
        } else {
            finalSeverity = severity;
        }

        if (duration == -1) {
            finalDuration = editing.getDuration();
        } else {
            finalDuration = duration;
        }

        if (date == null) {
            finalDate = editing.getDate();
        } else {
            finalDate = date;
        }

        log.set(index, new Symptom(finalLocation, finalSensation, finalSeverity, finalDuration, finalDate));
    }

    // REQUIRES: index >= 0
    // MODIFIES: this
    // EFFECTS: removes the Symptom at the given index from this log.
    //          all log entries after index are shifted down one index
    @Override
    public void delete(int index) {
        log.remove(index);
    }

    public List<Entry> getLog() {
        return log;
    }

    /*
    // REQUIRES: location and sensation exist, severity in range [1, 5], date is valid, duration > 0
    // MODIFIES: this
    // EFFECTS: adds a Symptom to this log with location, sensation, severity, duration (minutes), and date
    public void addSymptom(String location, String sensation, int severity, int duration, String date) {

    }
    */

    /*
    // REQUIRES: index >= 0,
    //           location and sensation either exist or are null,
    //           date is either valid or null,
    //           severity is either 1-5 or -1,
    //           duration is either a value in minutes > 0, or -1
    // MODIFIES: this
    // EFFECTS: edits the Symptom at the given index in this log with the provided fields.
    //          "invalid" inputs to fields are not edited:
    //          null objects and ints of -1 are disregarded.
    public void editSymptomByIndex(int index, String location, String sensation, int severity, int duration,
                                   String date) {
        // get index
        // pass arguments to editSymptom in Symptom class
    }
    */

    /*
    // EFFECTS: show all Symptoms logged and their index
    @Override
    public String viewAllLogs() {
        return null;
    }

    // REQUIRES: start and end are valid dates such that
    //           start is before end, and
    //           end is before or equal to today
    // EFFECTS: show info on the Symptoms logged within the given date bounds, empty panel if none
    @Override
    public String viewLogRange(String start, String end) {
        return null;
    }
     */
}
