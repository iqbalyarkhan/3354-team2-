package team2.calendarapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//Activity which is a month view
public class MonthView extends AppCompatActivity {

    //To hold the current date which will be highlighted
    //in the month view
    private TextView currentDate;


    /**
     * To initialize the month view activity
     * @param savedInstanceState - the saved instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentDate = (TextView) findViewById(R.id.date);

        /*Intent receive = getIntent();
        String date = receive.getStringExtra("date");
        currentDate.setText(date);*/
        DayView dayView = new DayView();
        Intent dayIntent = new Intent(dayView.getContext(), DayView.class );
        startActivityForResult(dayIntent, 0);

    }

}
