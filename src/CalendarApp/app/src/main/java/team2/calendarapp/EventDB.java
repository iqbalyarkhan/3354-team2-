package team2.calendarapp;

<<<<<<< HEAD
import java.io.Serializable;
=======
import android.database.sqlite.SQLiteDatabase;

>>>>>>> aa66e394ef0a3683bf741e58b4d4a8f5502628b6
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;

/**
<<<<<<< HEAD
 * Created by Daryl on 11/2/2017.
 */

public class EventDB implements Serializable {
=======
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
>>>>>>> aa66e394ef0a3683bf741e58b4d4a8f5502628b6
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

<<<<<<< HEAD
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
=======
>>>>>>> aa66e394ef0a3683bf741e58b4d4a8f5502628b6
}
