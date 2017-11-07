package team2.calendarapp;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
public class DayView extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    protected LayoutManagerType mLayoutManagerType;
    ArrayList<Event> events = new ArrayList<>();
    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.
        initData();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.activity_day,container,false);
        mRecyclerView = root.getRootView().findViewById(R.id.day_recycler);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        if(savedInstanceState != null){
            mLayoutManagerType = (LayoutManagerType) savedInstanceState.getSerializable("layoutManager");
        }
        setRecyclerViewManager(mLayoutManagerType);
        mAdapter = new DayAdapter(events);
        mRecyclerView.setAdapter(mAdapter);
        ((SimpleItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);



        return root;
    }

    public void setRecyclerViewManager(LayoutManagerType layoutManagerType){
        int scrollPos = 0;
        if(mRecyclerView.getLayoutManager() != null){
            scrollPos = ((LinearLayoutManager) mRecyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
        }
        switch(layoutManagerType){
            case GRID_LAYOUT_MANAGER:
                mLayoutManager = new GridLayoutManager(getActivity(),2);
                mLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
                break;
            case LINEAR_LAYOUT_MANAGER:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                break;
            default:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        }
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(scrollPos);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putSerializable("layoutManager",mLayoutManagerType);
        super.onSaveInstanceState(savedInstanceState);
    }
    public void initData(){
        events.add(new Event("Event1","description1","startTime1","endTime1"));
        events.add(new Event("Event2","description2","startTime2","endTime2"));
        events.add(new Event("Event1","description1","startTime1","endTime1"));
        events.add(new Event("Event2","description2","startTime2","endTime2"));
        events.add(new Event("Event1","description1","startTime1","endTime1"));
        events.add(new Event("Event2","description2","startTime2","endTime2"));
        events.add(new Event("Event1","description1","startTime1","endTime1"));
        events.add(new Event("Event2","description2","startTime2","endTime2"));
        events.add(new Event("Event1","description1","startTime1","endTime1"));
        events.add(new Event("Event2","description2","startTime2","endTime2"));
    }
}
