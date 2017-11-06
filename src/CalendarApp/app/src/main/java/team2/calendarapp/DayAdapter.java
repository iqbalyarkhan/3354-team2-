package team2.calendarapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Andrew on 10/31/2017.
 */

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.ViewHolder>{
    private ArrayList<Event> list;
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public EventView event;
        public ViewHolder(EventView v){
            super(v);
            event = v;
        }
    }

    public DayAdapter(ArrayList<Event> list){
        this.list = list;
    }

    @Override
    public DayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        EventView v = (EventView) LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        holder.event.setTitle(list.get(position).getName());
        /*holder.event.setStartTime(list.get(position).getStart());
        holder.event.setEndTime(list.get(position).getEnd());*/
    }
    @Override
    public int getItemCount(){
        return list.size();
    }
}
