package team2.calendarapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Andrew on 11/21/2017.
 */

public class WeekView extends Fragment {
    private enum weekDays{SUN,MON,TUES,WED,THUR,FRI,SAT}
    private ArrayList<WeekBarDay> week;
    private ArrayList<FrameLayout> eventContainer;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_week, container, false);
        week = new ArrayList<WeekBarDay>();
        eventContainer = new ArrayList<FrameLayout>();
        addControls(root);
        drawEvents(this.getArguments());
        return root;
    }

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

    }
    /*private void getRange(String number){
        int day;
        try{
            day = Integer.parseInt(number);
        }catch (Exception e){
            return;
        }
        int low = (day/7) * 7;
        int high = (int) Math.ceil(day/7.0) * 7;

        for(int i = low; i < high; i++){
            week.get(i).setDayNumber(Integer.toString(i));
        }
    } */

    private void drawEvents(Bundle b){
        EventDB eventDB = EventDB.getInstance();
        clearViews();
        EventView event;
        if(b == null)
            return;
        String[] parts = b.getString("dateRange").split("/");
        String first = parts[0];
        String last = parts[1];
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        try{
            start.setTime(new SimpleDateFormat("mm-dd-yyyy").parse(first));
            end.setTime(new SimpleDateFormat("mm-dd-yyyy").parse(last));
        }catch(java.text.ParseException e){
            return;
        }

        Event[] arr = eventDB.getEventsInRange(start,end);
        for(Event e : arr){
            if(e == null) return;
            event = new EventView(getActivity());
            event.setEvent(e);
            eventContainer.get(e.getStart().get(Calendar.DAY_OF_WEEK) - 1).addView(event);
        }
    }

    private void clearViews(){
        for(FrameLayout f : eventContainer){
            f.removeAllViews();
        }
    }
}
