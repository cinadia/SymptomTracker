package model;

import model.entries.Symptom;
import model.logs.SymptomLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SymptomLogTest extends LogTest {

//    private SymptomLog log;
//    private SymptomLog logSizeOne;
//    private SymptomLog logSizeFive;
//    private Symptom e1;
//    private Symptom e2;
//    private Symptom e3;
//    private Symptom e4;
//    private Symptom e5;
    private Symptom symNoChangesToEdit;

    @Override
    @BeforeEach
    public void setup() {
        log = new SymptomLog();
        logSizeOne = new SymptomLog();
        logSizeFive = new SymptomLog();

        e1 = new Symptom("foot", "sharp", 5, 1, "2022-01-01");
        e2 = new Symptom("jaw", "hot", 1, 30, "2022-02-02");
        e3 = new Symptom("hand", "ache", 3, 15, "2021-12-12");
        e4 = new Symptom("hip", "stab", 4, 3, "2021-12-11");
        e5 = new Symptom("back", "stab", 4, 3, "2021-12-10");
        symNoChangesToEdit = new Symptom(null, null, -1, -1, null);

        logSizeOne.add(e1);

        logSizeFive.add(e1);
        logSizeFive.add(e2);
        logSizeFive.add(e3);
        logSizeFive.add(e4);
        logSizeFive.add(e5);
    }

    @Override
    @Test
    public void testEditLogByIndexSizeOne() {
        // call
        logSizeOne.editLogByIndex(0, e2); // replace s1 with s2
        // check
        assertEquals(1, logSizeOne.getLog().size());
        assertEquals(e2.getLocation(), logSizeOne.getLog().get(0).getLocation());
        assertEquals(e2.getSensation(), logSizeOne.getLog().get(0).getSensation());
        assertEquals(e2.getSeverity(), logSizeOne.getLog().get(0).getSeverity());
        assertEquals(e2.getDuration(), logSizeOne.getLog().get(0).getDuration());
        assertEquals(e2.getDate(), logSizeOne.getLog().get(0).getDate());
        assertEquals(e2.getScore(),
                logSizeOne.getLog().get(0).getSeverity() * logSizeOne.getLog().get(0).getDuration());

    }

    @Override
    @Test
    public void testEditLogByIndexSizeFive() {
        // call
        logSizeFive.editLogByIndex(3, e2); // replace s4 with s2
        // check
        assertEquals(5, logSizeFive.getLog().size());

        assertEquals(e2.getLocation(), logSizeFive.getLog().get(3).getLocation());
        assertEquals(e2.getSensation(), logSizeFive.getLog().get(3).getSensation());
        assertEquals(e2.getSeverity(), logSizeFive.getLog().get(3).getSeverity());
        assertEquals(e2.getDuration(), logSizeFive.getLog().get(3).getDuration());
        assertEquals(e2.getDate(), logSizeFive.getLog().get(3).getDate());
        assertEquals(e2.getScore(),
                logSizeFive.getLog().get(3).getSeverity() * logSizeFive.getLog().get(3).getDuration());

    }

    @Override
    @Test
    public void testEditLogByIndexNoEntryValuesToChange() {
        // call
        // change s2 to symNoChangesToEdit, except slogFive[3] doesn't change in value (stays s4)
        logSizeFive.editLogByIndex(3, symNoChangesToEdit);
        // check
        assertEquals(5, logSizeFive.getLog().size());

        assertEquals(e4.getLocation(), logSizeFive.getLog().get(3).getLocation());
        assertEquals(e4.getSensation(), logSizeFive.getLog().get(3).getSensation());
        assertEquals(e4.getSeverity(), logSizeFive.getLog().get(3).getSeverity());
        assertEquals(e4.getDuration(), logSizeFive.getLog().get(3).getDuration());
        assertEquals(e4.getDate(), logSizeFive.getLog().get(3).getDate());
        assertEquals(e4.getScore(),
                logSizeFive.getLog().get(3).getSeverity() * logSizeFive.getLog().get(3).getDuration());

    }

}
