package team2.calendarapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class AgendaView extends Fragment {
/**
AgendaView is the view for displaying calendar events as an agenda list.
The AgendaView view displays events in a list and shows event name, event start time, and event end time
*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
        @param inflater the LayoutInflater used to convert AgendaView xml layout into Agenda View objects
        @param container the ViewGroup used for the inflate method that initializes the view root to be false
        @param savedInstanceState bundle used for the onCreate method that is called when the AgendaView activity starts up 
        @return root the view which is the modified view for the AgendaView containing calendar events in a list
        */
        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_agenda,container,false);
        ListView agendaListView = root.findViewById(R.id.agenda_list_view);
        // getting calendar events list
        Event [] agendaEventsList = EventDB.getEvents();
        // creating new AgendaEventsAdapter (the custom view for the agenda events list) and applying that adapter to agendaListView
        AgendaEventsAdapter agendaEventsListAdapter = new AgendaEventsAdapter( getActivity(), agendaEventsList);
        agendaListView.setAdapter(agendaEventsListAdapter);
        return root;
    }
    

}
