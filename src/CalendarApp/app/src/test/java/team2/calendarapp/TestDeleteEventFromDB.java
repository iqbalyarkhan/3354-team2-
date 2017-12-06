package team2.calendarapp;

import org.junit.Before;
import org.junit.Test;
import java.util.Calendar;
import java.util.GregorianCalendar;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.*;

/**
 * Test for deleting events from database
 */
public class TestDeleteEventFromDB {
    private EventDB tester;

    /**
     * Initializes the test function
     */
    @Before
    public void initialize() {

        tester = EventDB.getInstance();

    }

    //Tests for a null event with no dates
    @Test
    public void deleteNullEventTest(){
        //New calendar object with both start and end dates as null
        Calendar startDate = new GregorianCalendar(0000, 00, 00);
        Calendar endDate = new GregorianCalendar(0000, 00, 00);
        Event newEvent = new Event("test", null, null, startDate,
                endDate, 0);
        assertFalse(tester.delete(newEvent));
    }

    //Tests for deleting event with incorrect input dates
    @Test
    public void deleteIncorrectInputTest() {
        Calendar startDate = new GregorianCalendar(2017,13,32);
        Calendar endDate = new GregorianCalendar(2017,14,33);
        Event newEvent = new Event(null,null,null,startDate, endDate,0);
        assertFalse(tester.delete(newEvent));
    }

    //Tests for deleting an event that was actually added to the database
    @Test
    public void deleteActualEvent() {
        Calendar startDate = new GregorianCalendar(2017,12,12);
        Calendar endDate = new GregorianCalendar(2017,12,14);
        Event event = new Event("Birthday!", "my birthday!", "Dallas,TX", startDate,endDate,0);
        tester.addEvent(event);
        assertTrue(tester.delete(event));
    }

}
