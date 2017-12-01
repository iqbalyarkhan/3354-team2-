package team2.calendarapp;

import android.graphics.Color;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;

/**
 * Created by Daryl on 11/1/2017.
 */

<<<<<<< HEAD
public class Event implements Comparable<Event>, Serializable {
    private String name, description, location;
    private Calendar startDate, endDate;
    private int category;
    private static ArrayList<Category> categories = new ArrayList<>(Arrays.asList(new Category[]{new Category("None", Color.WHITE)}));
=======
/**
 * The Event class creates and stores attributes for a calendar event
 */
public class Event implements Comparable<Event> {
    private String name, description, location;
    private Date date;
    private int ID, start, end, category;
    private static ArrayList<String> categories = new ArrayList<>(Arrays.asList(new String[]{"None"}));
>>>>>>> aa66e394ef0a3683bf741e58b4d4a8f5502628b6

    // default constructor for event
    public Event(){}

<<<<<<< HEAD
    public Event(String newName, String newDescription, String newLocation, Calendar newStartDate, Calendar newEndDate, int newCategory){
=======
    // Constructor for Event
    public Event(int newID,String newName, String newDescription, String newLocation, Date newDate, int newStart, int newEnd, int newCategory){
        ID = newID;
>>>>>>> aa66e394ef0a3683bf741e58b4d4a8f5502628b6
        name = newName;
        description = newDescription;
        location = newLocation;
        startDate = newStartDate;
        endDate = newEndDate;
        category = newCategory;
    }

    public void setEventID(int newID){
        ID = newID;
    }

    public int getID(){
        return ID;
    }
    public void setName(String newName){
        name = newName;
    }

    public String getName(){
        return name;
    }

    public void setDescription(String newDescription){
        description = newDescription;
    }

    public String getDescription(){
        return description;
    }

    public void setLocation(String newLocation){
        location = newLocation;
    }

    public String getLocation(){
        return location;
    }

    public void setStartDate(Calendar newDate){
        startDate = newDate;
    }

    public Calendar getStartDate(){
        return startDate;
    }

    public void setEndDate(Calendar newDate){
        endDate = newDate;
    }

    public Calendar getEndDate(){
        return endDate;
    }

    public void setCategory(int newCategory){
        category = newCategory;
    }

    public int getCategory(){
        return category;
    }

    public static boolean addCategory(String name, int color){
        for (Category c : categories){
            if (name.equals(c.getName())){
                return false;
            }
        }
        boolean worked = categories.add(new Category(name, color));
        Collections.sort(categories);
        return worked;
    }

    public static Category[] getCategories(){
        return categories.toArray(new Category[]{new Category("", 0)});
    }

    public static void setCategories(Category[] categoryList){
        categories = new ArrayList<>(Arrays.asList(categoryList));
    }

    // comparing the Event date and start time
    public int compareTo(Event other){
<<<<<<< HEAD
        return startDate.compareTo(other.startDate);
=======
        /**
         * Compares the event date and startTime with another event
         * @param other the other event to be compared
         * @return compared date which is the difference of the event's start time
         */
        if (date.compareTo(other.getDate()) != 0){
            return date.compareTo(other.getDate());
        }
        else{
            return other.getStart() - start;
        }
>>>>>>> aa66e394ef0a3683bf741e58b4d4a8f5502628b6
    }

    public String toString() {
        String string = name + ";" + startDate.get(Calendar.MONTH) + "/" + startDate.get(Calendar.DAY_OF_MONTH) + "/" + startDate.get(Calendar.YEAR);
        string += " " + startDate.get(Calendar.HOUR) + ":" + startDate.get(Calendar.MINUTE) + "-";
        string += endDate.get(Calendar.HOUR) + ":" + endDate.get(Calendar.MINUTE) + ";";
        string += description + ";" + location + ";" + category;
        return string;
    }
}
