package persistence;

import org.json.JSONObject;

public interface Writable {
    // EFFECTS: return this as JSON object
    // TODO: "taken from Writable interface" necessary?
    // Taken from Writable interface in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    JSONObject toJson();
}
