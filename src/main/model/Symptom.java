package model;

import java.util.Date;

/**
 * A Symptom
 */
public class Symptom {

    // REQUIRES: location and sensation exist, severity in range [1, 5], date is valid, duration > 0
    // EFFECTS: Creates a new Symptom with location, sensation, severity, duration (minutes), and date
    public Symptom(String location, String sensation, int severity, int duration, Date date) {

    }

    // REQUIRES: location and sensation either exist or are null,
    //           Date is either valid or null,
    //           severity is either 1-5 or -1,
    //           duration is either a value in minutes > 0, or -1
    // MODIFIES: this
    // EFFECTS: edits this Symptom with the provided fields.
    //          "invalid" inputs to fields are not edited:
    //          null objects and ints of -1 are disregarded.
    public Symptom editSymptom() {
        return null;
    }

    public String getLocation() {
        return null;
    }

    public String getSensation() {
        return null;
    }

    public int getSeverity() {
        return 0; // an invalid value, and also an invalid *invalid* value (-1 is true 'invalid')
    }

    public int getDuration() {
        return 0;
    }

    public Date getDate() {
        return null;
    }


}
