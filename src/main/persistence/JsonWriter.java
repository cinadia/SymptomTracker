package persistence;

import model.LogHistory;
import model.logs.Log;

import java.io.FileNotFoundException;

/**
 * Represents a writer that writes JSON representation of Log to file
 */
public class JsonWriter {

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        //this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        //writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of LogHistory to file
    public void write(LogHistory lh) {
        //JSONObject json = wr.toJson();
        //saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        //writer.close();
    }

    // TODO
    // MODIFIES: this
    // EFFECTS: writes string to file
    //private void saveToFile(String json) {
        //writer.print(json);
    //}
}
