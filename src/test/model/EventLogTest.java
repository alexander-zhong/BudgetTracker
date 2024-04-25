package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// This was made based on https://github.students.cs.ubc.ca/CPSC210/AlarmSystem EventLoggerTest Class
public class EventLogTest {
    private Event eventTester1;
    private Event eventTester2;
    private Event eventTester3;

    @BeforeEach
    public void loadEvents() {
        eventTester1 = new Event("Action 1");
        eventTester2 = new Event("Action 2");
        eventTester3 = new Event("Action 3");
        EventLog eventLoggerTester = EventLog.getInstance();
        eventLoggerTester.logEvent(eventTester1);
        eventLoggerTester.logEvent(eventTester2);
        eventLoggerTester.logEvent(eventTester3);
    }

    @Test
    public void testLogEvent() {
        List<Event> currentList = new ArrayList<>();

        EventLog eventLoggerTester = EventLog.getInstance();
        for (Event next : eventLoggerTester) {
            currentList.add(next);
        }

        assertTrue(currentList.contains(eventTester1));
        assertTrue(currentList.contains(eventTester2));
        assertTrue(currentList.contains(eventTester3));
    }

    @Test
    public void testClear() {
        EventLog eventLoggerTester = EventLog.getInstance();
        eventLoggerTester.clear();
        Iterator<Event> itr = eventLoggerTester.iterator();
        assertTrue(itr.hasNext());
        assertEquals("Event log cleared.", itr.next().getDescription());
        assertFalse(itr.hasNext());
    }
}
