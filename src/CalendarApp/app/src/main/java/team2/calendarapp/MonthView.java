package team2.calendarapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

/*//Activity which is a month view
public class MonthView extends AppCompatActivity {

    //To hold the current date which will be highlighted
    //in the month view
    private TextView currentDate;


    *//**
 * To initialize the month view activity
 * @param - the saved instance
 *//*
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentDate = (TextView) findViewById(R.id.date);

        *//*Intent receive = getIntent();
        String date = receive.getStringExtra("date");
        currentDate.setText(date);*//*
        DayView dayView = new DayView();
        Intent dayIntent = new Intent(this, DayView.class );
        startActivityForResult(dayIntent, 0);

    }

}*/

public class MonthView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent receive = getIntent();

        //To handle events
        HashSet<Date> events = new HashSet<>();
        events.add(new Date());

        //Updates the calendar view with relevant events
        MainCalendarView cv = ((MainCalendarView)findViewById(R.id.calendar_view));
        cv.updateCalendar(events);

        //On long press the date is displayed as a toast
        //must change to day view on long click
        cv.setEventHandler(new MainCalendarView.EventHandler() {
            @Override
            public void onDayLongPress(Date date) {
                //Toast.makeText(MonthView.this, "No events for today", Toast.LENGTH_SHORT).show();

                //Gets the current date and extracts information to be passed to the day view
                Calendar currDate = Calendar.getInstance();
                currDate.setTime(date);
                String sMonth = Integer.toString(currDate.get(Calendar.MONTH) + 1);
                String sDayNumber = Integer.toString(currDate.get(Calendar.DAY_OF_MONTH));
                String sDayName = Integer.toString(currDate.get(Calendar.DAY_OF_WEEK));
                String sYear = Integer.toString(currDate.get(Calendar.YEAR));

                String currentDate = sMonth +  "/" + sDayNumber + "/" + sYear;
                Toast.makeText(MonthView.this,currentDate,Toast.LENGTH_LONG).show();

                //Bundle to transfer date information to DayView
                Bundle dateInformation = new Bundle();
                dateInformation.putString("dateInformation", currentDate);
                DayView dayView = new DayView();
                dayView.setArguments(dateInformation);


            }
        });
    }

}

