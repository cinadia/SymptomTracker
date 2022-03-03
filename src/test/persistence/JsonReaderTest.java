package persistence;

import model.entries.Entry;
import model.logs.Log;
import model.LogHistory;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Adapted from JsonReaderTest class in
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
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
        JsonReader reader = new JsonReader("./data/testReaderEmptyLogHistory");
        try {
            LogHistory lh = reader.read();
            assertEquals("Name's log history", lh.getName());
            assertEquals(0, lh.numSymptoms());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralLogHistory");
        try {
            LogHistory lh = reader.read();
            assertEquals("Name's log history", lh.getName());

            Log sLog = lh.getSymptomLogs();
            assertEquals(2, sLog.getLog().size());

            Entry symE1 = sLog.getLog().get(0);
            Entry symE2 = sLog.getLog().get(1);

            assertEquals("knee", symE1.getLocation());
            assertEquals("sharp", symE1.getSensation());
            assertEquals("4", Integer.valueOf(symE1.getSeverity()).toString());
            assertEquals("20", Integer.valueOf(symE1.getDuration()).toString());
            assertEquals("80", Integer.valueOf(symE1.getScore()).toString());
            assertEquals("2022-02-02", symE1.getDate());

            assertEquals("stomach", symE2.getLocation());
            assertEquals("ache", symE2.getSensation());
            assertEquals("3", Integer.valueOf(symE2.getSeverity()).toString());
            assertEquals("10", Integer.valueOf(symE2.getDuration()).toString());
            assertEquals("30", Integer.valueOf(symE2.getScore()).toString());
            assertEquals("2021-01-01", symE2.getDate());

            Log rLog = lh.getRemedyLogs();
            assertEquals(2, rLog.getLog().size());

            Entry remE1 = rLog.getLog().get(0);
            Entry remE2 = rLog.getLog().get(1);

            assertEquals("knee", remE1.getLocation());
            assertEquals("ice", remE1.getRemedy());
            assertEquals("2022-02-02", remE1.getDate());

            assertEquals("stomach", remE2.getLocation());
            assertEquals("painkillers", remE2.getRemedy());
            assertEquals("2021-01-01", remE2.getDate());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
