package team2.calendarapp;


import android.os.Bundle;
import android.support.design.widget.NavigationView;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;

public class BaseView extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    protected DrawerLayout mDrawer;
    protected FrameLayout mContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        loadCalendar();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContainer = findViewById(R.id.content_container);
        mDrawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
<<<<<<< HEAD

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        CreateEvent day = new CreateEvent();
        Calendar start = Calendar.getInstance();
        start.set(1998, 1, 1, 10, 0);
        Calendar end = Calendar.getInstance();
        end.set(1998, 1, 1, 15, 0);
        Event event = new Event("Name", "description", "location", start, end, 0);
        EventDB.addEvent(event);
        Bundle args = new Bundle();
        args.putSerializable("Event", event);
        day.setArguments(args);
        transaction.replace(R.id.fragment_container,day);
        transaction.commit();
=======
        getSupportFragmentManager().beginTransaction().replace(R.id.content_container,(new MonthView())).addToBackStack("fragBack").commit();
>>>>>>> aa66e394ef0a3683bf741e58b4d4a8f5502628b6
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent fragment_day in AndroidManifest.xml.
        switch(item.getItemId()){
            case R.id.day_view:
                mDrawer.closeDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        //Allows navigation to month view when appropriate
        //option chosen from navigation bar
        if(manager.getBackStackEntryCount() > 1){
            manager.popBackStack();
        }
        if (id == R.id.week_view) {
            WeekView week = new WeekView();
            transaction.replace(R.id.content_container,week).addToBackStack("fragBack").commit();

        } else if (id == R.id.day_view) {
            DayView day = new DayView();
            transaction.replace(R.id.content_container,day).addToBackStack("fragBack").commit();
        } else if (id == R.id.agenda_view) {
            AgendaView agendaView = new AgendaView();
            transaction.replace(R.id.content_container,agendaView).addToBackStack("fragBack").commit();
        }
        mDrawer.closeDrawer(Gravity.START);
        return true;
    }

    public void saveCalendar(){
        try {
            FileOutputStream fileOut = new FileOutputStream("events.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(EventDB.getEvents());
            out.close();
            fileOut.close();

            fileOut = new FileOutputStream("categories.txt");
            out = new ObjectOutputStream(fileOut);
            out.writeObject(Event.getCategories());
            out.close();
            fileOut.close();
        }
        catch(IOException e){}
    }

    public void loadCalendar(){
        try{
            FileInputStream fileIn = new FileInputStream("events.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            EventDB.loadEventList((Event[]) in.readObject());
            in.close();
            fileIn.close();

            fileIn = new FileInputStream("events.txt");
            in = new ObjectInputStream(fileIn);
            Event.setCategories((Category[]) in.readObject());
            in.close();
            fileIn.close();
        }
        catch(IOException e){}
        catch(ClassNotFoundException e){}
    }
}
