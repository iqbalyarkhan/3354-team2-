package team2.calendarapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

//import team2.calendarapp.R;
import team2.calendarapp.R;

import team2.calendarapp.interfaces.OnDateSelectedListener;
import team2.calendarapp.objects.CalendarDate;
import team2.calendarapp.views.CustomCalendarView;

public class MonthView extends AppCompatActivity implements OnDateSelectedListener {

    //Defining private views for each element
    private CustomCalendarView customCalendar;
    private TextView textForDay;
    private TextView textForDayOfWeek;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Defining the text views
        textForDay = (TextView) findViewById(R.id.activity_main_text_day_of_month);
        textForDayOfWeek = (TextView) findViewById(R.id.activity_main_text_day_of_week);
        customCalendar = (CustomCalendarView) findViewById(R.id.activity_main_view_custom_calendar);
        customCalendar.setOnDateSelectedListener(this);

    }

    @Override
    public void onDateSelected(CalendarDate date) {
        textForDay.setText(date.dayToString());
        textForDayOfWeek.setText(date.dayOfWeekToStringName());
    }
}
