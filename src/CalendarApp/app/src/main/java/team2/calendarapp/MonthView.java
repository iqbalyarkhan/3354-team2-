package team2.calendarapp;


import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

/**
 * Class monthview that deals with rendering, interacting
 * and chaing the said view.
 */

public class MonthView extends Fragment {

    private MainCalendarView.WeekHandler weekHandler = null;
    private MainCalendarView.DayHandler dayHandler = null;
    private Calendar currentDate = Calendar.getInstance();
    private ActionBar mToolbar;
    private EventDB db = EventDB.getInstance();
    private ImageButton addButton;

    /**
     * Mehtod to create the Month view
     * @param inflater - Inflates current view
     * @param container - contains the layout for month view
     * @param savedInstanceState - saved instance for current view
     * @return - returns the created month view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_month,container,false);

        /*final LinearLayout monthContainer;
        monthContainer = root.findViewById(R.id.month_container);*/

        //To handle events
        HashSet<Date> events = new HashSet<>();
        events.add(new Date());

        //Updates the calendar view with relevant events
        MainCalendarView cv = ((MainCalendarView)root.findViewById(R.id.calendar_view));
        cv.updateCalendar(events);
        addButton = getActivity().findViewById(R.id.addButton);
        addButton.setOnClickListener(new ImageButton.OnClickListener(){
            public void onClick(View v){
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_container,new CreateEvent(),"createEvent").addToBackStack("fragBack").commit();
            }
        });
        if((mToolbar = ((AppCompatActivity) getActivity()).getSupportActionBar()) != null){
            mToolbar.setTitle("Calendar");
        }
        //On long press the date range is displayed as a toast.
        cv.setWeekHandler(new MainCalendarView.WeekHandler() {
            @Override
            public void onDayLongPress(Date date) {
                //Toast.makeText(MonthView.this, "No events for today", Toast.LENGTH_SHORT).show();
                //Gets current week dates in string format
                //String currWeekDates = getWeekDates(date);
                //System.out.println(currWeekDates);
                //Toast.makeText(getContext(), currWeekDates, Toast.LENGTH_SHORT).show();
                //Bundle to pass to week view that holds the current week dates
                Bundle b = new Bundle();

                //Returns current date based on user's click
                b.putLong("week",date.getTime());
                WeekView week = new WeekView();
                week.setArguments(b);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_container,week,"weekView").addToBackStack("fragBack").commit();


            }
        });

        //To handle current day selected: Returns a bundle of current date
        //based on the day clicked on by the user
        cv.setDayHandler(new MainCalendarView.DayHandler() {
            @Override
            public void onDayPress(Date date) {

                Bundle b = new Bundle();

                //Returns current date based on user's click
                b.putLong("day",date.getTime());
                DayView day = new DayView();
                day.setArguments(b);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_container,day,"dayView").addToBackStack("fragBack").commit();

            }
        });

        return root;
    }

    /**
     * Getter Method to return the class variable currentDate
     * @return - the current date based on user's selected date
     */
    public Calendar getCurrentDate(){

        return currentDate;

    }

    /**
     * Method to extract current week's start and end dates
     * @param date - the current date selected by the user
     * @return - String that holds current weeks start and end date in the format:
     *      "MM-DD till MM-DD"
     */
    public Bundle getWeekDates(Date date){

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
        SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        String start = df.format((first.getTime()));
        String end = df.format(last.getTime());
        Bundle bundle = new Bundle();
        bundle.putString("dateRange",start + "/" + end);
        return bundle;

    }

}

