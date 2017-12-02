package team2.calendarapp;

import java.io.Serializable;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;

/**
 * Created by Daryl on 11/2/2017.
 */

public class EventDB implements Serializable {
// The EventDB stores the calendar events for the user and stores a sorted list of the calendar events
    private static EventDB self;
    private ArrayList<Event> events = new ArrayList<>();

    private EventDB(){};

    public static EventDB getInstance(){
        if (self == null){
            self = new EventDB();
        }
        return self;
    }

    // @return events.toArray(new Event[]{new Event()}) the sorted list of calendar events 
    public Event[] getEvents(){
        return events.toArray(new Event[]{new Event()});
    }
    /** 
    * adds new Event to the sorted events list
    * @param toAdd the Event to be added to the calendar events list
    * @return worked a boolean that indicates whether the event was successfully added
    */
    
    public boolean addEvent(Event toAdd){
        boolean worked = events.add(toAdd);
        Collections.sort(events);
        return worked;
    }

    public void loadEventList(Event[] eventList){
        events = new ArrayList<>(Arrays.asList(eventList));
    }

    public Event isCollision(Event event){
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

    public boolean delete(Event event){
        return events.remove(event);
    }

    public Event[] getEventsInRange(Calendar date){
        return getEventsInRange(date, date);
    }

    public Event[] getEventsInRange(Calendar start, Calendar end){
        ArrayList<Event> eventsInRange = new ArrayList<>();
        end.add(Calendar.HOUR, 24);
        Calendar eventDate;

        for (Event e : events){
            eventDate = e.getStart();
            if (eventDate.after(end)){
                break;
            }
            if (eventDate.after(start) || eventDate.equals(start)){
                eventsInRange.add(e);
            }
        }
        return eventsInRange.toArray(new Event[]{new Event()});
    }

    public String asString(){
        String string = "";
        for (Event e : events){
            string += e.toString() + "   :    ";
        }
        return string;
    }
}
