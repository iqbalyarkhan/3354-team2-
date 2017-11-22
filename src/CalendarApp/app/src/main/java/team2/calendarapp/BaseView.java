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

public class BaseView extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    protected DrawerLayout mDrawer;
    protected FrameLayout mContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
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
        getSupportFragmentManager().beginTransaction().replace(R.id.content_container,(new MonthView())).addToBackStack("fragBack").commit();
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
}
