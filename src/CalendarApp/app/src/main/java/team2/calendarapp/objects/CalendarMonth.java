package team2.calendarapp.objects;

import team2.calendarapp.utils.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by iqbalkhan on 10/29/17.
 */

public class CalendarMonth {

    public static final int NUMBER_OF_WEEKS_IN_MONTH = 6;
    public static final int NUMBER_OF_DAYS_IN_WEEK = 7;
    private int month;
    private int year;
    private List<CalendarDate> days;

    public CalendarMonth(Calendar calendar){

        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
        createMonthDays();

    }

    public CalendarMonth(CalendarMonth other, int value){

        Calendar calendar = Calendar.getInstance();
        calendar.set(other.getYear(), other.getMonth(), 1);
        calendar.add(Calendar.MONTH, value);

        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
        createMonthDays();

    }

    private void createMonthDays(){

        CalendarDate date = new CalendarDate(1, month, year);
        int dayOfWeek = date.getDayOfWeek();
        date.addDays(1-dayOfWeek);

        days = new ArrayList<>();

        for (int i = 0; i < NUMBER_OF_DAYS_IN_WEEK * NUMBER_OF_DAYS_IN_WEEK; i++){

            CalendarDate day = new CalendarDate(date.getDay(), date.getMonth(), date.getYear());
            days.add(day);
            date.addDays(1);

        }


    }

    public int getMonth(){

        return month;

    }

    public int getYear(){

        return year;

    }

    public List<CalendarDate> getDays(){

        return days;

    }

    @Override
    public String toString(){

        return DateUtils.monthToString(month) + " " + year;

    }

}
