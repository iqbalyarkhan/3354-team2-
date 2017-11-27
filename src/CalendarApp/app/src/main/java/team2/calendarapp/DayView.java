package team2.calendarapp;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.app.Fragment;
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
        dayContainer = root.findViewById(R.id.day_container);
        eventContainer = root.findViewById(R.id.event_container);
        mToolbar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        mToolbar.setTitle("DayView");
        drawDays();


        return root;
    }

    private void drawDays(){
        HourView hourView;
        dayContainer.removeAllViews();
        for(int i = 0; i < 24; i++){
            hourView = new HourView(getActivity());
            hourView.setTime(String.format("%1$2s:00",i));
            dayContainer.addView(hourView);
        }
    }
    private void drawEvents(){
        eventContainer.removeAllViews();

    }

}
