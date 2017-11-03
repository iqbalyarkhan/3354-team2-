package team2.calendarapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Date;

public class CreateEvent extends AppCompatActivity {
    EditText etEventName, etEventDescription, etEventLocation, etEventStart, etEventEnd, etEventMonth, etEventDay, etEventYear;
    Spinner sEventCategory;
    boolean startAM = true, endAM = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        etEventName = findViewById(R.id.etEventName);
        etEventDescription = findViewById(R.id.etEventDescription);
        etEventLocation = findViewById(R.id.etEventLocation);
        etEventStart = findViewById(R.id.etEventStart);
        etEventEnd = findViewById(R.id.etEventEnd);
        sEventCategory = findViewById(R.id.sEventCategory);
        etEventMonth = findViewById(R.id.etEventMonth);
        etEventDay = findViewById(R.id.etEventDay);
        etEventYear = findViewById(R.id.etEventYear);

        setUpSpinner();
    }

    private void setUpSpinner(){
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Arrays.asList(Event.getCategories()));
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sEventCategory.setAdapter(dataAdapter);
    }

    protected void saveEvent(View v){
        String name = etEventName.getText().toString();
        String description = etEventDescription.getText().toString();
        String location = etEventLocation.getText().toString();
        String startString = etEventStart.getText().toString();
        int category = sEventCategory.getSelectedItemPosition();
        try {
            int month = Integer.parseInt(etEventMonth.getText().toString());
            int day = Integer.parseInt(etEventDay.getText().toString());
            int year = Integer.parseInt(etEventYear.getText().toString());

            if (name.equals("")){
                makeToast("Please enter an event name");
            }
            else if (month > 12 || month < 1 || day > 31 || day < 1){
                makeToast("Please enter a valid date");
            }
            else {
                try {
                    int start = parseTime(etEventStart.getText().toString(), startAM);
                    int end = parseTime(etEventEnd.getText().toString(), endAM);
                    Date date = new Date(year, month, day);
                    Event event = new Event(name, description, location, date, start, end, category);
                    EventDB.addEvent(event);
                    makeToast("hello " + EventDB.getEvents().toString());
                }
                catch (IllegalArgumentException e){
                    makeToast("Please enter a valid start/end time");
                }
            }
        }
        catch(NumberFormatException e){
            makeToast("Please enter a valid date");
        }
    }

    private void makeToast(String message){
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    private int parseTime(String time, boolean am){
        int hours = 0, mins = 0;
        if (time.length() < 4 || time.length() > 5 || time.indexOf(':') != time.lastIndexOf(':')){
            throw new IllegalArgumentException();
        }
        else if (time.charAt(1) == ':') {
            hours = Integer.parseInt(time.substring(0, 1));
            mins = Integer.parseInt(time.substring(2));
        }
        else if (time.charAt(2) == ':'){
            hours = Integer.parseInt(time.substring(0, 2));
            mins = Integer.parseInt(time.substring(3));
        }
        else{
            throw new IllegalArgumentException();
        }
        int ans = (hours % 12) * 60 + mins + (am ? 0 : 720);
        return ans;
    }

    protected void createCategory(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Title");

        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Event.addCategory(input.getText().toString());
                setUpSpinner();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    protected void startAM(View v){
        Button button = (Button) v;
        if (button.getText().toString().charAt(0) == 'A'){
            button.setText("PM");
            startAM = false;
        }
        else{
            button.setText("AM");
            startAM = true;
        }
    }

    protected void endAM(View v){
        Button button = (Button) v;
        if (button.getText().toString().charAt(0) == 'A'){
            button.setText("PM");
            endAM = false;
        }
        else{
            button.setText("AM");
            endAM = true;
        }
    }
}
