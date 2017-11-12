package team2.calendarapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

/**
 * Created by Daryl on 11/1/2017.
 */

public class Event implements Comparable<Event> {
    private String name, description, location;
    private Date date;
    private int start, end, category;
    private static ArrayList<String> categories = new ArrayList<>(Arrays.asList(new String[]{"None"}));

    public Event(){}

    public Event(String newName, String newDescription, String newLocation, Date newDate, int newStart, int newEnd, int newCategory){
        name = newName;
        description = newDescription;
        location = newLocation;
        date = newDate;
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

    public void setDate(Date newDate){
        date = newDate;
    }

    public Date getDate(){
        return date;
    }

    public void setStartTime(int newStart){
        start = newStart;
    }

    public int getStart(){
        return start;
    }

    public void setEnd(int newEnd){
        end = newEnd;
    }

    public int getEnd(){
        return end;
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
        if (date.compareTo(other.getDate()) != 0){
            return date.compareTo(other.getDate());
        }
        else{
            return other.getStart() - start;
        }
    }

    public String toString() {
        return (name + "\n" + start);
    }


}
