package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemedyLogTest {

    private RemedyLog rlog;
    private RemedyLog rlogOne;
    private RemedyLog rlogFive;
    private Remedy s1;
    private Remedy s2;
    private Remedy s3;
    private Remedy s4;
    private Remedy s5;

    @BeforeEach
    public void setup() {
        rlog = new RemedyLog();
        rlogOne = new RemedyLog();
        rlogFive = new RemedyLog();

        s1 = new Remedy("foot", "ice pack", "2022-01-01");
        s2 = new Remedy("jaw", "acupuncture", "2022-02-02");
        s3 = new Remedy("hand", "physiotherapy","2021-12-12");
        s4 = new Remedy("hip", "hot pack", "2021-12-11");
        s5 = new Remedy("back", "chiropractor","2021-12-10");

        rlogOne.add(s1);

        rlogFive.add(s1);
        rlogFive.add(s2);
        rlogFive.add(s3);
        rlogFive.add(s4);
        rlogFive.add(s5);
    }

    @Test
    public void testConstructor() {
        // check
        assertEquals(0, rlog.getLog().size());
    }

    @Test
    public void testAddRemedy() {
        // first time
        // call
        rlog.add(s1);

        // check
        assertEquals(1, rlog.getLog().size());
        assertEquals(s1, rlog.getLog().get(0));

        // second time
        // call
        rlog.add(s2);

        // check
        assertEquals(2, rlog.getLog().size());
        assertEquals(s1, rlog.getLog().get(0));
        assertEquals(s2, rlog.getLog().get(1));

    }

    @Test
    public void testEditRemedyByIndexSizeOne() {
        // call
        rlogOne.editLogByIndex(0, s2); // replace s1 with s2
        // check
        assertEquals(1, rlogOne.getLog().size());
        assertEquals(s2, rlogOne.getLog().get(0));
    }

    @Test
    public void testEditRemedyByIndexSizeFive() {
        // call
        rlogFive.editLogByIndex(3, s2); // replace s4 with s2
        // check
        assertEquals(5, rlogOne.getLog().size());
        assertEquals(s2, rlogOne.getLog().get(3));
    }

    @Test
    public void testDeleteOneEntry() {
        // call
        rlogOne.delete(0);
        assertEquals(0, rlogOne.getLog().size());
    }

    @Test
    public void testDeleteBeginningOfManyEntries() {
        //call
        rlogFive.delete(0); // deletes s1
        assertEquals(4, rlogFive.getLog().size());
        assertEquals(s2, rlogFive.getLog().get(0));
        assertEquals(s3, rlogFive.getLog().get(1));
        assertEquals(s4, rlogFive.getLog().get(2));
        assertEquals(s5, rlogFive.getLog().get(3));
    }

    @Test
    public void testDeleteMiddleOfManyEntries() {
        // call
        rlogFive.delete(2); // deletes s3
        assertEquals(4, rlog.getLog().size());
        assertEquals(s1, rlogFive.getLog().get(0));
        assertEquals(s2, rlogFive.getLog().get(1));
        assertEquals(s4, rlogFive.getLog().get(2));
        assertEquals(s5, rlogFive.getLog().get(3));
    }

    @Test
    public void testDeleteEndOfManyEntries() {
        // call
        rlogFive.delete(4); // deletes s5
        assertEquals(4, rlogFive.getLog().size());
        assertEquals(s1, rlogFive.getLog().get(0));
        assertEquals(s2, rlogFive.getLog().get(1));
        assertEquals(s3, rlogFive.getLog().get(2));
        assertEquals(s4, rlogFive.getLog().get(3));
    }

    @Test
    public void testDeleteManyEntries() {
        // call
        rlogFive.delete(0); // deletes s1
        rlogFive.delete(3); // deletes s5

        assertEquals(3, rlogFive.getLog().size());
        assertEquals(s2, rlogFive.getLog().get(0));
        assertEquals(s3, rlogFive.getLog().get(1));
        assertEquals(s4, rlogFive.getLog().get(2));
    }
}
