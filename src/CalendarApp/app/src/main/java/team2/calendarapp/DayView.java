package team2.calendarapp;

import android.graphics.Rect;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;
import java.util.Calendar;

public class DayView extends Fragment {

    private FrameLayout eventContainer;
    private ActionBar mToolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_day,container,false);
        mToolbar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        eventContainer = root.findViewById(R.id.event_container);
        drawEvents();

        return root;
    }
    private void drawEvents(){
        eventContainer.removeAllViews();
        EventView event;
        Rect rectangle;
        for(int i = 0; i < EventDB.getEvents().length; i++){
            event = new EventView(getActivity());
            rectangle = new Rect();
            event.setEvent(EventDB.getEvents()[0]);
            eventContainer.addView(event);
        }
    }

    private Rect getSize(Event e){
        Rect rectangle = new Rect();
        rectangle.top = e.getStart().get(Calendar.HOUR_OF_DAY) * R.dimen.hour_height + e.getStart().get(Calendar.MINUTE) ;
        rectangle.bottom = e.getEnd().get(Calendar.HOUR_OF_DAY) * R.dimen.hour_height + e.getEnd().get(Calendar.MINUTE);
        rectangle.left = R.dimen.time_width;
        rectangle.right = eventContainer.getWidth();
        return rectangle;
    }
}
