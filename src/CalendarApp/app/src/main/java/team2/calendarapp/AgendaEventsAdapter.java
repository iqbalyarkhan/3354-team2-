package team2.calendarapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * This class formats the custom layout by creating an AgendaEventsAdapter that sets the text to
 * be displayed in the AgendaView
 */

public class AgendaEventsAdapter extends ArrayAdapter<Event>{
    // agendaEventsAdapter default constructor
    public AgendaEventsAdapter (Context context, Event[] agendaEventsArrayList){
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
       Event agendaEvent = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_view_layout, parent, false);
        }
        TextView eventName = (TextView)convertView.findViewById(R.id.eventNameDisplay);
        TextView eventTime = convertView.findViewById(R.id.eventTimeDisplay);
        if(agendaEvent != null){
            eventName.setText(agendaEvent.getName());
            //eventTime.setText(agendaEvent.getStart() + " - "+ agendaEvent.getEnd());

            Calendar calendarStart  = (Calendar) agendaEvent.getStart();
            Date start = calendarStart.getTime();
            String startHours = Integer.toString(calendarStart.get(Calendar.HOUR));
            String startMinutes = Integer.toString(calendarStart.get(Calendar.MINUTE));
            String startMonth = Integer.toString(calendarStart.get(Calendar.MONTH) + 1);
            String startDay = Integer.toString(calendarStart.get(Calendar.DAY_OF_MONTH));
            String startYear = Integer.toString(calendarStart.get(Calendar.YEAR));

            String startInfo = startMonth+"/"+startDay+"/"+startYear+" "+startHours+":"+startMinutes;


            Calendar calendarEnd = (Calendar) agendaEvent.getEnd();
            Date end = calendarEnd.getTime();
            String endHours = Integer.toString(calendarEnd.get(Calendar.HOUR));
            String endMinutes = Integer.toString(calendarEnd.get(Calendar.MINUTE));
            String endMonth = Integer.toString(calendarEnd.get(Calendar.MONTH) + 1);
            String endDay = Integer.toString(calendarEnd.get(Calendar.DAY_OF_MONTH));
            String endYear = Integer.toString(calendarEnd.get(Calendar.YEAR));

            String endInfo = endHours+":"+endMinutes;

            eventTime.setText(startInfo+" - "+endInfo);

        }
        return convertView;
    }

}
