package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SymptomLogTest {
    private SymptomLog slog;
    private Symptom s1;
    private Symptom s2;
    private Symptom s3;
    private Symptom s4;
    private Symptom s5;

    @BeforeEach
    public void setup() {
        slog = new SymptomLog();
        s1 = new Symptom("foot", "sharp", 5, 1, "2022-01-01");
        s2 = new Symptom("jaw", "hot", 1, 30, "2022-02-02");
        s3 = new Symptom("hand", "ache", 3, 15, "2021-12-12");
        s4 = new Symptom("hip", "stab", 4, 3, "2021-12-11");
        s5 = new Symptom("hip", "stab", 4, 3, "2021-12-10");
    }

    @Test
    public void testConstructor() {
        // check
        assertEquals(0, slog.getLog().size());
    }

    @Test
    public void testAddSymptom() {
        // first time
        // call
        slog.addSymptom(s1);

        // check
        assertEquals(1, slog.getLog().size());
        assertEquals(new Symptom(newLocation, newSensation, newSeverity, newDuration, newDate), slog.getLog().get(0));

        // second time
        // setup


        // call
        slog.addSymptom(secondLocation, secondSensation, secondSeverity, secondDuration, secondDate);

        // check
        assertEquals(2, slog.getLog().size());
        assertEquals(new Symptom(secondLocation, secondSensation, secondSeverity, secondDuration, secondDate),
                slog.getLog().get(1));

    }

    @Test
    public void testEditSymptomByIndex() {

    }

    @Test
    public void testViewAllLogs() {

    }

    @Test
    public void testViewLogRange() {

    }

    @Test
    public void testDeleteOneEntry() {
        // call
        slogOne.delete(0);
        assertEquals(0, slogOne.getLog().size());
    }

    @Test
    public void testDeleteBeginningOfManyEntries() {
        //call
        slogFive.delete(0);
        assertEquals(4, slogFive.getLog().size());
    }

    @Test
    public void testDeleteMiddleOfManyEntries() {
        // call
        slogFive.delete(2);
        assertEquals(4, slog.getLog().size());
    }

    @Test
    public void testDeleteEndOfManyEntries() {
        // call
        slogFive.delete(4);
        assertEquals(4, slogFive.getLog().size());
    }



}
