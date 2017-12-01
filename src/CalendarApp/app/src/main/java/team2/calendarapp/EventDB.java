package team2.calendarapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;

/**
 * Created by Daryl on 11/2/2017.
 */

public class EventDB implements Serializable {
    private static ArrayList<Event> events = new ArrayList<>();

    public static Event[] getEvents(){
        return events.toArray(new Event[]{new Event()});
    }

    public static boolean addEvent(Event toAdd){
        boolean worked = events.add(toAdd);
        Collections.sort(events);
        return worked;
    }

    public static void loadEventList(Event[] eventList){
        events = new ArrayList<>(Arrays.asList(eventList));
    }

    public static Event isCollision(Event event){
        Calendar startDate = event.getStartDate(), endDate = event.getEndDate();
        for (Event i : events){
            if (startDate.after(i.getStartDate()) && startDate.before(i.getEndDate())){
                return i;
            }
            if (endDate.after(i.getStartDate()) && endDate.before(i.getEndDate())){
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
