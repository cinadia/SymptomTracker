package model;

import model.entries.Remedy;
import model.entries.Symptom;
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
    //protected List<Log> history;
    protected Log symLog;
    protected Log remLog;

    // default constructor
    // TODO: why do I need default constructor? isn't it automatically provided?
    public LogHistory() {
        symLog = new SymptomLog();
        remLog = new RemedyLog();
    }

    // EFFECTS: constructs LogHistory with a name and an empty symptom and remedy log
    public LogHistory(String name) {
        this.name = name;
        symLog = new SymptomLog();
        remLog = new RemedyLog();
    }

    // MODIFIES: this
    // EFFECTS: adds new entry to the symptom log in this log history
    public void addSymptom(Symptom sym) {
        symLog.add(sym);
    }

    // MODIFIES: this
    // EFFECTS: adds new entry to the remedy log in this log history
    public void addRemedy(Remedy rem) {
        remLog.add(rem);
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
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("symptom log", logsToJson(symLog));
        json.put("remedy log", logsToJson(remLog));
        return json;

    }

    // EFFECTS: returns specified log in this log history as a JSON array
    protected JSONArray logsToJson(Log log) {
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(log.toJson());
        //jsonArray.put(rLog.toJson());

//        for (Log l : history) {
//            for (Entry e : l.getLog()) {
//                jsonArray.put(t.toJson());
//            }
//
//        }

        return jsonArray;
    }

    // EFFECTS: returns number of entries in the symptom log of this log history
    public int numSymptoms() {
        return symLog.getLog().size();
    }

    // EFFECTS: returns number of entries in the remedy log of this log history
    public int numRemedies() {
        return remLog.getLog().size();
    }

    public String getName() {
        return name;
    }
}