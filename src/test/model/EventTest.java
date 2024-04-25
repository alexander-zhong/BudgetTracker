package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


// Testing class constructed based on https://github.students.cs.ubc.ca/CPSC210/AlarmSystem EventTest class
public class EventTest {
    private Event eventTester;
    private Date date;


    @BeforeEach
    public void runBefore() {
        eventTester = new Event("Successfully Logged In");
        date = Calendar.getInstance().getTime();
    }

    @Test
    public void testConstructor() {
        assertEquals("Successfully Logged In", eventTester.getDescription());
        assertEquals(date, eventTester.getDate());
    }

    @Test
    public void testToString() {
        assertEquals(date.toString() + "\n" + "Successfully Logged In", eventTester.toString());
    }

    @Test
    public void testEquals() {
        assertFalse(new Event("Successfully Logged In").equals(eventTester));
        assertTrue(eventTester.equals(eventTester));
    }

    @Test
    public void testEqualsWhenNull() {
        assertFalse(eventTester.equals(null));
    }

    @Test
    public void testEqualsWhenDifferentClass() {
        assertFalse((eventTester.equals(2)));
    }

    @Test
    public void testHash() {
        assertEquals(eventTester.hashCode(), eventTester.hashCode());
    }
}
