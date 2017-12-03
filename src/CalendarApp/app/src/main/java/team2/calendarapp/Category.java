package team2.calendarapp;

import android.graphics.Color;

import java.io.Serializable;

/**
 * Created by Daryl on 11/29/2017.
 */

public class Category implements Serializable, Comparable<Category> {
    private String name;
    private int color;

    public Category(String newName, int newColor){
        name = newName;
        color = newColor;
    }

    public String getName(){
        return name;
    }

    public void setName(String newName){
        name = newName;
    }

    public int getColor(){
        return color;
    }

    public void setColor(int newColor){
        color = newColor;
    }

    public String toString(){
        return name;
    }

    //compareTo functions the same way as any other compareTo function
    //@param other: The other category that will be compared to this
    //@return: a negative integer if this is less than that, a positive integer if this is greater than that, 0 if they are equal
    public int compareTo(Category other){
        String otherName = other.getName().toLowerCase(), thisName = name.toLowerCase();      //Make them lowercase because we don't want case to interfere in this comparison
        if (thisName.equals("none")){     //None is put first because that should be the default category
            return -1;
        }
        else if (otherName.equals("none")){
            return 1;
        }
        else{       //Other than none, they should be ordered alphabetically
            return thisName.compareTo(otherName);
        }
    }
}
