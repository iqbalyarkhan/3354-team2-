package team2.calendarapp;

import java.util.ArrayList;
import java.util.Collections;

/**
* The EventDB stores the calendar events for the user and stores a sorted list of the calendar events
*/
public class EventDB {
    private static ArrayList<Event> events = new ArrayList<>();

    // @return events.toArray(new Event[]{new Event()}) the sorted list of calendar events 
    public static Event[] getEvents(){
        return events.toArray(new Event[]{new Event()});
    }
    /** 
    * adds new Event to the sorted events list
    * @param toAdd the Event to be added to the calendar events list
    * @return worked a boolean 
    */
    
    public static boolean addEvent(Event toAdd){
        boolean worked = events.add(toAdd);
        Collections.sort(events);
        return worked;
    }
}
