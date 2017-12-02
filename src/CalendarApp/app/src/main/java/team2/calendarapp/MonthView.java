package team2.calendarapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

public class MonthView extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.activity_main,container,false);

        //To handle events
        HashSet<Date> events = new HashSet<>();
        events.add(new Date());

        //Updates the calendar view with relevant events
        MainCalendarView cv = ((MainCalendarView)root.findViewById(R.id.calendar_view));
        cv.updateCalendar(events);

        //On long press the date range is displayed as a toast.
        cv.setEventHandler(new MainCalendarView.EventHandler() {
            @Override
            public void onDayLongPress(Date date) {
                //Toast.makeText(MonthView.this, "No events for today", Toast.LENGTH_SHORT).show();
                //Gets current week dates in string format
                String currWeekDates = getWeekDates(date);
                //System.out.println(currWeekDates);
                Toast.makeText(getContext(), currWeekDates, Toast.LENGTH_SHORT).show();


            }
        });
        return root;
    }

    /**
     * Method to extract current week's start and end dates
     * @param date - the current date selected by the user
     * @return - String that holds current weeks start and end date in the format:
     *      "MM-DD till MM-DD"
     */
    public String getWeekDates(Date date){

        //Sets the date to what the user has clicked
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        //Gets the start date of the week
        Calendar first = (Calendar) cal.clone();
        first.add(Calendar.DAY_OF_WEEK,
                first.getFirstDayOfWeek() - first.get(Calendar.DAY_OF_WEEK));

        //Adds six days to start date to get the end date
        Calendar last = (Calendar) first.clone();
        last.add(Calendar.DAY_OF_YEAR, 6);

        //Combines the two and returns the date range
        SimpleDateFormat df = new SimpleDateFormat("MM-dd-YYYY");
        String start = df.format((first.getTime()));
        String end = df.format(last.getTime());
        String currWeekDates = start + " till " + end;
        return currWeekDates;

    }

}

