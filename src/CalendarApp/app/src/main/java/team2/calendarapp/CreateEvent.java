package team2.calendarapp;

import android.app.AlertDialog;
import android.support.v4.app.Fragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Arrays;
import java.util.Calendar;

/*This class will allow the user to create, edit, and delete events*/

public class CreateEvent extends Fragment implements View.OnClickListener {
    EditText etEventName, etEventDescription, etEventLocation, etEventStart, etEventEnd, etEventMonth, etEventDay, etEventYear;
    Spinner sEventCategory;
    Button bSaveEvent, bCancel, bDelete, bCreateCategory;
    ToggleButton tbStartAM, tbEndAM;
    boolean edit = false;
    Event toEdit;
    EventDB database = EventDB.getInstance();

    //The onCreate method just initializes all the views and sets up the Activity for everything else
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.activity_create_event, container, false);

        etEventName = root.findViewById(R.id.etEventName);
        etEventDescription = root.findViewById(R.id.etEventDescription);
        etEventLocation = root.findViewById(R.id.etEventLocation);
        etEventStart = root.findViewById(R.id.etEventStart);
        etEventEnd = root.findViewById(R.id.etEventEnd);
        sEventCategory = root.findViewById(R.id.sEventCategory);
        etEventMonth = root.findViewById(R.id.etEventMonth);
        etEventDay = root.findViewById(R.id.etEventDay);
        etEventYear = root.findViewById(R.id.etEventYear);
        bSaveEvent = root.findViewById(R.id.bSaveEvent);
        bDelete = root.findViewById(R.id.bDelete);
        bCancel = root.findViewById(R.id.bCancel);
        tbStartAM = root.findViewById(R.id.tbStartAM);
        tbEndAM = root.findViewById(R.id.tbEndAM);
        bCreateCategory = root.findViewById(R.id.bCreateCategory);

        bSaveEvent.setOnClickListener(this);
        bCancel.setOnClickListener(this);
        bDelete.setOnClickListener(this);
        bCreateCategory.setOnClickListener(this);

        setUpSpinner();     //Populate the Category Spinner with all the allowed categories.

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);       //Make sure the layout doesn't change when the keyboard is created.

        Bundle info = getArguments();
        if (info != null && info.containsKey("Event")) {            //If the Bundle contains an event, we need to prepare this Activity for editing it.
            toEdit = (Event) info.getSerializable("Event");
            edit = true;
            populateFields();           //Fill in all the fields with dthe appropriate values
        }
        else if (info != null && info.containsKey("Date")){         //If a Calendar object was passed in, fill out the date fields to represent that
            populateDate((Calendar) info.getSerializable("Date"));
        }
        if (!edit){     //If we're not editing an event, there shouldn't be a delete button.
            bDelete.setVisibility(View.GONE);
        }
        return root;
    }

    //setUpSpinner populates the category spinner with all the available categories
    private void setUpSpinner(){
        ArrayAdapter<Category> dataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, Arrays.asList(Event.getCategories()));
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sEventCategory.setAdapter(dataAdapter);
    }

    //populateFields fills in all the fields with the values of an Event object that was passed in.
    private void populateFields(){
        etEventName.setText(toEdit.getName());

        Calendar date = toEdit.getStart();
        etEventMonth.setText("" + (date.get(Calendar.MONTH) + 1));
        etEventDay.setText("" + date.get(Calendar.DAY_OF_MONTH));
        etEventYear.setText("" + date.get(Calendar.YEAR));
        String eventTime;
        if(date.get(Calendar.MINUTE) < 10)
            eventTime = "" + date.get(Calendar.HOUR) + ":0" + date.get(Calendar.MINUTE);
        else
            eventTime = "" + date.get(Calendar.HOUR) + ":" + date.get(Calendar.MINUTE);
        etEventStart.setText(eventTime);
        tbStartAM.setChecked(date.get(Calendar.AM_PM) == Calendar.AM);

        date = toEdit.getEnd();
        if(date.get(Calendar.MINUTE) < 10)
            eventTime = "" + date.get(Calendar.HOUR) + ":0" + date.get(Calendar.MINUTE);
        else
            eventTime = "" + date.get(Calendar.HOUR) + ":" + date.get(Calendar.MINUTE);
        etEventEnd.setText(eventTime);
        tbEndAM.setChecked(date.get(Calendar.AM_PM) == Calendar.AM);

        etEventLocation.setText(toEdit.getLocation());
        etEventDescription.setText(toEdit.getDescription());
        sEventCategory.setSelection(toEdit.getCategory());
    }

    //populateDate fills in the date fields with a value that was passed in.
    private void populateDate(Calendar date){
        etEventMonth.setText("" + date.get(Calendar.MONTH ));
        etEventDay.setText("" + date.get(Calendar.DAY_OF_MONTH));
        etEventYear.setText("" + date.get(Calendar.YEAR));
    }

    //saveEvent grabs all the information from the relevant fields and saves them in an event object. Then it pushes that object to the EventDB
    protected void saveEvent(){
        String name = etEventName.getText().toString();                         //These commands get the simple information that can't be incorrect
        String description = etEventDescription.getText().toString();
        String location = etEventLocation.getText().toString();
        int category = sEventCategory.getSelectedItemPosition();

        try {
            int month = Integer.parseInt(etEventMonth.getText().toString())-1;    //Try to parse integers from these fields. If we can't, they are incorrect. One is subtracted because Calendar is 0-indexed
            int day = Integer.parseInt(etEventDay.getText().toString());
            int year = Integer.parseInt(etEventYear.getText().toString());

            if (name.equals("")){           //If name is blank, have them enter it again
                makeToast("Please enter an event name");
            }
            else {
                try {
                    int[] start = parseTime(etEventStart.getText().toString(), tbStartAM.isChecked());      //Pull the times from the corresponding EditTexts
                    int[] end = parseTime(etEventEnd.getText().toString(), tbEndAM.isChecked());
                    int startHour = start[0], startMin = start[1], endHour = end[0], endMin = end[1];
                    Calendar startDate = Calendar.getInstance(), endDate = Calendar.getInstance();

                    startDate.set(year, month, day, startHour, startMin);       //Initialize the date object representing when the Event starts.
                    endDate.set(year, month, day, endHour, endMin);     //Same as above, but for when the event ends
                    if (startDate.get(Calendar.MONTH) != month){        //If the day value was too high, it will roll over into month. By checking that, we can determine the correctness of the day value provided.
                        makeToast("Please enter a valid date");
                        return;
                    }

                    Event event = new Event(name, description, location, startDate, endDate, category);
                    if (edit){      //If we are editing this event, delete the old one from the database
                        database.delete(toEdit);
                    }
                    Event collision = database.isCollision(event);
                    if (collision != null){     //If there is a collision, it can't be added
                        makeToast(collision.getName() + " is happening at the same time");
                    }
                    else {
                        database.addEvent(event);       //If everything went well, we can add the event to the database and return to the Calendar
                        getActivity().getSupportFragmentManager().popBackStack();    //Remove this fragment and return to whatever was there before
                    }

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

    //makeToast is used to make a Toast
    //@param message: the message that will be displayed to the user.
    private void makeToast(String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    //parseTime takes a string in standard time format (hh:mm) as well as if it's AM or PM and returns two integers representing the time
    //@param time: A string in format hh:mm or h:mm representing the time
    //@param am: A boolean representing whether the time is AM or PM
    //@return: an integer array. The first value is the hours after midnight, the second is the minutes after that hour.
    private int[] parseTime(String time, boolean am){
        int hours, mins;
        if (time.length() < 4 || time.length() > 5 || time.indexOf(':') != time.lastIndexOf(':')){      //If the string is too long or too short, or there is more than one :, it is incorrect
            throw new IllegalArgumentException();
        }
        else if (time.charAt(1) == ':') {       //If the format is h:mm, only read the first character for the hours
            hours = Integer.parseInt(time.substring(0, 1), 10);
            mins = Integer.parseInt(time.substring(2), 10);
        }
        else if (time.charAt(2) == ':'){        //Otherwise read the first 2
            hours = Integer.parseInt(time.substring(0, 2), 10);
            mins = Integer.parseInt(time.substring(3), 10);
        }
        else{       //If there was no :, the format is incorrect
            throw new IllegalArgumentException();
        }
        return new int[]{(hours % 12) + (am ? 0 : 12), mins};       //Modulus 12 because 12:00 belongs to a different AM/PM. Possibly add the 12 because this is 24-hour time
    }

    //createCategory creates an AlertDialog that allows the user to create a new category for their events
    protected void createCategory() {
        final LinearLayout layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.HORIZONTAL);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());   //Create the AlertDialog
        builder.setTitle("New Category");

        final EditText input = new EditText(getActivity());     //Create the EditText that they will use to input the new Category
        final Spinner color = new Spinner(getActivity());       //Create the Spinner that will be used to select the color
        input.setEms(5);        //Make sure the EditText isn't too small
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, new String[]{"Green", "Red", "Blue", "Yellow", "Magenta", "Aqua"});      //Populate the Spinner with the Colors that can be selected.
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        color.setAdapter(dataAdapter);

        layout.addView(input);      //Add the two views to the layout and set that layout to be the content of the AlertDialog
        layout.addView(color);
        builder.setView(layout);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {        //If they press OK, add the categories to the list of them
                if (Event.addCategory(input.getText().toString(), Color.parseColor(((String)color.getSelectedItem())))) {       //We don't want categories to have the same name
                    setUpSpinner();                                             //Then refresh the list
                }
                else{
                    makeToast("That category name is already taken");
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {        //If they press cancel, end the AlertDialog
                dialog.cancel();
            }
        });

        builder.show();
    }

    //cancel gets rid of this Fragment without saving anything
    protected void cancel(){
        getActivity().getSupportFragmentManager().popBackStack();
    }

    //delete deletes the current Event being edited and gets rid of this Fragment
    protected void delete(){
        database.delete(toEdit);
        getActivity().getSupportFragmentManager().popBackStack();
    }

    //When a button is pressed, this method gets called. It then calls the appropriate method based on which button was pressed
    public void onClick(View v){
        switch (v.getId()){
            case R.id.bSaveEvent: saveEvent();
                                  break;
            case R.id.bCancel: cancel();
                               break;
            case R.id.bDelete: delete();
                               break;
            case R.id.bCreateCategory: createCategory();
                                       break;
        }
    }
}
