package team2.calendarapp;

import java.util.Comparator;

/**
 * Created by Daryl on 11/2/2017.
 */

public class CategoryComparator implements Comparator<Category> {
    public int compare(Category a, Category b){
        String aName, bName;
        aName = a.getName().toLowerCase();
        bName = b.getName().toLowerCase();
        if (aName.equals("none")){
            return -1;
        }
        else if (bName.equals("none")){
            return 1;
        }
        else{
            return aName.compareTo(bName);
        }
    }
}
