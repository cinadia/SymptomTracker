package model.logs;

import model.entries.Entry;
import model.logging.Event;
import model.logging.EventLog;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a collection of entries.
 */
public abstract class Log implements Writable {

    protected List<Entry> log;

    // EFFECTS: Creates an empty collection of Entries with no name
    public Log() {
        log  = new ArrayList<Entry>();
    }

    // MODIFIES: this
    // EFFECTS: adds an Entry to this log
    public void add(Entry e) {
        log.add(e);
    }

    // REQUIRES: index >= 0
    // MODIFIES: this
    // EFFECTS: replaces the Entry at the given index in this log with e
    public abstract void editLogByIndex(int index, Entry e);

    // REQUIRES: index >= 0
    // MODIFIES: this
    // EFFECTS: removes the log entry at the given index from this log.
    //          all log entries after index are shifted down one index
    public void delete(int index) {
        Entry removed = log.remove(index);
    }

    public List<Entry> getLog() {
        return log;
    }

    @Override
    // EFFECTS: returns this RemedyLog as JSON object
    public abstract JSONObject toJson();

    // EFFECTS: returns entries in this log as a JSON array
    public JSONArray entriesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Entry e : log) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }





}
