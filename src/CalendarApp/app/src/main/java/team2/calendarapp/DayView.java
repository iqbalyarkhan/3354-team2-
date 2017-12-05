package team2.calendarapp;

import android.graphics.Rect;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * The class to control day view layout and functionalities
 */

public class DayView extends Fragment {

    private FrameLayout eventContainer;
    private ConstraintLayout dayContainer;
    private ImageButton addButton;
    private ActionBar mToolbar;
    private EventDB db = EventDB.getInstance();

    /**
     * Class to handle day view on creation
     * @param inflater - Inflates the view on creation
     * @param container - Holds the current day view
     * @param savedInstanceState - saved instance of the application
     * @return - Returns the day view with properly inflated elements
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_day,container,false);
        mToolbar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        eventContainer = root.findViewById(R.id.event_container);
        dayContainer = root.findViewById(R.id.day_container);


        addButton = getActivity().findViewById(R.id.addButton);
        addButton.setOnClickListener(new ImageButton.OnClickListener(){
            public void onClick(View v){
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_container,new CreateEvent(),"createEvent").addToBackStack("fragBack").commit();
            }
        });
        drawEvents(this.getArguments());
        return root;
    }

    /**
     * Method to draw events based on the bundle of events passed
     * @param bundle - Bundle that holds the date in milliseconds
     */
    private void drawEvents(Bundle bundle){
        eventContainer.removeAllViews();

        //create a starting time for a day
        //grabs the time passed in a bundle and sets it to the beginning of that day
        Calendar start = Calendar.getInstance();
        start.setTimeInMillis(bundle.getLong("day"));
        setTimeInToolbar(start);
        start.set(Calendar.HOUR_OF_DAY, 0);
        start.clear(Calendar.MINUTE);
        start.clear(Calendar.SECOND);
        start.clear(Calendar.MILLISECOND);

        //create ending boundary for searching
        //sets the passed in date to the end of that day
        Calendar end = Calendar.getInstance();
        end.setTimeInMillis(bundle.getLong("day"));
        end.set(Calendar.HOUR_OF_DAY, 23);
        end.set(Calendar.MINUTE, 59);
        end.set(Calendar.SECOND, 59);

        //dynamically draw events
        Event[] arr = EventDB.getInstance().getEventsInRange(start,end);
        System.out.println(arr.length);
        EventView eventView;
        for (Event e: arr){
            if(e == null)
                continue;
            eventView = new EventView(getActivity());
            eventView.setEvent(e);
            setClick(eventView,e);
            eventContainer.addView(eventView);
        }
    }

    private void setTimeInToolbar(Calendar cal){
        if(mToolbar != null)
            mToolbar.setTitle(cal.getDisplayName(Calendar.MONTH,Calendar.LONG, Locale.ENGLISH) + " " + cal.get(Calendar.DAY_OF_MONTH) + ", " + cal.get(Calendar.YEAR));
    }
    /**
     * Method to handle editing events in day view
     * @param eventView - The event view in the current day
     * @param event - The actual event set for the current day
     */
    private void setClick(EventView eventView, final Event event){

        eventView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateEvent ce = new CreateEvent();
                Bundle b= new Bundle();
                b.putSerializable("Event",event);
                ce.setArguments(b);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_container,ce,"editEvent").addToBackStack("fragBack").commit();
            }
        });
    }
}
