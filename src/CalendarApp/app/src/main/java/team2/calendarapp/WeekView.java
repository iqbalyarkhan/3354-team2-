package team2.calendarapp;

import android.drm.DrmStore;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by Andrew on 11/21/2017.
 */

/**
 * Class to handle week view that can be accessed from month view
 * or from drop down menu.
 */
public class WeekView extends Fragment {

    /**
     * Private fields to properly define and render week view.
     */
    private enum weekDays{SUN,MON,TUES,WED,THUR,FRI,SAT}
    private ArrayList<WeekBarDay> week;
    private ArrayList<FrameLayout> eventContainer;
    private ConstraintLayout weekContainer;
    private ImageButton addButton;
    private ActionBar mToolbar;

    /**
     * Method to create the week view
     * @param inflater - Inflates the view to render week view
     * @param container - Contains the information for currently selected view
     * @param savedInstanceState - Saved instance of the current view
     * @return - returns the week view that was rendered
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_week, container, false);
        week = new ArrayList<WeekBarDay>();
        eventContainer = new ArrayList<FrameLayout>();
        mToolbar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        addButton = getActivity().findViewById(R.id.addButton);
        addButton.setOnClickListener(new ImageButton.OnClickListener(){
            public void onClick(View v){
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_container,new CreateEvent(),"createEvent").addToBackStack("fragBack").commit();
            }
        });
        addControls(root);
        setDate(this.getArguments());
        drawEvents(this.getArguments());
        return root;
    }

    /**
     * Method to add controls for the current week view.
     * @param root - the root view for current week view
     */
    private void addControls(View root){
        View weekBar = root.findViewById(R.id.week_bar);
        week.add((WeekBarDay) weekBar.findViewById(R.id.sunday));
        week.add((WeekBarDay) weekBar.findViewById(R.id.monday));
        week.add((WeekBarDay) weekBar.findViewById(R.id.tuesday));
        week.add((WeekBarDay) weekBar.findViewById(R.id.wednesday));
        week.add((WeekBarDay) weekBar.findViewById(R.id.thursday));
        week.add((WeekBarDay) weekBar.findViewById(R.id.friday));
        week.add((WeekBarDay) weekBar.findViewById(R.id.saturday));
        View weekEventContainer = root.findViewById(R.id.week_event_continaer);
        eventContainer.add((FrameLayout)weekEventContainer.findViewById(R.id.sun).findViewById(R.id.week_day_event_container));
        eventContainer.add((FrameLayout)weekEventContainer.findViewById(R.id.mon).findViewById(R.id.week_day_event_container));
        eventContainer.add((FrameLayout)weekEventContainer.findViewById(R.id.tue).findViewById(R.id.week_day_event_container));
        eventContainer.add((FrameLayout)weekEventContainer.findViewById(R.id.wed).findViewById(R.id.week_day_event_container));
        eventContainer.add((FrameLayout)weekEventContainer.findViewById(R.id.thu).findViewById(R.id.week_day_event_container));
        eventContainer.add((FrameLayout)weekEventContainer.findViewById(R.id.fri).findViewById(R.id.week_day_event_container));
        eventContainer.add((FrameLayout)weekEventContainer.findViewById(R.id.sat).findViewById(R.id.week_day_event_container));
        weekContainer = root.findViewById(R.id.week_container);
    }


    /**
     * Method to draw events in the week view.
     * @param bundle - Bundle to hold events for the current week selected
     */
    private void drawEvents(Bundle bundle){
        EventDB eventDB = EventDB.getInstance();
        clearViews();
        EventView eventView;
        Long currentTime = bundle.getLong("week");



        Calendar start = Calendar.getInstance();
        start.setTimeInMillis(currentTime);
        start.set(Calendar.HOUR_OF_DAY, 0);
        start.clear(Calendar.MINUTE);
        start.clear(Calendar.SECOND);
        start.clear(Calendar.MILLISECOND);
        start.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
        setTimeInToolbar(start);

        Calendar end = Calendar.getInstance();
        end.setTimeInMillis(currentTime);
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.clear(Calendar.MINUTE);
        end.clear(Calendar.SECOND);
        end.clear(Calendar.MILLISECOND);
        end.set(Calendar.DAY_OF_WEEK,Calendar.SATURDAY);


        Event[] arr = eventDB.getEventsInRange(start,end);
        for(Event e : arr){
            if(e == null) return;
            eventView = new EventView(getActivity());
            eventView.setEvent(e);
            setClick(eventView,e );
            eventContainer.get(e.getStart().get(Calendar.DAY_OF_WEEK) - 1).addView(eventView);
        }
    }

    /**
     *
     * @param cal the calendar instance that the title will be based off of
     */
    private void setTimeInToolbar(Calendar cal){
        if(mToolbar != null)
            mToolbar.setTitle(cal.getDisplayName(Calendar.MONTH,Calendar.LONG, Locale.ENGLISH) + " " + cal.get(Calendar.DAY_OF_MONTH) + ", " + cal.get(Calendar.YEAR));
    }

    /**
     *To set the dates that correspond to current week
     * @param bundle - Bundle to hold the current week's information
     */
    private void setDate(Bundle bundle){
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(bundle.getLong("week"));
        cal.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
        for(int i = 0; i < 7; i++){
            week.get(i).setDayNumber(cal.get(Calendar.DATE) + "");
            cal.add(Calendar.DAY_OF_WEEK,1);
        }
    }

    /**
     * To clear all views
     */
    private void clearViews(){
        for(FrameLayout f : eventContainer){
            f.removeAllViews();
        }
    }

    /**
     * Method to populate the current view correctly with events.
     * @param eventView - Holds the event view for current week
     * @param event - Holds the actual event for this particular view
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
