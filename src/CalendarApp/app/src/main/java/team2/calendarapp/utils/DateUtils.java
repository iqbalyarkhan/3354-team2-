package team2.calendarapp.utils;

import team2.calendarapp.R;
import team2.calendarapp.application.App;
import java.util.Calendar;

/**
 * Created by iqbalkhan on 10/29/17.
 */

public class DateUtils {

    public static String monthToString(int month) {
        switch (month) {
            case Calendar.JANUARY:
                return "January";

            case Calendar.FEBRUARY:
                return "February";

            case Calendar.MARCH:
                return "March";

            case Calendar.APRIL:
                return "April";

            case Calendar.MAY:
                return "May";

            case Calendar.JUNE:
                return "June";

            case Calendar.JULY:
                return "July";

            case Calendar.AUGUST:
                return "August";

            case Calendar.SEPTEMBER:
                return "September";

            case Calendar.OCTOBER:
                return "October";

            case Calendar.NOVEMBER:
                return "November";

            case Calendar.DECEMBER:
                return "December";
        }
        return "";
    }

    public static String dayOfWeekToString(int day){

        switch (day){

            case Calendar.SUNDAY:
                return "Sunday";

            case Calendar.MONDAY:
                return "Monday";

            case Calendar.TUESDAY:
                return "Tuesday";

            case Calendar.WEDNESDAY:
                return "Wednesday";

            case Calendar.THURSDAY:
                return "Thursday";

            case Calendar.FRIDAY:
                return "Friday";

            case Calendar.SATURDAY:
                return "Saturday";


        }

        return "";

    }

}
