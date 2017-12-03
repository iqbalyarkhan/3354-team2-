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

public class DayView extends Fragment {

    private FrameLayout eventContainer;
    private ConstraintLayout dayContainer;
    private FloatingActionButton addButton;
    private EventDB db = EventDB.getInstance();

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
                dayContainer.removeAllViews();
                getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.day_container,new CreateEvent(),"createEvent").commit();
            }
        });
        drawEvents(this.getArguments());
        return root;
    }


    private void drawEvents(Bundle b){
        eventContainer.removeAllViews();
        String day = b.getString("day");
        Calendar cal = Calendar.getInstance();
        try{
            cal.setTime(new SimpleDateFormat("mm-dd-yyyy").parse(day));
        }catch(java.text.ParseException e){

        }
        Event[] arr = EventDB.getInstance().getEventsInRange(cal);
        EventView event;
        Log.d("length", arr.length + "");
        for (Event e: arr){
            if(e == null)
                return;
            event = new EventView(getActivity());
            event.setEvent(e);
            eventContainer.addView(event);
        }
    }
}
