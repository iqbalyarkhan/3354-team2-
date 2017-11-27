package team2.calendarapp;

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
    private static ArrayList<String> categories = new ArrayList<>(Arrays.asList(new String[]{"None"}));

    public Event(){}

    public Event(String newName, String newDescription, String newLocation, Calendar newStartDate, Calendar newEndDate, int newCategory){
        name = newName;
        description = newDescription;
        location = newLocation;
        startDate = newStartDate;
        endDate = newEndDate;
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

    public static boolean addCategory(String newCategory){
        boolean worked = categories.add(newCategory);
        Collections.sort(categories, new StringComparator());
        return worked;
    }

    public static String[] getCategories(){
        return categories.toArray(new String[]{""});
    }

    public int compareTo(Event other){
        return startDate.compareTo(other.startDate);
    }

    public String toString() {
        return (name + "\n" + startDate.toString());
    }
}
