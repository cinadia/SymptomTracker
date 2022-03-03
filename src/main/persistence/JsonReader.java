package persistence;

import model.LogHistory;
import model.entries.Entry;
import model.entries.Remedy;
import model.entries.Symptom;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Represents a reader that reads LogHistory from JSON data stored in file
 * Methods adapted from JsonReader class in
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 * unless stated otherwise
 */
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads LogHistory from file and returns it;
    // throws IOException if an error occurs reading data from file
    public LogHistory read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLogHistory(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private LogHistory parseLogHistory(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        LogHistory lh = new LogHistory(name);
        addSymptomLog(lh, jsonObject);
        addRemedyLog(lh, jsonObject);
        return lh;
    }

    // Original Method
    // MODIFIES: lh
    // EFFECTS: parses symptom log from JSON object and adds them to log history
    private void addSymptomLog(LogHistory lh, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("symptom log");
        for (Object json : jsonArray) {
            JSONObject nextEntry = (JSONObject) json;
            addSymptomEntry(lh, nextEntry);
        }
    }

    // Original Method
    // MODIFIES: lh
    // EFFECTS: parses remedy log from JSON object and adds them to log history
    private void addRemedyLog(LogHistory lh, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("remedy log");
        for (Object json : jsonArray) {
            JSONObject nextEntry = (JSONObject) json;
            addRemedyEntry(lh, nextEntry);
        }
    }

    // Original Method
    // MODIFIES: lh
    // EFFECTS: parses symptom entry from JSON object and adds it to workroom
    private void addSymptomEntry(LogHistory lh, JSONObject jsonObject) {
        String location = jsonObject.getString("location");
        String sensation = jsonObject.getString("sensation");
        int severity = Integer.parseInt(jsonObject.getString("severity"));
        int duration = Integer.parseInt(jsonObject.getString("duration"));
        String date = jsonObject.getString("date");
        lh.getSymptomLogs().add(new Symptom(location, sensation, severity, duration, date));
    }

    // Original Method
    // MODIFIES: lh
    // EFFECTS: parses remedy entry from JSON object and adds it to workroom
    private void addRemedyEntry(LogHistory lh, JSONObject jsonObject) {
        String location = jsonObject.getString("location");
        String remedy = jsonObject.getString("remedy");
        String date = jsonObject.getString("date");
        lh.getRemedyLogs().add(new Remedy(location, remedy, date));
    }






}
