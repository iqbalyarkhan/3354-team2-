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
    private static ArrayList<Category> categories = new ArrayList<>(Arrays.asList(new Category[]{new Category("None", Color.WHITE)}));

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

    public static boolean addCategory(String name, int color){
        boolean worked = categories.add(new Category(name, color));
        Collections.sort(categories, new CategoryComparator());
        return worked;
    }

    public static Category[] getCategories(){
        return categories.toArray(new Category[]{new Category("", 0)});
    }

    public static void setCategories(Category[] categoryList){
        categories = new ArrayList<>(Arrays.asList(categoryList));
    }

    public int compareTo(Event other){
        return startDate.compareTo(other.startDate);
    }

    public String toString() {
        return (name + "\n" + startDate.toString());
    }
}
