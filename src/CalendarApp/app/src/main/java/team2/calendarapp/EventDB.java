package team2.calendarapp;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Daryl on 11/2/2017.
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

    public static Event[] getEvents(){
        return events.toArray(new Event[]{new Event()});
    }

    public static boolean addEvent(Event toAdd){
        boolean worked = events.add(toAdd);
        Collections.sort(events);
        return worked;
    }
}
