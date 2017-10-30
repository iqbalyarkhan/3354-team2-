package team2.calendarapp;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DayView extends BaseView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_drawer);
        ConstraintLayout constraintLayout = findViewById(R.id.constraint); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.acitivity_day, constraintLayout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(new SimpleDateFormat("MM/dd").format(new Date()));
    }
}
