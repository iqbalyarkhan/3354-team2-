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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * The class to control day view layout and functionalities
 */

public class DayView extends Fragment {

    private FrameLayout eventContainer;
    private ConstraintLayout dayContainer;
    private FloatingActionButton addButton;
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
        ActionBar mToolbar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if(mToolbar != null)
            mToolbar.setTitle("DayView");
        eventContainer = root.findViewById(R.id.event_container);
        addButton = root.findViewById(R.id.floatingActionButton);
        dayContainer = root.findViewById(R.id.day_container);
        addButton.setOnClickListener(new FloatingActionButton.OnClickListener(){
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
     * @param bundle - Bundle that holds the event information
     */
    private void drawEvents(Bundle bundle){
        eventContainer.removeAllViews();


        Calendar start = Calendar.getInstance();
        start.setTimeInMillis(bundle.getLong("day"));
        start.set(Calendar.HOUR_OF_DAY, 0);
        start.clear(Calendar.MINUTE);
        start.clear(Calendar.SECOND);
        start.clear(Calendar.MILLISECOND);


        Calendar end = Calendar.getInstance();
        end.setTimeInMillis(bundle.getLong("day"));
        end.set(Calendar.HOUR_OF_DAY, 23);
        end.set(Calendar.MINUTE, 59);
        end.set(Calendar.SECOND, 59);


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

    /**
     * Method to specify activities for day clicks
     * @param eventView - The event view for current day
     * @param event - The acutal event set for the current day
     */
    private void setClick(EventView eventView, final Event event){

        eventView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dayContainer.removeAllViews();
                CreateEvent ce = new CreateEvent();
                Bundle b= new Bundle();
                b.putSerializable("Event",event);
                ce.setArguments(b);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.day_container,ce,"editEvent").commit();
            }
        });
    }
}
