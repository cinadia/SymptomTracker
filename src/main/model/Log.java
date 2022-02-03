package model;

import java.util.List;

/**
 * Represents a collection of entries.
 */
public interface Log {

    // MODIFIES: this
    // EFFECTS: adds an Entry to this log
    void add(Entry e);

    // REQUIRES: index >= 0
    // MODIFIES: this
    // EFFECTS: replaces the Entry at the given index in this log with e
    void editLogByIndex(int index, Entry e);

    // REQUIRES: index >= 0
    // MODIFIES: this
    // EFFECTS: removes the log entry at the given index from this log.
    //          all log entries after index are shifted down one index
    void delete(int index);

    List<Entry> getLog();

}
