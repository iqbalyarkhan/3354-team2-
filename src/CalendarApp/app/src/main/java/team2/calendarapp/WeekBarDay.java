package team2.calendarapp;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;


/**
 * Created by Andrew on 11/21/2017.
 */

public class WeekBarDay extends LinearLayoutCompat {
    private TextView dayNumber;
    private TextView dayOfTheWeek;
    public WeekBarDay(Context context){
        super(context);
        init(context,null);
    }
    public WeekBarDay(Context context, AttributeSet attrs){
        super(context,attrs);
        init(context,attrs);

    }
    public WeekBarDay(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        init(context,attrs);
    }
    public void init(Context context, AttributeSet attrs){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_week_bar_day,this);

        dayNumber = findViewById(R.id.week_day_number);
        dayOfTheWeek = findViewById(R.id.week_day);

        int [] set = {android.R.attr.text};
        TypedArray a = context.obtainStyledAttributes(attrs,set);
        dayOfTheWeek.setText(a.getText(0));
        a.recycle();
    }


    public void setDayNumber(String day) {
        this.dayNumber.setText(day);
    }
    public void setDayOfTheWeek(String day){
        this.dayOfTheWeek.setText(day);
    }


}
