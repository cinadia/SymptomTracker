package persistence;

import org.json.JSONObject;

/**
 * Represents a writable JSON object
 * Adapted from JsonReader class in
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */
public interface Writable {
    // EFFECTS: return this as JSON object
    JSONObject toJson();
}
