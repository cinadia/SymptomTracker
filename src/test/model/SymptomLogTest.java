package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SymptomLogTest {

    private SymptomLog slog;
    private SymptomLog slogOne;
    private SymptomLog slogFive;
    private Symptom s1;
    private Symptom s2;
    private Symptom s3;
    private Symptom s4;
    private Symptom s5;

    @BeforeEach
    public void setup() {
        slog = new SymptomLog();
        slogOne = new SymptomLog();
        slogFive = new SymptomLog();

        s1 = new Symptom("foot", "sharp", 5, 1, "2022-01-01");
        s2 = new Symptom("jaw", "hot", 1, 30, "2022-02-02");
        s3 = new Symptom("hand", "ache", 3, 15, "2021-12-12");
        s4 = new Symptom("hip", "stab", 4, 3, "2021-12-11");
        s5 = new Symptom("hip", "stab", 4, 3, "2021-12-10");

        slogOne.addSymptom(s1);

        slogFive.addSymptom(s1);
        slogFive.addSymptom(s2);
        slogFive.addSymptom(s3);
        slogFive.addSymptom(s4);
        slogFive.addSymptom(s5);
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
        assertEquals(s1, slog.getLog().get(0));

        // second time
        // call
        slog.addSymptom(s2);

        // check
        assertEquals(2, slog.getLog().size());
        assertEquals(s1, slog.getLog().get(0));
        assertEquals(s2, slog.getLog().get(1));

    }

    @Test
    public void testEditSymptomByIndexSizeOne() {
        // call
        slogOne.editSymptomByIndex(0, s2); // replace s1 with s2
        // check
        assertEquals(1, slogOne.getLog().size());
        assertEquals(s2, slogOne.getLog().get(0));
    }

    @Test
    public void testEditSymptomByIndexSizeFive() {
        // call
        slogFive.editSymptomByIndex(3, s2); // replace s4 with s2
        // check
        assertEquals(5, slogOne.getLog().size());
        assertEquals(s2, slogOne.getLog().get(3));
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
        slogFive.delete(0); // deletes s1
        assertEquals(4, slogFive.getLog().size());
        assertEquals(s2, slogFive.getLog().get(0));
        assertEquals(s3, slogFive.getLog().get(1));
        assertEquals(s4, slogFive.getLog().get(2));
        assertEquals(s5, slogFive.getLog().get(3));


    }

    @Test
    public void testDeleteMiddleOfManyEntries() {
        // call
        slogFive.delete(2); // deletes s3
        assertEquals(4, slog.getLog().size());
        assertEquals(s1, slogFive.getLog().get(0));
        assertEquals(s2, slogFive.getLog().get(1));
        assertEquals(s4, slogFive.getLog().get(2));
        assertEquals(s5, slogFive.getLog().get(3));
    }

    @Test
    public void testDeleteEndOfManyEntries() {
        // call
        slogFive.delete(4); // deletes s5
        assertEquals(4, slogFive.getLog().size());
        assertEquals(s1, slogFive.getLog().get(0));
        assertEquals(s2, slogFive.getLog().get(1));
        assertEquals(s3, slogFive.getLog().get(2));
        assertEquals(s4, slogFive.getLog().get(3));
    }

    @Test
    public void testDeleteManyEntries() {
        // call
        slogFive.delete(0); // deletes s1
        slogFive.delete(3); // deletes s5

        assertEquals(3, slogFive.getLog().size());
        assertEquals(s2, slogFive.getLog().get(0));
        assertEquals(s3, slogFive.getLog().get(1));
        assertEquals(s4, slogFive.getLog().get(2));
    }



}
