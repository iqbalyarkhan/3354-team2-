package team2.calendarapp;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.*;

/**
 * Created by anmol on 12/3/2017.
 */
public class AddEventToEventDBTest {
    private EventDB tester;
    @Before
    public void setUp() {
        tester = EventDB.getInstance();
    }

    @Test
    public void testAddEventDefaultDate(){
        // set event start time and end time to 0000, 00, 00
        // This would make the event start and End time January 1, 1970, the default Calendar time
        Calendar calStart = new GregorianCalendar(0000, 00, 00);
        Calendar calEnd = new GregorianCalendar(0000, 00, 00);
        Event newEvent = new Event("test", null, null, calStart,
                calEnd, 0);
            assertTrue(tester.addEvent(newEvent));
        }
    @Test
    public void testAddNegativeEvent() {
        Calendar calStart = new GregorianCalendar(1111,00,-1);
        Calendar calEnd = new GregorianCalendar(1211,11,-2);
        // creating an event that has negative days of the month
        // Negative values count back from the end of the month,
        // should return true
        Event newEvent = new Event(null,null,null,calStart, calEnd,0);
        assertTrue(tester.addEvent(newEvent));
    }
    @Test
    public void testAddNullEvent() {
        // a null event should not be added to the database
        Event newEvent = null;
        assertFalse(tester.addEvent(newEvent));
    }

    @Test
    public void testAddNormalEvent(){
        // add a normal calendar event should return true
        Calendar calStart = new GregorianCalendar(2017,12,03);
        Calendar calEnd = new GregorianCalendar(2017,12,06);
        Event newEvent = new Event("test android project", "use JUnit Framework","Home",
                calStart, calEnd, 1);
        assertTrue(tester.addEvent(newEvent));

    }


}