package team2.calendarapp;

import android.graphics.Rect;
import android.support.design.widget.FloatingActionButton;
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

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DayView extends Fragment implements View.OnClickListener {

    private FrameLayout eventContainer;

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
        addButton = getActivity().findViewById(R.id.floatingActionButton);
        if(addButton != null)
            addButton.setOnClickListener(this);
        Bundle bundle = this.getArguments();

        return root;
    }

    public void onClick(View v){
        getActivity().getFragmentManager().beginTransaction().add(R.id.drawer_layout,new CreateEvent()).addToBackStack(null).commit();
    }

    private void drawEvents(){
        eventContainer.removeAllViews();
        Event[] arr = EventDB.getInstance().getEventsInRange(Calendar.getInstance());
        EventView event;
        for (Event e: arr){
            if(e == null)
                return;
            event = new EventView(getActivity());
            event.setEvent(e);
            eventContainer.addView(event);
        }
    }
}
