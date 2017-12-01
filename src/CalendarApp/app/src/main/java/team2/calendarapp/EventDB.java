package team2.calendarapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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


    public static void loadEventList(Event[] eventList){
        events = new ArrayList<>(Arrays.asList(eventList));
    }

    public static Event isCollision(Event event){
        Calendar startDate = event.getStart(), endDate = event.getEnd();
        for (Event i : events){
            if (startDate.after(i.getStart()) && startDate.before(i.getEnd())){
                return i;
            }
            if (endDate.after(i.getStart()) && endDate.before(i.getEnd())){
                return i;
            }
        }
        return null;
    }

    public static boolean delete(Event event){
        return events.remove(event);
    }

    public static String asString(){
        String string = "";
        for (Event e : events){
            string += e.toString() + "   :    ";
        }
        return string;
    }

}
