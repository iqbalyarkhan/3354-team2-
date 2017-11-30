package team2.calendarapp;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Collections;

/**
* The EventDB stores the calendar events for the user and stores a sorted list of the calendar events
*/
public class EventDB {
    //Singleton design pattern
    private static EventDB self;
    // hide the default constructor
    private EventDB(){ }
    // create instance of EventDB only if we don't have an EventDB already
    public static EventDB Instance(){
        if (self == null)
            self = new EventDB();
        return self;
    }
    private static ArrayList<Event> events = new ArrayList<>();

    // @return events.toArray(new Event[]{new Event()}) the sorted list of calendar events 
    public static Event[] getEvents(){
        return events.toArray(new Event[]{new Event()});
    }
    /** 
    * adds new Event to the sorted events list
    * @param toAdd the Event to be added to the calendar events list
    * @return worked a boolean that indicates whether the event was successfully added
    */
    
    public static boolean addEvent(Event toAdd){
        boolean worked = events.add(toAdd);
        Collections.sort(events);
        return worked;
    }

}
