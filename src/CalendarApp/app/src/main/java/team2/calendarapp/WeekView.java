package team2.calendarapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Andrew on 11/21/2017.
 */

public class WeekView extends Fragment {
    private enum weekDays{SUN,MON,TUES,WED,THUR,FRI,SAT}
    private ArrayList<WeekBarDay> week;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_week, container, false);
        week = new ArrayList<WeekBarDay>();
        addControls(root);
        setRange("5");
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
    }
    private void setRange(String number){
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
    }
}
