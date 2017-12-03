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

    //Adds a Category to the list of valid Categories
    //@param name: the name of the Category to be added
    //@param color: the color of the Category to be added
    //@return: whether or not the category was successfully added
    public static boolean addCategory(String name, int color){
        for (Category c : categories){
            if (name.equals(c.getName())){      //If the proposed name matches any of those already in the list, it can't be added
                return false;
            }
        }

        boolean worked = categories.add(new Category(name, color));
        Collections.sort(categories);
        return worked;
    }

    public static Category[] getCategories(){
        return categories.toArray(new Category[0]);
    }

    //Sets the list of Categories to the one given
    //@param categoryList: the list of Categories to set the internal list as
    public static void setCategories(Category[] categoryList){
        categories = new ArrayList<>(Arrays.asList(categoryList));
    }

    //standard compareTo method
    //@param other: the other Event to compare this one to
    //@return: a negative integer if this is less than that, a positive integer is this is greater than that, 0 if they're equal.
    public int compareTo(Event other){
        if(other == null){      //If the event is null, it doens't matter where it goes
            return 0;
        }
        return startDate.compareTo(other.startDate);        //Sort based on startDate
    }

    //a method to convert the Event object to a String
    //@return: a String representation of the given Event
    public String toString() {
        String string = name + ";" + startDate.get(Calendar.MONTH) + "/" + startDate.get(Calendar.DAY_OF_MONTH) + "/" + startDate.get(Calendar.YEAR);
        string += " " + startDate.get(Calendar.HOUR) + ":" + startDate.get(Calendar.MINUTE) + "-";
        string += endDate.get(Calendar.HOUR) + ":" + endDate.get(Calendar.MINUTE) + ";";
        string += description + ";" + location + ";" + category;
        return string;
    }

    //a method to determine if two Events are equal
    //@param other: the Event to be compared to this
    //@return: true if they're equal, false otherwise
    public boolean equals(Event other){
        if (!name.equals(other.name)){
            return false;
        }
        if (!description.equals(other.description)){
            return false;
        }
        if (!location.equals(other.location)){
            return false;
        }
        if (!startDate.equals(other.startDate)){
            return false;
        }
        if (!endDate.equals(other.endDate)){
            return false;
        }
        if (category != other.category){
            return false;
        }
        return true;
    }
}
