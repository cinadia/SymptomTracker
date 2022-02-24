package model;

import model.entries.Entry;
import model.logs.Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class LogTest {

    protected Log log;
    protected Log logSizeOne;
    protected Log logSizeFive;
    protected Entry e1;
    protected Entry e2;
    protected Entry e3;
    protected Entry e4;
    protected Entry e5;
    //protected Entry entryNoChangesToEdit;

    @BeforeEach
    abstract void setup();

    @Test
    public void testConstructor() {
        // check
        assertEquals(0, log.getLog().size());
    }

    @Test
    public void testAddEntry() {
        // first time
        // call
        log.add(e1);

        // check
        assertEquals(1, log.getLog().size());
        assertEquals(e1, log.getLog().get(0));

        // second time
        // call
        log.add(e2);

        // check
        assertEquals(2, log.getLog().size());
        assertEquals(e1, log.getLog().get(0));
        assertEquals(e2, log.getLog().get(1));

    }

    @Test
    abstract void testEditLogByIndexSizeOne();

    @Test
    abstract void testEditLogByIndexSizeFive();

    @Test
    abstract void testEditLogByIndexNoEntryValuesToChange();

    @Test
    public void testDeleteOneEntry() {
        // call
        logSizeOne.delete(0);
        assertEquals(0, logSizeOne.getLog().size());
    }

    @Test
    public void testDeleteBeginningOfManyEntries() {
        //call
        logSizeFive.delete(0); // deletes e1
        // check
        assertEquals(4, logSizeFive.getLog().size());
        assertEquals(e2, logSizeFive.getLog().get(0));
        assertEquals(e3, logSizeFive.getLog().get(1));
        assertEquals(e4, logSizeFive.getLog().get(2));
        assertEquals(e5, logSizeFive.getLog().get(3));
    }

    @Test
    public void testDeleteMiddleOfManyEntries() {
        // call
        logSizeFive.delete(2); // deletes e3
        assertEquals(4, logSizeFive.getLog().size());
        assertEquals(e1, logSizeFive.getLog().get(0));
        assertEquals(e2, logSizeFive.getLog().get(1));
        assertEquals(e4, logSizeFive.getLog().get(2));
        assertEquals(e5, logSizeFive.getLog().get(3));
    }

    @Test
    public void testDeleteEndOfManyEntries() {
        // call
        logSizeFive.delete(4); // deletes e5
        assertEquals(4, logSizeFive.getLog().size());
        assertEquals(e1, logSizeFive.getLog().get(0));
        assertEquals(e2, logSizeFive.getLog().get(1));
        assertEquals(e3, logSizeFive.getLog().get(2));
        assertEquals(e4, logSizeFive.getLog().get(3));
    }

    @Test
    public void testDeleteManyEntries() {
        // call
        logSizeFive.delete(0); // deletes e1
        logSizeFive.delete(3); // deletes e5

        Assertions.assertEquals(3, logSizeFive.getLog().size());
        Assertions.assertEquals(e2, logSizeFive.getLog().get(0));
        Assertions.assertEquals(e3, logSizeFive.getLog().get(1));
        Assertions.assertEquals(e4, logSizeFive.getLog().get(2));
    }
}
