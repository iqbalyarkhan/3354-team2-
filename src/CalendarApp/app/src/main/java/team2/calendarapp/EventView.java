package team2.calendarapp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

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

    }
    public void setEvent(Event event){
        title.setText(event.getName());
        eventDescription.setText(event.getDescription());
        //container.setBackgroundColor(event.getColor);
    }
    
}
