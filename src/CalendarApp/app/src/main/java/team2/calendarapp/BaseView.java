package team2.calendarapp;


import android.content.Context;
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
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Date;


/**
Base view reads, loads, and stores the user's calendar events from a file. This view also creates the calendar toolbar
which allows the user to switch to different calendar views.
*/
public class BaseView extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    protected DrawerLayout mDrawer;
    protected FrameLayout mContainer;
    private EventDB database = EventDB.getInstance();
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
        
        // calendar starts on MonthView
        getSupportFragmentManager().beginTransaction().replace(R.id.content_container,(new MonthView()),"Month").commit();
    }

    /**
     * Calls the saveCalendar() function to routinely save the calendar events to the EventDB
     */
    @Override
    public void onPause(){
        super.onPause();
        saveCalendar();
    }



    /**
     * Method to handle click on options menu.
     * @param item - To hold the menu item being selected
     * @return - returns true if view change was successful.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent fragment_day in AndroidManifest.xml.
        switch(item.getItemId()){
            case R.id.day_view:
                mDrawer.closeDrawer(GravityCompat.START);
                return true;
            case R.id.week_view:
                mDrawer.closeDrawer(GravityCompat.START);
                return true;
            case R.id.agenda_view:
                mDrawer.closeDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    /**
    * onNavigationItemSelected determines what view is selected by the user using the toolbar and then switches to that view
    * @param item - The item selected from the menu
    */

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Bundle b = new Bundle();
        //Allows navigation to different views
        //option chosen from navigation bar
        if(manager.getBackStackEntryCount() > 0){
            manager.popBackStack();
        }
        //MonthView month = (MonthView) manager.findFragmentByTag("Month");
        
        
        // Determines what view is clicked, creates a new instance of that view, and then displays it
        if (id == R.id.week_view) {
            WeekView week = new WeekView();
            b.putLong("week",Calendar.getInstance().getTimeInMillis());
            week.setArguments(b);
            transaction.replace(R.id.content_container,week).addToBackStack("fragBack").commit();
        } else if (id == R.id.day_view) {
            DayView day = new DayView();
            b.putLong("day",Calendar.getInstance().getTimeInMillis());
            day.setArguments(b);
            transaction.replace(R.id.content_container,day).addToBackStack("fragBack").commit();
        } else if (id == R.id.agenda_view) {
            AgendaView agendaView = new AgendaView();
            transaction.replace(R.id.content_container,agendaView).addToBackStack("fragBack").commit();
        }
        mDrawer.closeDrawer(Gravity.START);
        return true;
    }

    /**
     * Saves the calendar state
     */
    public void saveCalendar(){
        /**
        * Reads the calendar events from the file and save the calendar events to the EventDB
        * Also reads the event categories from categories.txt and saves the categories with the event
        * @throws IOException if the files are not able to be read or opened 
        */
        try {
            FileOutputStream fileOut = openFileOutput("events.txt", Context.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(database.getEvents());
            out.close();
            fileOut.close();

            fileOut = openFileOutput("categories.txt", Context.MODE_PRIVATE);
            out = new ObjectOutputStream(fileOut);
            out.writeObject(Event.getCategories());
            out.close();
            fileOut.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
/**
* loadCalendar loads the calendar events from events.txt and event categories from categories.txt by reading the files
* @throws IOException if the files are not read successfully 
* @throws ClassNotFoundException if the EventDB class is no longer found 
*/
    public void loadCalendar(){
        try{
            FileInputStream fileIn = openFileInput("events.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            database.loadEventList((Event[]) in.readObject());
            in.close();
            fileIn.close();

            fileIn = openFileInput("categories.txt");
            in = new ObjectInputStream(fileIn);
            Event.setCategories((Category[]) in.readObject());
            in.close();
            fileIn.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

}
