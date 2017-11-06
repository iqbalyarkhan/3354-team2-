package team2.calendarapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
public class DayView extends BaseView {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Event> events = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);
        events.add(new Event("Event1","description1","startTime1","endTime1"));
        events.add(new Event("Event2","description2","startTime2","endTime2"));
        events.add(new Event("Event3","description3","startTime3","endTime3"));
        events.add(new Event("Event4","description4","startTime4","endTime4"));
        events.add(new Event("Event5","description5","startTime5","endTime5"));
        events.add(new Event("Event6","description6","startTime6","endTime6"));
        events.add(new Event("Event7","description7","startTime7","endTime7"));
        events.add(new Event("Event8","description8","startTime8","endTime8"));
        events.add(new Event("Event9","description9","startTime9","endTime9"));
        events.add(new Event("Event10","description10","startTime10","endTime10"));
        events.add(new Event("Event11","description11","startTime11","endTime11"));
        events.add(new Event("Event12","description12","startTime12","endTime12"));
        events.add(new Event("Event13","description13","startTime13","endTime13"));
        events.add(new Event("Event14","description14","startTime14","endTime14"));
        events.add(new Event("Event15","description15","startTime15","endTime15"));
        events.add(new Event("Event16","description16","startTime16","endTime16"));
        events.add(new Event("Event17","description17","startTime17","endTime17"));
        events.add(new Event("Event1","description1","startTime1","endTime1"));
        events.add(new Event("Event2","description2","startTime2","endTime2"));
        events.add(new Event("Event3","description3","startTime3","endTime3"));
        events.add(new Event("Event4","description4","startTime4","endTime4"));
        events.add(new Event("Event5","description5","startTime5","endTime5"));
        events.add(new Event("Event6","description6","startTime6","endTime6"));
        events.add(new Event("Event7","description7","startTime7","endTime7"));
        events.add(new Event("Event8","description8","startTime8","endTime8"));
        events.add(new Event("Event9","description9","startTime9","endTime9"));
        events.add(new Event("Event10","description10","startTime10","endTime10"));
        events.add(new Event("Event11","description11","startTime11","endTime11"));
        events.add(new Event("Event12","description12","startTime12","endTime12"));
        events.add(new Event("Event13","description13","startTime13","endTime13"));
        events.add(new Event("Event14","description14","startTime14","endTime14"));
        events.add(new Event("Event15","description15","startTime15","endTime15"));
        events.add(new Event("Event16","description16","startTime16","endTime16"));
        events.add(new Event("Event17","description17","startTime17","endTime17"));
        events.add(new Event("Event1","description1","startTime1","endTime1"));
        events.add(new Event("Event2","description2","startTime2","endTime2"));
        events.add(new Event("Event3","description3","startTime3","endTime3"));
        events.add(new Event("Event4","description4","startTime4","endTime4"));
        events.add(new Event("Event5","description5","startTime5","endTime5"));
        events.add(new Event("Event6","description6","startTime6","endTime6"));
        events.add(new Event("Event7","description7","startTime7","endTime7"));
        events.add(new Event("Event8","description8","startTime8","endTime8"));
        events.add(new Event("Event9","description9","startTime9","endTime9"));
        events.add(new Event("Event10","description10","startTime10","endTime10"));
        events.add(new Event("Event11","description11","startTime11","endTime11"));
        events.add(new Event("Event12","description12","startTime12","endTime12"));
        events.add(new Event("Event13","description13","startTime13","endTime13"));
        events.add(new Event("Event14","description14","startTime14","endTime14"));
        events.add(new Event("Event15","description15","startTime15","endTime15"));
        events.add(new Event("Event16","description16","startTime16","endTime16"));
        events.add(new Event("Event17","description17","startTime17","endTime17"));


        init();

    }

    public void init(){
        mRecyclerView = findViewById(R.id.day_recycler);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new DayAdapter(events);
        mRecyclerView.setAdapter(mAdapter);
    }
}
