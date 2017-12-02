package team2.calendarapp;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by Andrew on 10/31/2017.
 */

public class EventView extends LinearLayout {
    private TextView title;
    private TextView eventDescription;
    private LinearLayout container;
    public EventView(Context context){
        super(context);
        initControls(context);
    }
    public EventView(Context context, AttributeSet attrs){
        super(context,attrs);
        initControls(context);
    }
    public EventView(Context context, AttributeSet attrs, int defStyle){
        super(context,attrs,defStyle);
        initControls(context);
    }
    private void initControls(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_event,this);
        title = findViewById(R.id.event_title);
        eventDescription = findViewById(R.id.event_description);
        container = findViewById(R.id.event_container_individual);
    }
    public void setEvent(Event event){
        title.setText(event.getName());
        eventDescription.setText(event.getDescription());
        container.setBackgroundColor(Event.getCategories()[event.getCategory()].getColor());
        setPos(event);
    }
    private void setPos(Event e){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        int start = e.getStart().get(Calendar.HOUR_OF_DAY) * 60 + e.getStart().get(Calendar.MINUTE);
        int end = e.getEnd().get(Calendar.HOUR_OF_DAY) * 60 + e.getEnd().get(Calendar.MINUTE);
        params.topMargin = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, start,getResources().getDisplayMetrics()));
        params.height = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, end - start ,getResources().getDisplayMetrics()));
        setLayoutParams(params);
    }
}
