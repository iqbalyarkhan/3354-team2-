package iq.com.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //To handle events
        HashSet<Date> events = new HashSet<>();
        events.add(new Date());

        //Updates the calendar view with relevant events
        CalendarView cv = ((CalendarView)findViewById(R.id.calendar_view));
        cv.updateCalendar(events);

        //On long press the date is displayed as a toast
        //must change to day view on long click
        cv.setEventHandler(new CalendarView.EventHandler() {
            @Override
            public void onDayLongPress(Date date) {
                DateFormat df = SimpleDateFormat.getDateInstance();
                //Toast.makeText(MainActivity.this, df.format(date), Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, "No events for today", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //@Override - not needed since we already have a toolbar to access different views
    /*public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }*/

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item){

        int id = item.getItemId();

        if(id == R.id.action_settings){

            return true;

        }

        return super.onOptionsItemSelected(item);

    }*/
}
