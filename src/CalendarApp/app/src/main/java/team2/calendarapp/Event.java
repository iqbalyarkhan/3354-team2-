package team2.calendarapp;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Daryl on 11/1/2017.
 */

public class Event {
    private String name, description, location;
    private Date date;
    private int start, end, category;
    private static ArrayList<String> categories = new ArrayList<>();

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

    public boolean addCategory(String newCategory){
        return categories.add(newCategory);
    }

    public String[] getCategories(){
        return categories.toArray(new String[]{""});
    }
}
