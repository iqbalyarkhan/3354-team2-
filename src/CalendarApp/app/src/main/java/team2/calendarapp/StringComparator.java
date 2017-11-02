package team2.calendarapp;

import java.util.Comparator;

/**
 * Created by Daryl on 11/2/2017.
 */

public class StringComparator implements Comparator<String> {
    public int compare(String a, String b){
        a = a.toLowerCase();
        b = b.toLowerCase();
        if (a.equals("none")){
            return -1;
        }
        else if (b.equals("none")){
            return 1;
        }
        else{
            return a.compareTo(b);
        }
    }
}
