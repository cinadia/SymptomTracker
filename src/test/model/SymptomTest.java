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

    }
    /*
    @Test
    public void editSymptomAllValid() {
        // first test
        // setup
        String newLocation = "foot";
        String newSensation = "sharp";
        int newSeverity = 4;
        int newDuration = 11;
        String newDate = "2021-12-12";
        // call
        sym.editSymptom(newLocation, newSensation, newSeverity, newDuration, newDate);
        // check
        assertEquals(newLocation, sym.getLocation());
        assertEquals(newSensation, sym.getSensation());
        assertEquals(newSeverity, sym.getSeverity());
        assertEquals(newDuration, sym.getDuration());
        assertEquals(newDate, sym.getDate());

        // second test
        String secondLocation = "hip";
        String secondSensation = "numb";
        int secondSeverity = 1;
        int secondDuration = 20;
        String secondDate = "2021-06-06";
        // call
        sym.editSymptom(secondLocation, secondSensation, secondSeverity, secondDuration, secondDate);
        // check
        assertEquals(secondLocation, sym.getLocation());
        assertEquals(secondSensation, sym.getSensation());
        assertEquals(secondSeverity, sym.getSeverity());
        assertEquals(secondDuration, sym.getDuration());
        assertEquals(secondDate, sym.getDate());
    }

    @Test
    public void editSymptomLocationInvalid() {
        // setup
        String newLocation = null;
        String newSensation = "sharp";
        int newSeverity = 4;
        int newDuration = 11;
        String newDate = "2021-12-12";
        // call
        sym.editSymptom(newLocation, newSensation, newSeverity, newDuration, newDate);
        // check
        assertEquals(location, sym.getLocation()); // stays the same
        assertEquals(newSensation, sym.getSensation());
        assertEquals(newSeverity, sym.getSeverity());
        assertEquals(newDuration, sym.getDuration());
        assertEquals(newDate, sym.getDate());

    }

    @Test
    public void editSymptomSensationInvalid() {
        // setup
        String newLocation = "foot";
        String newSensation = null;
        int newSeverity = 4;
        int newDuration = 11;
        String newDate = "2021-12-12";
        // call
        sym.editSymptom(newLocation, newSensation, newSeverity, newDuration, newDate);
        // check
        assertEquals(newLocation, sym.getLocation()); // stays the same
        assertEquals(sensation, sym.getSensation());
        assertEquals(newSeverity, sym.getSeverity());
        assertEquals(newDuration, sym.getDuration());
        assertEquals(newDate, sym.getDate());
    }

    @Test
    public void editSymptomSeverityInvalid() {
        // setup
        String newLocation = "foot";
        String newSensation = "ache";
        int newSeverity = -1;
        int newDuration = 11;
        String newDate = "2021-12-12";
        // call
        sym.editSymptom(newLocation, newSensation, newSeverity, newDuration, newDate);
        // check
        assertEquals(newLocation, sym.getLocation()); // stays the same
        assertEquals(newSensation, sym.getSensation());
        assertEquals(severity, sym.getSeverity());
        assertEquals(newDuration, sym.getDuration());
        assertEquals(newDate, sym.getDate());
    }

    @Test
    public void editSymptomSeverityLowerBoundary() {
        // setup
        String newLocation = "foot";
        String newSensation = "ache";
        int newSeverity = 1;
        int newDuration = 20;
        String newDate = "2021-12-12";
        // call
        sym.editSymptom(newLocation, newSensation, newSeverity, newDuration, newDate);
        // check
        assertEquals(newLocation, sym.getLocation()); // stays the same
        assertEquals(newSensation, sym.getSensation());
        assertEquals(newSeverity, sym.getSeverity());
        assertEquals(newDuration, sym.getDuration());
        assertEquals(newDate, sym.getDate());
    }

    @Test
    public void editSymptomSeverityUpperBoundary() {
        // setup
        String newLocation = "foot";
        String newSensation = "ache";
        int newSeverity = 5;
        int newDuration = 20;
        String newDate = "2021-12-12";
        // call
        sym.editSymptom(newLocation, newSensation, newSeverity, newDuration, newDate);
        // check
        assertEquals(newLocation, sym.getLocation()); // stays the same
        assertEquals(newSensation, sym.getSensation());
        assertEquals(newSeverity, sym.getSeverity());
        assertEquals(newDuration, sym.getDuration());
        assertEquals(newDate, sym.getDate());
    }
    
    @Test 
    public void editSymptomDurationInvalid() {
        // setup
        String newLocation = "foot";
        String newSensation = "ache";
        int newSeverity = 2;
        int newDuration = -1;
        String newDate = "2021-12-12";
        // call
        sym.editSymptom(newLocation, newSensation, newSeverity, newDuration, newDate);
        // check
        assertEquals(newLocation, sym.getLocation()); // stays the same
        assertEquals(newSensation, sym.getSensation());
        assertEquals(newSeverity, sym.getSeverity());
        assertEquals(duration, sym.getDuration());
        assertEquals(newDate, sym.getDate());
    }

    @Test
    public void editSymptomDurationBoundary() {
        // setup
        String newLocation = "foot";
        String newSensation = "ache";
        int newSeverity = 2;
        int newDuration = 1;
        String newDate = "2021-12-12";
        // call
        sym.editSymptom(newLocation, newSensation, newSeverity, newDuration, newDate);
        // check
        assertEquals(newLocation, sym.getLocation()); // stays the same
        assertEquals(newSensation, sym.getSensation());
        assertEquals(newSeverity, sym.getSeverity());
        assertEquals(newDuration, sym.getDuration());
        assertEquals(newDate, sym.getDate());
    }

    @Test
    public void editSymptomDateInvalid() {
        // setup
        String newLocation = "foot";
        String newSensation = "ache";
        int newSeverity = 2;
        int newDuration = -1;
        String newDate = null;
        // call
        sym.editSymptom(newLocation, newSensation, newSeverity, newDuration, newDate);
        // check
        assertEquals(newLocation, sym.getLocation()); // stays the same
        assertEquals(newSensation, sym.getSensation());
        assertEquals(newSeverity, sym.getSeverity());
        assertEquals(duration, sym.getDuration());
        assertEquals(date, sym.getDate());
    }

     */
}