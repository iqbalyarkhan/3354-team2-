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

public class Event implements Comparable<Event>, Serializable {
    private String name, description, location;
    private Calendar startDate, endDate;
    private int category;
    private static ArrayList<Category> categories = new ArrayList<>(Arrays.asList(new Category[]{new Category("None", Color.BLUE)}));

    // default constructor for event
    public Event(){}

    // Constructor for Event
    public Event(String newName, String newDescription, String newLocation, Calendar newStart, Calendar newEnd, int newCategory){
        name = newName;
        description = newDescription;
        location = newLocation;
        startDate = newStart;
        endDate = newEnd;
        category = newCategory;
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

    public void setStart(Calendar newDate){
        startDate = newDate;
    }

    public Calendar getStart(){
        return startDate;
    }

    public void setEnd(Calendar newDate){
        endDate = newDate;
    }

    public Calendar getEnd(){
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
        if(other == null){
            return 0 ;
        }
        return startDate.compareTo(other.startDate);
    }

    public String toString() {
        String string = name + ";" + startDate.get(Calendar.MONTH) + "/" + startDate.get(Calendar.DAY_OF_MONTH) + "/" + startDate.get(Calendar.YEAR);
        string += " " + startDate.get(Calendar.HOUR) + ":" + startDate.get(Calendar.MINUTE) + "-";
        string += endDate.get(Calendar.HOUR) + ":" + endDate.get(Calendar.MINUTE) + ";";
        string += description + ";" + location + ";" + category;
        return string;
    }
}
