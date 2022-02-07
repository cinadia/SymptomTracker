package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class SymptomTest {
    private Symptom sym;
    private String location;
    private String sensation;
    private int severity;
    private int duration;
    private String date;

    @BeforeEach
    public void setup() {
        location = "jaw";
        sensation = "ache";
        severity = 3;
        duration = 15;
        date = "2022-01-01";
        sym = new Symptom(location, sensation, severity, duration, date);
    }

    @Test
    public void testConstructor() {
        // call and check
        assertEquals(location, sym.getLocation());
        assertEquals(sensation, sym.getSensation());
        assertEquals(severity, sym.getSeverity());
        assertEquals(duration, sym.getDuration());
        assertEquals(date, sym.getDate());
        assertEquals(severity * duration, sym.getScore());

    }

    @Test
    public void testGetSensation() {
        assertEquals(sensation, sym.getSensation());
    }

    @Test
    public void testGetSeverity() {
        assertEquals(severity, sym.getSeverity());
    }

    @Test
    public void testGetDuration() {
        assertEquals(duration, sym.getDuration());
    }

    @Test
    public void testGetScore() {
        assertEquals(duration * severity, sym.getScore());
    }

    @Test
    public void testGetRemedy() {
        assertEquals(null, sym.getRemedy());
    }
}