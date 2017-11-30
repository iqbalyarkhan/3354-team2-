package team2.calendarapp;

import android.graphics.Color;

import java.io.Serializable;

/**
 * Created by Daryl on 11/29/2017.
 */

public class Category implements Serializable {
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
}
