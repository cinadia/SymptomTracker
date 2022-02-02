package model;

import java.util.Date;
import java.util.List;

/**
 * A collection of Symptoms added by the user
 */
public class SymptomLog {

    // EFFECTS: Creates an empty collection of Symptoms (a log)
    public SymptomLog() {

    }

    // REQUIRES: location and sensation exist, severity in range [1, 5], date is valid, duration > 0
    // MODIFIES: this
    // EFFECTS: adds a Symptom to this log with location, sensation, severity, duration (minutes), and date
    public void addSymptom(String location, String sensation, int severity, int duration, Date date) {

    }

    // EFFECTS: show all Symptoms logged and their index
    public List<Symptom> viewAllSymptoms() {
        return null;
    }

    // REQUIRES: start and end are valid dates such that start is before end, and end is before or equal to today
    // EFFECTS: show info on the Symptoms logged within the given date bounds, empty panel if none
    public List<Symptom> viewSelectSymptoms(Date start, Date end) {
        return null;
    }

    // REQUIRES: index >= 0,
    //           location and sensation either exist or are null,
    //           Date is either valid or null,
    //           severity is either 1-5 or -1,
    //           duration is either a value in minutes > 0, or -1
    // MODIFIES: this
    // EFFECTS: edits the Symptom at the given index in this log with the provided fields.
    //          "invalid" inputs to fields are not edited:
    //          null objects and ints of -1 are disregarded.
    public void editSymptomByIndex(int index, String location, String sensation, int severity, int duration, Date date) {
        // get index
        // pass arguments to editSymptom in Symptom class
    }

    // REQUIRES: index >= 0
    // MODIFIES: this
    // EFFECTS: removes the Symptom at the given index from this log. all symptoms after index are shifted down
    //          one index
    public void deleteSymptom(int index){

    }

    public Symptom getSymptom() {
        return null;
    }
}
