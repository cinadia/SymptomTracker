package model;

import model.entries.Remedy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemedyTest {
    private Remedy rem;
    private String location;
    private String remedy;
    private String date;

    @BeforeEach
    public void setup() {
        location = "jaw";
        remedy = "ice pack";
        date = "2022-01-01";
        rem = new Remedy(location, remedy, date);
    }

    @Test
    public void testConstructor() {
        // call and check
        rem = new Remedy(location, remedy, date);
        assertEquals(location, rem.getLocation());
        assertEquals(remedy, rem.getRemedy());
        assertEquals(date, rem.getDate());

    }

    @Test
    public void testGetSensation() {
        assertEquals(null, rem.getSensation());
    }

    @Test
    public void testGetSeverity() {
        assertEquals(-1, rem.getSeverity());
    }

    @Test
    public void testGetDuration() {
        assertEquals(-1, rem.getDuration());
    }

    @Test
    public void testGetScore() {
        assertEquals(-1, rem.getScore());
    }
}
