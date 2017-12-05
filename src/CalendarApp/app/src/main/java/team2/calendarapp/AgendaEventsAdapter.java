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

           //Calendar start = agendaEvent.getStart();
            String startInfo = getEventTimes(agendaEvent.getStart());
            String endInfo = getEventTimes(agendaEvent.getEnd());
            String startDate = getEventDate(agendaEvent.getStart());
            String timeDisplay = startDate+"  "+startInfo+" - "+endInfo;

            eventTime.setText(timeDisplay);

        }
        return convertView;
    }

    /**
     * Method to get start and end times for an event to be displayed in the agenda view
     * @param currentEvent - The current event that is being analyzed
     * @return - String with current event's times
     */
    public String getEventTimes(Calendar currentEvent){

        //Calendar eventCalendar  = (Calendar) currentEvent.getStart();
        String startHours = Integer.toString(currentEvent.get(Calendar.HOUR));
        String startMinutes = Integer.toString(currentEvent.get(Calendar.MINUTE));

        if (startMinutes.equals("0")){

            startMinutes = "00";

        }

        String timeInfo = startHours+":"+startMinutes;
        return timeInfo;

    }

    /**
     * Method to get start date for an event to be displayed in the agenda view
     * @param currentEvent - The event that is being analyzed
     * @return -String with current event's start date
     */
    public String getEventDate(Calendar currentEvent){

        String startMonth = Integer.toString(currentEvent.get(Calendar.MONTH) + 1);
        String startDay = Integer.toString(currentEvent.get(Calendar.DAY_OF_MONTH));
        String startYear = Integer.toString(currentEvent.get(Calendar.YEAR));

        String dateInfo = startMonth+"/"+startDay+"/"+startYear;
        return  dateInfo;

    }

}
