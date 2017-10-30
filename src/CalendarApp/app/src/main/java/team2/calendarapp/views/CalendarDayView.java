package team2.calendarapp.views;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import team2.calendarapp.R;
import team2.calendarapp.objects.CalendarDate;

/**
 * Created by iqbalkhan on 10/29/17.
 */

public class CalendarDayView extends LinearLayout{

    private CalendarDate calendarDate;
    private TextView textDay;
    private View layoutBackground;

    public CalendarDayView(Context context, CalendarDate cDate){

        super(context);
        calendarDate = cDate;
        initialize();

    }

    private void initialize(){

        inflate(getContext(), R.layout.view_calendar_day, this);
        layoutBackground = findViewById(R.id.view_calendar_day_layout_background);
        textDay = (TextView) findViewById(R.id.view_calendar_day_text);
        textDay.setText("" + calendarDate.getDay());

    }

    public CalendarDate getDate(){

        return calendarDate;

    }

    public void setThisMonthTextColor(){

        textDay.setTextColor(ContextCompat.getColor(getContext(), R.color.white));

    }

    public void setOtherMonthTextColor(){

        textDay.setTextColor(ContextCompat.getColor(getContext(), R.color.grey));

    }

    public void setPurpleSolidOvalBackground(){


        layoutBackground.setBackgroundResource(R.drawable.oval_purple_solid);

    }

    public void unsetPurpleSolidOvalBackground(){

        layoutBackground.setBackgroundResource(R.drawable.oval_black_solid);

    }

}
