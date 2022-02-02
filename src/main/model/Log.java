package model;

import java.util.List;

public interface Log {

    // EFFECTS: show all log entries logged and their index
    String viewAllLogs();

    // REQUIRES: start and end are valid dates such that:
    //           start is before end, and
    //           end is before or equal to today
    // EFFECTS: show info on the log entries within the given date bounds, empty panel if none
    String viewLogRange(String start, String end);

    // REQUIRES: index >= 0
    // MODIFIES: this
    // EFFECTS: removes the log entry at the given index from this log.
    //          all log entries after index are shifted down one index
    void delete(int index);

}
