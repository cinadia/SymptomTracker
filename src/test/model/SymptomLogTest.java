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
    private Symptom symNoChangesToEdit;

    @BeforeEach
    public void setup() {
        slog = new SymptomLog();
        slogOne = new SymptomLog();
        slogFive = new SymptomLog();

        s1 = new Symptom("foot", "sharp", 5, 1, "2022-01-01");
        s2 = new Symptom("jaw", "hot", 1, 30, "2022-02-02");
        s3 = new Symptom("hand", "ache", 3, 15, "2021-12-12");
        s4 = new Symptom("hip", "stab", 4, 3, "2021-12-11");
        s5 = new Symptom("back", "stab", 4, 3, "2021-12-10");
        symNoChangesToEdit = new Symptom(null, null, -1, -1, null);

        slogOne.add(s1);

        slogFive.add(s1);
        slogFive.add(s2);
        slogFive.add(s3);
        slogFive.add(s4);
        slogFive.add(s5);
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
        slog.add(s1);

        // check
        assertEquals(1, slog.getLog().size());
        assertEquals(s1, slog.getLog().get(0));

        // second time
        // call
        slog.add(s2);

        // check
        assertEquals(2, slog.getLog().size());
        assertEquals(s1, slog.getLog().get(0));
        assertEquals(s2, slog.getLog().get(1));

    }

    @Test
    public void testEditLogByIndexSizeOne() {
        // call
        slogOne.editLogByIndex(0, s2); // replace s1 with s2
        // check
        assertEquals(1, slogOne.getLog().size());
        assertEquals(s2.getLocation(), slogOne.getLog().get(0).getLocation());
        assertEquals(s2.getSensation(), slogOne.getLog().get(0).getSensation());
        assertEquals(s2.getSeverity(), slogOne.getLog().get(0).getSeverity());
        assertEquals(s2.getDuration(), slogOne.getLog().get(0).getDuration());
        assertEquals(s2.getDate(), slogOne.getLog().get(0).getDate());

        //assertEquals(s2, slogOne.getLog().get(0));
    }

    @Test
    public void testEditLogByIndexSizeFive() {
        // call
        slogFive.editLogByIndex(3, s2); // replace s4 with s2
        // check
        assertEquals(5, slogFive.getLog().size());

        assertEquals(s2.getLocation(), slogFive.getLog().get(3).getLocation());
        assertEquals(s2.getSensation(), slogFive.getLog().get(3).getSensation());
        assertEquals(s2.getSeverity(), slogFive.getLog().get(3).getSeverity());
        assertEquals(s2.getDuration(), slogFive.getLog().get(3).getDuration());
        assertEquals(s2.getDate(), slogFive.getLog().get(3).getDate());
    }

    @Test
    public void testEditLogByIndexSymValuesDontChange() {
        // call
        // change s2 to symNoChangesToEdit, except slogFive[3] doesn't change in value (stays s4)
        slogFive.editLogByIndex(3, symNoChangesToEdit);
        // check
        assertEquals(5, slogFive.getLog().size());

        assertEquals(s4.getLocation(), slogFive.getLog().get(3).getLocation());
        assertEquals(s4.getSensation(), slogFive.getLog().get(3).getSensation());
        assertEquals(s4.getSeverity(), slogFive.getLog().get(3).getSeverity());
        assertEquals(s4.getDuration(), slogFive.getLog().get(3).getDuration());
        assertEquals(s4.getDate(), slogFive.getLog().get(3).getDate());
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
        // check
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
        assertEquals(4, slogFive.getLog().size());
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
