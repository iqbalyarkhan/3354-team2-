package team2.calendarapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CalendarView;

public class MonthLayoutActivity extends AppCompatActivity  {

    //Holds type of view - monthView
    private CalendarView monthView;

    /**
     * To initialize the month view activity
     * @param savedInstanceState - the current instance of the calendar
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_layout);
        monthView = (CalendarView) findViewById(R.id.calendarView);


        //TODO: Change view from Month to day view when a day is double-clicked
        monthView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {

            }
        });

    }

}
