package model;

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
        assertEquals(location, rem.getLocation());
        assertEquals(remedy, rem.getRemedy());
        assertEquals(date, rem.getDate());

    }

    @Test
    public void editRemedyAllValid() {
        // first test
        // setup
        String newLocation = "foot";
        String newRemedy = "sharp";
        String newDate = "2021-12-12";
        // call
        rem.editRemedy(newLocation, newRemedy, newDate);
        // check
        assertEquals(newLocation, rem.getLocation());
        assertEquals(newRemedy, rem.getRemedy());
        assertEquals(newDate, rem.getDate());

        // second test
        newLocation = "hip";
        newRemedy = "numb";
        newDate = "2021-06-06";
        // call
        rem.editRemedy(newLocation, newRemedy, newDate);
        // check
        assertEquals(newLocation, rem.getLocation());
        assertEquals(remedy, rem.getRemedy());
        assertEquals(newDate, rem.getDate());
    }

    @Test
    public void editRemedyLocationInvalid() {
        // setup
        String newLocation = null;
        String newRemedy = "sharp";
        String newDate = "2021-12-12";
        // call
        rem.editRemedy(newLocation, newRemedy, newDate);        // check
        assertEquals(location, rem.getLocation()); // stays the same
        assertEquals(newRemedy, rem.getRemedy());
        assertEquals(newDate, rem.getDate());

    }

    @Test
    public void editRemedyRemedyInvalid() {
        // setup
        String newLocation = "foot";
        String newRemedy = null;
        String newDate = "2021-12-12";
        // call
        rem.editRemedy(newLocation, newRemedy, newDate);        // check
        assertEquals(newLocation, rem.getLocation()); // stays the same
        assertEquals(remedy, rem.getRemedy());
        assertEquals(newDate, rem.getDate());
    }


    @Test
    public void editRemedyDateInvalid() {
        // setup
        String newLocation = "foot";
        String newRemedy = "ache";
        String newDate = null;
        // call
        rem.editRemedy(newLocation, newRemedy, newDate);
        // check
        assertEquals(newLocation, rem.getLocation()); // stays the same
        assertEquals(newRemedy, rem.getRemedy());
        assertEquals(date, rem.getDate());
    }
}
