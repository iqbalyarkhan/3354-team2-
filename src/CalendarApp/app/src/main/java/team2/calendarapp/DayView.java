package team2.calendarapp;

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

public class DayView extends Fragment {

    private FrameLayout eventContainer;
    private LinearLayout dayContainer;
    private ActionBar mToolbar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_day,container,false);
        mToolbar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        mToolbar.setTitle("DayView");


        return root;
    }
    private void drawEvents(){
        eventContainer.removeAllViews();
        EventView event;
        for(int i = 0; i < EventDB.getEvents().length; i++){
            event = new EventView(getActivity());
            event.setEvent(EventDB.getEvents()[0]);
            eventContainer.addView(event);
        }
    }

}
