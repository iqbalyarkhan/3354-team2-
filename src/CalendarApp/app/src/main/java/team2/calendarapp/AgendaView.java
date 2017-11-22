package team2.calendarapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class AgendaView extends Fragment {

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
        // create agendaEvent objects
        AgendaEvents firstEvent = new AgendaEvents("Learn android studio", "8:00 AM", "11:00 AM");
        AgendaEvents secondEvent = new AgendaEvents("Software engineering class", "11:30 AM", "12:45 PM");
        AgendaEvents thirdEvent = new AgendaEvents("Program android studio ", "1:00 PM", "2:00 PM");
        AgendaEvents fourthEvent = new AgendaEvents("Java programming", "2:00 PM", "3:00 PM");
        AgendaEvents fifthEvent = new AgendaEvents("Learn android", "5:00 PM", "11:00 PM");
    ArrayList<AgendaEvents> agendaEventsList = new ArrayList();
        // add AgendaEvents to AgendaEventList
        agendaEventsList.add(firstEvent);
        agendaEventsList.add(secondEvent);
        agendaEventsList.add(thirdEvent);
        agendaEventsList.add(fourthEvent);
        agendaEventsList.add(fifthEvent);
        // creating new AgendaEventsAdapter and applying that adapter to agendaListView
        AgendaEventsAdapter agendaEventsListAdapter = new AgendaEventsAdapter( getActivity(), agendaEventsList);
        agendaListView.setAdapter(agendaEventsListAdapter);
        return root;
    }
    

}
