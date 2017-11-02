package team2.calendarapp;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Daryl on 11/2/2017.
 */

public class EventDB {
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
