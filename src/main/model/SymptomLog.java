package model;

import java.util.ArrayList;

/**
 * A collection of Symptoms added by the user
 */
// TODO: change date type
// TODO: change to abstract class!
public class SymptomLog extends Log {

    // EFFECTS: Creates an empty collection of Symptoms (a log)
    public SymptomLog() {
        log = new ArrayList<Entry>();
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
        int score = sym.getScore();

        String finalLocation;
        String finalSensation;
        int finalSeverity;
        int finalDuration;
        String finalDate;
        int finalScore;

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
}
