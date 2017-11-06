package team2.calendarapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Andrew on 10/31/2017.
 */

public class EventView extends RelativeLayout {
    public void setTitle(String title) {this.title.setText(title);}
    public void setStartTime(String startTime){this.startTime.setText(startTime);}
    public void setEndTime(String endTime){this.endTime.setText(endTime);}

    private TextView title;
    private TextView startTime;
    private TextView endTime;
    public EventView(Context context){
        super(context);
        initControl(context);
    }
    public EventView(Context context, AttributeSet attrs){
        super(context,attrs);
        initControl(context);
    }
    public EventView(Context context, AttributeSet attrs, int defStyle){
        super(context,attrs,defStyle);
        initControl(context);
    }
    private void initControl(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.event_view,this);
        title = findViewById(R.id.event_title);
        startTime = findViewById(R.id.start_time);
        endTime = findViewById(R.id.end_time);
    }

    private class EventAdapter extends ArrayAdapter<Event>{
        private LayoutInflater inflater;
        public EventAdapter(Context context, ArrayList<Event> events){
            super(context,R.layout.event_view,events);
            inflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent){
            Event event = getItem(position);
            if(view == null){
                view = inflater.inflate(R.layout.event_view, parent, false);
            }
            //((TextView)view).setText(event.getName());
            /*((TextView)view).setText(event.getStart());
            ((TextView)view).setText(event.getStart());*/
            return view;
        }

    }
}
