package team2.calendarapp;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Andrew on 11/11/2017.
 */

public class HourView extends LinearLayout {
    private TextView time;
    private LinearLayout separator;
    public HourView(Context context){
        super(context);
        initControls(context);
    }
    private void initControls(Context context){
        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_hour,this);
        time = findViewById(R.id.hour_text);
        separator = findViewById(R.id.hour_separator);
    }
    public void setTime(String time){
        this.time.setText(time);
    }





}
