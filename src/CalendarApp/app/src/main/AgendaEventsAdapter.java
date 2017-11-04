package team2.calendarapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * This class formats the custom layout by creating an AgendaEventsAdapter that sets the text to
 * be displayed in the AgendaView
 */

public class AgendaEventsAdapter extends ArrayAdapter<AgendaEvents>{
    // agendaEventsAdapter default constructor
    public AgendaEventsAdapter (Context context, ArrayList<AgendaEvents> agendaEventsArrayList){
        super(context,0,agendaEventsArrayList);
    }

    /**
     * getView gets the custom view for AgendaView and sets the text for events
     * @author Anmol Agarwal
     * @param position is the location for the event
     * @param convertView is the custom AgendaView layout using the adapter_view_layout
     * @param parent the view group
     * @return convertView which is modified custom view that has text of AgendaEvents
     */
    public View getView(int position, View convertView, ViewGroup parent){
        AgendaEvents agendaEvent = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_view_layout, parent, false);
        }
        TextView eventName = (TextView)convertView.findViewById(R.id.eventNameDisplay);
        TextView eventTime = convertView.findViewById(R.id.eventTimeDisplay);
        eventName.setText(agendaEvent.getEventName());
        eventTime.setText(agendaEvent.getStartTime() + " - "+ agendaEvent.getEndTime());

        return convertView;
    }
}
