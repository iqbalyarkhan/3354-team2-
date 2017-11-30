package team2.calendarapp;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        setUpSpinner();

        if (savedInstanceState != null && savedInstanceState.containsKey("Event")) {
            toEdit = (Event) savedInstanceState.getSerializable("Event");
            edit = true;
            populateFields();
        }
        return root;
    }

    //setUpSpinner populates the category spinner with all the available categories
    private void setUpSpinner(){
        ArrayAdapter<Category> dataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, Arrays.asList(Event.getCategories()));
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sEventCategory.setAdapter(dataAdapter);
    }

    private void populateFields(){
        etEventName.setText(toEdit.getName());

        Calendar date = toEdit.getStartDate();
        etEventMonth.setText(date.get(Calendar.MONTH));
        etEventDay.setText(date.get(Calendar.DAY_OF_MONTH));
        etEventYear.setText(date.get(Calendar.YEAR));

        String eventTime = "" + date.get(Calendar.HOUR) + ":" + date.get(Calendar.MINUTE);
        etEventStart.setText(eventTime);
        tbStartAM.setChecked(date.get(Calendar.AM_PM) == Calendar.AM);

        date = toEdit.getEndDate();
        etEventMonth.setText(date.get(Calendar.MONTH));
        etEventDay.setText(date.get(Calendar.DAY_OF_MONTH));
        etEventYear.setText(date.get(Calendar.YEAR));

        eventTime = "" + date.get(Calendar.HOUR) + ":" + date.get(Calendar.MINUTE);
        etEventEnd.setText(eventTime);
        tbEndAM.setChecked(date.get(Calendar.AM_PM) == Calendar.AM);

        etEventLocation.setText(toEdit.getLocation());
        etEventDescription.setText(toEdit.getDescription());
        sEventCategory.setSelection(toEdit.getCategory());
    }

    //saveEvent grabs all the information from the relevant fields and saves them in an event object. Then it pushes that object to the EventDB
    protected void saveEvent(){
        String name = etEventName.getText().toString();                         //These commands get the simple information that can't be incorrect
        String description = etEventDescription.getText().toString();
        String location = etEventLocation.getText().toString();
        int category = sEventCategory.getSelectedItemPosition();
        try {
            int month = Integer.parseInt(etEventMonth.getText().toString());    //Try to parse integers from these fields. If we can't, they are incorrect
            int day = Integer.parseInt(etEventDay.getText().toString());
            int year = Integer.parseInt(etEventYear.getText().toString());

            if (name.equals("")){
                makeToast("Please enter an event name");
                return;
            }
            else if (month > 12 || month < 1 || day > 31 || day < 1){           //If the date fields are outside of a valid range, it is incorrect
                makeToast("Please enter a valid date");
                return;
            }
            else {
                try {
                    int start = parseTime(etEventStart.getText().toString(), tbStartAM.isChecked());  //parseTime gets the number of minutes this time is after midnight
                    int end = parseTime(etEventEnd.getText().toString(), tbEndAM.isChecked());
                    Calendar startDate = Calendar.getInstance(), endDate = Calendar.getInstance();
                    startDate.set(year, month, day, start / 60, start % 60); //Initialize the date object. year, month, and day are already correct, so we just pass them in. Start is converted to hours and minutes after midnight rather than just minutes.
                    endDate.set(year, month, day, end / 60, end % 60); //Same as above, but for when the event ends
                    Event event = new Event(name, description, location, startDate, endDate, category);
                    if (edit){
                        EventDB.delete(toEdit);
                    }
                    EventDB.addEvent(event);
                    getActivity().getFragmentManager().beginTransaction().remove(this).commit();    //Remove this fragment and return to whatever was there before
                }
                catch (IllegalArgumentException e){
                    makeToast("Please enter a valid start/end time");
                    return;
                }
            }
        }
        catch(NumberFormatException e){
            makeToast("Please enter a valid date");
            return;
        }
    }

    //makeToast is used to make a Toast
    private void makeToast(String message){
        Toast toast = Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT);
        toast.show();
    }

    //parseTime takes a string in standard time format (hh:mm) as well as if it's AM or PM and returns the number of minutes after midnight it is
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
        int ans = (hours % 12) * 60 + mins + (am ? 720 : 0);
        return ans;
    }

    //createCategory creates an AlertDialog that allows the user to create a new category for their events
    protected void createCategory() {
        final LinearLayout layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.HORIZONTAL);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());   //Create the AlertDialog
        builder.setTitle("New Category");

        final EditText input = new EditText(getActivity());     //Create the EditText that they will use to input the new Category
        final Spinner color = new Spinner(getActivity());
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, new String[]{"Green", "Red", "Blue", "Yellow", "Magenta", "Aqua"});
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        color.setAdapter(dataAdapter);

        layout.addView(input);
        layout.addView(color);
        builder.setView(layout);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {        //If they press OK, add the categories to the list of them
                Event.addCategory(input.getText().toString(), Color.parseColor(((String)color.getSelectedItem())));
                setUpSpinner();                                             //Then refresh the list
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

    protected void cancel(){
        getActivity().getFragmentManager().beginTransaction().remove(this).commit();
    }

    protected void delete(){
        getActivity().getFragmentManager().beginTransaction().remove(this).commit();
    }

    //When a button is pressed, this method gets called. It then calls the approriate method based on which button was pressec
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
