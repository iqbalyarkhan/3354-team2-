package team2.calendarapp;

import android.graphics.Color;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

/**
 * Created by Daryl on 11/1/2017.
 */

/**
 * The Event class creates and stores attributes for a calendar event
 */
public class Event {
    private String name, description, location;
    private Calendar date,start,end;
    private int category;
    private static ArrayList<Category> categories = new ArrayList<>(Arrays.asList(new Category[]{new Category("None", Color.WHITE)}));

    // default constructor for event
    public Event(){}

    // Constructor for Event
    public Event(String newName, String newDescription, String newLocation, Calendar newStart, Calendar newEnd, int newCategory){
        name = newName;
        description = newDescription;
        location = newLocation;
        start = newStart;
        end = newEnd;
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

    public void setStart(Calendar newStart){
        start = newStart;
    }

    public Calendar getStart(){
        return start;
    }

    public void setEnd(Calendar newEnd){
        end = newEnd;
    }

    public Calendar getEnd(){
        return end;
    }

    public void setCategory(int newCategory){
        category = newCategory;
    }

    public int getCategory(){
        return category;
    }

    public static boolean addCategory(String newCategory, int newColor){
        boolean worked = categories.add(new Category(newCategory, newColor));
        Collections.sort(categories);
        return worked;
    }

    public static Category[] getCategories(){
        return categories.toArray(new Category[]{new Category("", 0)});
    }
/*
    public int compareTo(Event other){
        /**
         * Compares the event date and startTime with another event
         * @param other the other event to be compared
         * @return compared date which is the difference of the event's start time
         *
        if (date.compareTo(other.getDate()) != 0){
            return date.compareTo(other.getDate());
        }
        else{
            return l;
        }
    }*/

    public String toString() {
        return (name + "\n" + start);
    }


}
