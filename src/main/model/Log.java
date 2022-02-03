package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a collection of entries.
 */
// TODO: change to abstract class!
public abstract class Log {

    protected ArrayList<Entry> log;

    // MODIFIES: this
    // EFFECTS: adds an Entry to this log
    public void add(Entry e) {
        log.add(e);
    }

    // REQUIRES: index >= 0
    // MODIFIES: this
    // EFFECTS: replaces the Entry at the given index in this log with e
    abstract void editLogByIndex(int index, Entry e);

    // REQUIRES: index >= 0
    // MODIFIES: this
    // EFFECTS: removes the log entry at the given index from this log.
    //          all log entries after index are shifted down one index
    public void delete(int index) {
        log.remove(index);
    }

    List<Entry> getLog() {
        return log;
    }

}
