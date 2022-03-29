package model.logs;

import model.entries.Entry;
import model.entries.Remedy;
import org.json.JSONObject;

/**
 * A collection of Remedies added by the user
 */
public class RemedyLog extends Log {

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

    @Override
    // EFFECTS: returns this RemedyLog as JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("remedy log", entriesToJson());
        return json;
    }
}
