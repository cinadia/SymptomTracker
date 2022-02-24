package persistence;

import model.LogHistory;
import model.entries.Entry;
import model.entries.Remedy;
import model.entries.Symptom;
import model.logs.Log;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            LogHistory lh = new LogHistory("My log history");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            LogHistory lh = new LogHistory("NEW log history");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyLogHistory.json");
            writer.open();
            writer.write(lh);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyLogHistory.json");
            lh = reader.read();
            assertEquals("NEW log history", lh.getName());
            assertEquals(0, lh.numSymptoms());
            assertEquals(0, lh.numRemedies());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            LogHistory lh = new LogHistory("NEW log history");
            lh.addSymptom(new Symptom("hand", "stinging", 5, 4, "2022-02-02"));
            lh.addSymptom(new Symptom("toe", "numb", 3, 2, "2022-01-01"));
            lh.addRemedy(new Remedy("arm", "acupuncture", "2020-01-01"));
            lh.addRemedy(new Remedy("toe", "ice", "2020-02-02"));

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralLogHistory.json");
            writer.open();
            writer.write(lh);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralLogHistory.json");
            lh = reader.read();

            assertEquals("NEW log history", lh.getName());

            Log sLog = lh.getSymptomLogs();
            assertEquals(2, sLog.getLog().size());

            Entry symE1 = sLog.getLog().get(0);
            Entry symE2 = sLog.getLog().get(1);

            assertEquals("hand", symE1.getLocation());
            assertEquals("stinging", symE1.getSensation());
            assertEquals("5", Integer.valueOf(symE1.getSeverity()).toString());
            assertEquals("4", Integer.valueOf(symE1.getDuration()).toString());
            assertEquals("20", Integer.valueOf(symE1.getScore()).toString());
            assertEquals("2022-02-02", symE1.getDate());

            assertEquals("toe", symE2.getLocation());
            assertEquals("numb", symE2.getSensation());
            assertEquals("3", Integer.valueOf(symE2.getSeverity()).toString());
            assertEquals("2", Integer.valueOf(symE2.getDuration()).toString());
            assertEquals("6", Integer.valueOf(symE2.getScore()).toString());
            assertEquals("2021-01-01", symE2.getDate());

            Log rLog = lh.getSymptomLogs();
            assertEquals(2, rLog.getLog().size());

            Entry remE1 = rLog.getLog().get(0);
            Entry remE2 = rLog.getLog().get(1);

            assertEquals("arm", remE1.getLocation());
            assertEquals("acupuncture", remE1.getRemedy());
            assertEquals("2021-01-01", remE1.getDate());

            assertEquals("toe", remE2.getLocation());
            assertEquals("ice", remE2.getRemedy());
            assertEquals("2022-02-02", remE2.getDate());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
