package team2.calendarapp.objects;

import java.util.Calendar;

import team2.calendarapp.adapters.CalendarViewAdapter;
import team2.calendarapp.utils.DateUtils;

/**
 * Created by iqbalkhan on 10/29/17.
 */

public class CalendarDate {

    private int theDay;
    private int theMonth;
    private int theYear;

    CalendarDate(int day, int month, int year){

        theDay = day;
        theMonth = month;
        theYear = year;

    }

    public CalendarDate(Calendar calendar){

        this (calendar.get(Calendar.DAY_OF_MONTH),
              calendar.get(Calendar.MONTH),
              calendar.get(Calendar.YEAR));

    }

    public CalendarDate(CalendarDate other){

        this(other.getDay(),
                other.getMonth(),
                other.getYear());

    }

    public int getDay(){

        return theDay;
    }

    public int getMonth(){

        return theMonth;

    }

    public int getYear(){

        return theYear;
    }

    public Calendar getCalendar(){

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(theYear, theMonth, theDay);
        return calendar;


    }

    public long getMilliS(){

        return getCalendar().getTimeInMillis();

    }

    public int getDayOfWeek(){

        return getCalendar().get(Calendar.DAY_OF_WEEK);

    }

    public boolean isToday(){

        Calendar today = Calendar.getInstance();

        return theYear == today.get(Calendar.YEAR) &&
                theMonth == today.get(Calendar.MONTH) &&
                theDay == today.get(Calendar.DAY_OF_MONTH);
    }

    public boolean isDateEqual(CalendarDate other){

        return theYear == other.getYear() &&
                theMonth == other.getMonth() &&
                theDay == other.getDay();

    }

    public void addDays(int value){

        Calendar calendar = getCalendar();
        calendar.add(Calendar.DATE, value);
        theDay = calendar.get(Calendar.DAY_OF_MONTH);
        theMonth = calendar.get(Calendar.MONTH);
        theYear = calendar.get(Calendar.YEAR);

    }

    @Override
    public String toString(){

        return monthToString() + "/" + dayToString() + "/" + yearToString();

    }

    public String dayToString(){

        if (theDay < 10){

            return "0" + theDay;
        }

        else{

            return "" + theDay;
        }

    }

    public String monthToString(){

        if ((theMonth + 1) < 10){

            return "0" + (theMonth + 1);

        }

        else{

            return "" + (theMonth + 1);

        }

    }

    public String yearToString(){

        return String.valueOf(theYear);

    }

    public String monthToStringName(){

        return DateUtils.monthToString(theMonth);

    }

    public String dayOfWeekToStringName(){

        return DateUtils.dayOfWeekToString(getCalendar().get(Calendar.DAY_OF_WEEK));

    }

}
