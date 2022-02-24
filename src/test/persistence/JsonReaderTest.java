package persistence;

import model.entries.Entry;
import model.logs.Log;
import model.LogHistory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            LogHistory lh = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptySymptomLog.json");
        try {
            LogHistory lh = reader.read();
            assertEquals("Name's symptom log", lh.getName());
            assertEquals(0, lh.numSymptoms());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralSymptomLog.json");
        try {
            LogHistory lh = reader.read();
            assertEquals("Name's symptom log", lh.getName());
            Log sLog = lh.getSymptomLogs();
            assertEquals(2, sLog.getLog().size());

            Entry e1 = sLog.getLog().get(0);
            Entry e2 = sLog.getLog().get(1);

            assertEquals("knee", e1.getLocation());
            assertEquals("sharp", e1.getSensation());
            assertEquals("4", Integer.valueOf(e1.getSeverity()).toString());
            assertEquals("20", Integer.valueOf(e1.getDuration()).toString());
            assertEquals("80", e1.getScore());
            assertEquals("2022-02-02", e1.getLocation());

            assertEquals("stomach", e2.getLocation());
            assertEquals("ache", e2.getSensation());
            assertEquals("3", Integer.valueOf(e2.getSeverity()).toString());
            assertEquals("10", Integer.valueOf(e2.getDuration()).toString());
            assertEquals("30", e2.getScore());
            assertEquals("2021-01-01", e2.getLocation());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

//    private void checkLog(String name, Category category, Entry entry) {
//        assertEquals(name, entry.getName());
//        assertEquals(category, entry.getCategory());
//    }

}
