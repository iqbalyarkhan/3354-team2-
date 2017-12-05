package team2.calendarapp;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class GetEventsInRange {
    private EventDB database;
    @Before
    public void setUp() {
        database = EventDB.getInstance();
    }

    @Test
    public void testNoValues(){
        database.loadEventList(new Event[0]);
        Calendar date = Calendar.getInstance();
        date.set(2017, 0, 0);
        assertArrayEquals("No Events in database", new Event[0], database.getEventsInRange(date));
    }

    @Test
    public void testEventsOutOfRange(){
        Calendar start1 = Calendar.getInstance(), end1 = Calendar.getInstance(), start2 = Calendar.getInstance(), end2 = Calendar.getInstance(), date = Calendar.getInstance();
        start1.set(2017, 11, 3, 10, 0);
        end1.set(2017, 11, 3, 15, 0);
        start2.set(2017, 11, 1, 10, 0);
        end2.set(2017, 11, 1, 15, 0);
        date.set(2017, 11, 2);
        Event event1 = new Event("Name", "description", "location", start1, end1, 0);
        Event event2 = new Event("Name", "description", "location", start2, end2, 0);
        database.loadEventList(new Event[]{event1, event2});
        assertArrayEquals("Events in database outside of range", new Event[0], database.getEventsInRange(date));
    }

    @Test
    public void testSingleDate(){
        Calendar start = Calendar.getInstance(), end = Calendar.getInstance(), date = Calendar.getInstance(), date2 = Calendar.getInstance();
        start.set(2017, 11, 3, 10, 0);
        end.set(2017, 11, 3, 15, 0);
        date.set(2017, 11, 3);
        date2.set(2017, 11, 3);
        Event event = new Event("Name", "description", "location", start, end, 0);
        database.loadEventList(new Event[]{event});
        assertArrayEquals("One Event in single date range", new Event[]{event}, database.getEventsInRange(date));
    }

    @Test
    public void testRange(){
        Calendar start1 = Calendar.getInstance(), end1 = Calendar.getInstance(), start2 = Calendar.getInstance(), end2 = Calendar.getInstance(), date1 = Calendar.getInstance(), date2 = Calendar.getInstance();
        Calendar start3 = Calendar.getInstance(), end3 = Calendar.getInstance();
        start1.set(2017, 11, 3, 10, 0);
        end1.set(2017, 11, 3, 15, 0);
        start2.set(2017, 11, 4, 10, 0);
        end2.set(2017, 11, 4, 15, 0);
        start3.set(2017, 11, 5, 10, 0);
        end3.set(2017, 11, 5, 15, 0);
        date1.set(2017, 11, 3);
        date2.set(2017, 11, 4);
        Event event1 = new Event("Name", "description", "location", start1, end1, 0);
        Event event2 = new Event("Name", "description", "location", start2, end2, 0);
        Event event3 = new Event("Name", "description", "location", start3, end3, 0);
        database.loadEventList(new Event[]{event1, event2, event3});
        assertArrayEquals("Two events in a range, one is outside", new Event[]{event1, event2}, database.getEventsInRange(date1, date2));
    }
}