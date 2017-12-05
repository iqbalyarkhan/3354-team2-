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
// The EventDB stores the calendar events for the user and stores a sorted list of the calendar events
    private static EventDB self;
    private ArrayList<Event> events = new ArrayList<>();

    //Returns the one and only instance of EventDB
    //@return self: An instance of EventDB
    public static EventDB getInstance(){
        if (self == null){      //Only create a new instance if we have to.
            self = new EventDB();
        }
        return self;
    }

    //returns the list of Events in Array form, removing null objects
    // @return: the sorted list of calendar events
    public Event[] getEvents(){
        for (int i = 0;i < events.size();i++){      //Remove any null values
            if (events.get(i) == null){
                events.remove(i);
            }
        }
        return events.toArray(new Event[0]);
    }

    /**
     * adds new Event to the sorted events list
     * * @param toAdd the Event to be added to the calendar events list
     * @return worked a boolean that indicates whether the event was successfully added
     * @throws NullPointerException if ToAdd is a null event
    */
    public boolean addEvent(Event toAdd) throws NullPointerException{
        try {
            boolean worked = events.add(toAdd);
            Collections.sort(events);
            return worked;
        }
        catch (NullPointerException e ){
            e.printStackTrace();
            return false;
        }
    }

    //Loads an EventList from the given array
    //@param eventList: the list of events to be loaded. This should be sorted.
    public void loadEventList(Event[] eventList){
        events = new ArrayList<>(Arrays.asList(eventList));
    }

    //Used to determine if the proposed Event will collide with any in the database
    //@param event: the proposed Event to be checked
    //@return: the Event that the proposed Event will collide with. Null if there is no such Event.
    public Event isCollision(Event event){
        Calendar startDate = event.getStart(), endDate = event.getEnd();
        for (Event i : events){
            if (i == null){     //Skip if there is a null Event
                continue;
            }
            if (startDate.after(i.getStart()) && startDate.before(i.getEnd())){     //If the start of end time collides, the whole event collides
                return i;
            }
            if (endDate.after(i.getStart()) && endDate.before(i.getEnd())){
                return i;
            }
        }
        return null;
    }

    //deletes a given Event from the database
    //@param event: the Event to be deleted
    //@return: whether or not the deletion was successful
    public boolean delete(Event event){
        return events.remove(event);
    }

    //returns all the events on a given day
    //@param date: the date to get all the events of
    //@return: an array of Events that happen on the given day
    @Deprecated
    public Event[] getEventsInRange(Calendar date){
        return getEventsInRange(date, (Calendar) date.clone());
    }

    //returns all the events in a given range
    //@param start: the first day to start getting Events from
    //@param end: the last day to get Events from
    //@return: an array of Events that happened in the given range
    public Event[] getEventsInRange(Calendar start, Calendar end){
        ArrayList<Event> eventsInRange = new ArrayList<>();
        //start.add(Calendar.HOUR, -24);      //When neither an hour nor minute is given, the Calendar defaults to the end of the day. Because we want the beginnin, we subtract 24 hours.
        Calendar eventDate;
        for (Event e : events){
            if (e == null){     //Don't consider any null Events
                continue;
            }
            eventDate = e.getStart();
            if (eventDate.after(end)){      //If the Event is past the end of the range, we've gone too far and can break out
                break;
            }
            if (eventDate.after(start)){        //We know this won't select anything after the end of the range because we would have broken out if that was the case.
                eventsInRange.add(e);
            }
        }
        return eventsInRange.toArray(new Event[eventsInRange.size()]);     //Return the ArrayList as an array
    }

    //converts the EventDB object to a String
    //@return: a representation of the EventDB as a string
    public String toString(){
        String string = "";
        for (Event e : events){
            string += e.toString() + "   :    ";
        }
        return string;
    }
}
