package team2.calendarapp;

import android.arch.lifecycle.Lifecycle;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Date;

public class AgendaView extends Fragment {
/**
AgendaView is the view for displaying calendar events as an agenda list.
The AgendaView view displays events in a list and shows event name, event start time, and event end time
*/
    private EventDB db = EventDB.getInstance();
    private ImageButton addButton;
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
        addButton = getActivity().findViewById(R.id.addButton);
        addButton.setOnClickListener(new ImageButton.OnClickListener(){
            public void onClick(View v){
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_container,new CreateEvent(),"createEvent").addToBackStack("fragBack").commit();
            }
        });
        // getting calendar events list
        Event [] agendaEvents = db.getEvents();
        
        // creating new AgendaEventsAdapter (the custom view for the agenda events list) and applying that adapter to agendaListView
        AgendaEventsAdapter agendaEventsListAdapter = new AgendaEventsAdapter( getActivity(), agendaEvents);
        agendaListView.setAdapter(agendaEventsListAdapter);
        return root;
    }
    

}
