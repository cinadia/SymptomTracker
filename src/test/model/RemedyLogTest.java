package model;

import model.entries.Remedy;
import model.logs.RemedyLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemedyLogTest extends LogTest {

//    private RemedyLog log;
//    private RemedyLog logSizeOne;
//    private RemedyLog logSizeFive;
//    private Remedy e1;
//    private Remedy e2;
//    private Remedy e3;
//    private Remedy e4;
//    private Remedy e5;
    private Remedy remNoChangesToEdit;

    @BeforeEach
    public void setup() {
        log = new RemedyLog();
        logSizeOne = new RemedyLog();
        logSizeFive = new RemedyLog();

        e1 = new Remedy("foot", "ice pack", "2022-01-01");
        e2 = new Remedy("jaw", "acupuncture", "2022-02-02");
        e3 = new Remedy("hand", "physiotherapy","2021-12-12");
        e4 = new Remedy("hip", "hot pack", "2021-12-11");
        e5 = new Remedy("back", "chiropractor","2021-12-10");
        remNoChangesToEdit = new Remedy(null, null, null);

        logSizeOne.add(e1);

        logSizeFive.add(e1);
        logSizeFive.add(e2);
        logSizeFive.add(e3);
        logSizeFive.add(e4);
        logSizeFive.add(e5);
    }

    @Test
    public void testEditLogByIndexSizeOne() {
        // call
        logSizeOne.editLogByIndex(0, e2); // replace s1 with s2
        // check
        assertEquals(1, logSizeOne.getLog().size());
        assertEquals(e2.getLocation(), logSizeOne.getLog().get(0).getLocation());
        assertEquals(e2.getRemedy(), logSizeOne.getLog().get(0).getRemedy());
        assertEquals(e2.getDate(), logSizeOne.getLog().get(0).getDate());
    }

    @Test
    public void testEditLogByIndexSizeFive() {
        // call
        logSizeFive.editLogByIndex(3, e2); // replace s4 with s2
        // check
        assertEquals(5, logSizeFive.getLog().size());
        assertEquals(e2.getLocation(), logSizeFive.getLog().get(3).getLocation());
        assertEquals(e2.getRemedy(), logSizeFive.getLog().get(3).getRemedy());
        assertEquals(e2.getDate(), logSizeFive.getLog().get(3).getDate());

    }

    @Test
    public void testEditLogByIndexNoEntryValuesToChange() {
        // call
        logSizeFive.editLogByIndex(3, remNoChangesToEdit);
        // check
        assertEquals(5, logSizeFive.getLog().size());
        assertEquals(e4.getLocation(), logSizeFive.getLog().get(3).getLocation());
        assertEquals(e4.getRemedy(), logSizeFive.getLog().get(3).getRemedy());
        assertEquals(e4.getDate(), logSizeFive.getLog().get(3).getDate());

    }

}
