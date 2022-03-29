package model;

import model.entries.Remedy;
import model.entries.Symptom;
import model.logging.Event;
import model.logging.EventLog;
import model.logs.Log;
import model.logs.RemedyLog;
import model.logs.SymptomLog;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

/**
 * Representation of log history, with a symptom and remedy log
 */
public class LogHistory implements Writable {
    protected String name;
    protected Log symLog;
    protected Log remLog;

    // EFFECTS: constructs LogHistory with a name and an empty symptom and remedy log
    public LogHistory(String name) {
        super();
        this.name = name;
        this.symLog = new SymptomLog();
        this.remLog = new RemedyLog();
    }

    // EFFECTS: constructs LogHistory with a name and given symptom and remedy logs
    public LogHistory(String name, Log symLog, Log remLog) {
        super();
        this.name = name;
        this.symLog = symLog;
        this.remLog = remLog;
    }

    // MODIFIES: this
    // EFFECTS: adds new entry to the symptom log in this log history
    public void addSymptom(Symptom sym) {
        symLog.add(sym);
        EventLog.getInstance().logEvent(new Event("A symptom entry has been added. "));
    }

    // MODIFIES: this
    // EFFECTS: adds new entry to the remedy log in this log history
    public void addRemedy(Remedy rem) {
        remLog.add(rem);
        EventLog.getInstance().logEvent(new Event("A remedy entry has been added. "));
    }

    // MODIFIES: this
    // EFFECTS: deletes symptom entry at index from this log history
    public void deleteSymptom(int index) {
        symLog.delete(index);
        EventLog.getInstance().logEvent(new Event("Symptom entry " + ++index + " has been deleted. "));
    }

    // MODIFIES: this
    // EFFECTS: deletes symptom entry at index from this log history
    public void deleteRemedy(int index) {
        remLog.delete(index);
        EventLog.getInstance().logEvent(new Event("Remedy entry " + ++index + " has been deleted. "));
    }

    // EFFECTS: returns the symptom log in this log history
    public Log getSymptomLogs() {
        return symLog;
    }

    // EFFECTS: returns the remedy log in this log history
    public Log getRemedyLogs() {
        return remLog;
    }

    @Override
    // EFFECTS: return this as JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("symptom log", logsToJson(symLog));
        json.put("remedy log", logsToJson(remLog));
        return json;
    }

    // EFFECTS: returns specified log in this log history as a JSON array of its entries
    protected JSONArray logsToJson(Log log) {
        JSONArray jsonArray = log.entriesToJson();
        return jsonArray;
    }

    // EFFECTS: returns the EventLog history
    public void getEventLog() {
        for (Event next : EventLog.getInstance()) {
            System.out.println(next.toString());
        }
       // return ret;
    }

    // EFFECTS: returns number of entries in the symptom log of this log history
    public int numSymptoms() {
        return symLog.getLog().size();
    }

    // EFFECTS: returns number of entries in the remedy log of this log history
    public int numRemedies() {
        return remLog.getLog().size();
    }

    // EFFECTS: returns name of this log history
    public String getName() {
        return name;
    }
}
