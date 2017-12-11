package team2.calendarapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

public class MainCalendarView extends LinearLayout{

    // Number of days to be shown in one view ie 6 weeks
    private static final int DAYS_COUNT = 42;

    // Format for our date
    private static final String DATE_FORMAT = "MMM yyyy";

    // String to hold date
    private String dateFormat;

    // To hold current date: Only to compare selected date with current date
    private Calendar currentDate = Calendar.getInstance();

    private ConstraintLayout monthContainer = null;

    //to handle events
    private EventDB db = EventDB.getInstance();
    private MainCalendarView.WeekHandler weekHandler = null;
    private MainCalendarView.DayHandler dayHandler = null;


    //Components of the view
    /**
     * header - the top most banner in month view
     * btnPrev,btnNext - to display previous months and next months
     * txtDate: the date itself
     * grid: grid-view for the month
     */
    private LinearLayout header;
    private ImageView btnPrev;
    private ImageView btnNext;
    private TextView txtDate;
    private GridView grid;

    //To render context
    public MainCalendarView(Context context)
    {

        super(context);
    }

    //Standard constructor
    public MainCalendarView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initControl(context, attrs);
    }

    /**
     * Constructor to render the month view
     * @param context - the context of this view
     * @param attrs - Attributes to be set upon rendering of page
     * @param defStyleAttr - The styles of view
     */
    public MainCalendarView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        initControl(context, attrs);

    }

    /**
     * To initialize the xml layout control
     * @param context - current context
     * @param attrs - attributes to be loaded
     */
    private void initControl(Context context, AttributeSet attrs)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.control_calendar, this);

        loadDateFormat(attrs);
        assignUiElements();
        assignClickHandlers();

        updateCalendar();
    }


    /**
     * Function to load the date fomrat
     * @param attrs - attributes to be used to load date format
     */
    private void loadDateFormat(AttributeSet attrs)
    {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.CalendarView);

        try
        {
            dateFormat = ta.getString(R.styleable.CalendarView_dateFormat);
            if (dateFormat == null)
                dateFormat = DATE_FORMAT;
        }
        finally
        {
            ta.recycle();
        }
    }

    /**
     * Method to assign elements that control how the view is rendered
     */
    private void assignUiElements()
    {
        //Assigns local variables to components defined in xml file
        header = findViewById(R.id.calendar_header);
        btnPrev = findViewById(R.id.calendar_prev_button);
        btnNext = findViewById(R.id.calendar_next_button);
        txtDate = findViewById(R.id.calendar_date_display);
        grid = findViewById(R.id.calendar_grid);
    }

    /**
     * To load month on click
     */
    private void assignClickHandlers()
    {

        //When the next button is clicked, this
        //adds one month to current month and refresh page
        btnNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                currentDate.add(Calendar.MONTH, 1);
                updateCalendar(null);
                //eventHandler.setEvents();
            }
        });

        //When the previous button is clicked, this method
        //subtracts one month from current month and refreshes page
        btnPrev.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                currentDate.add(Calendar.MONTH, -1);
                updateCalendar(null);
                //eventHandler.setEvents();
            }
        });

        // When a day is long pressed
        grid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {

            @Override
            public boolean onItemLongClick(AdapterView<?> view, View cell, int position, long id)
            {
                //Handles long press
                if (weekHandler == null)
                    return false;
                weekHandler.onDayLongPress((Date)view.getItemAtPosition(position));
                return true;
            }
        });

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            //@Override
            public void onItemClick(AdapterView<?> view, View cell, int selectedDate, long id) {

                dayHandler.onDayPress((Date)view.getItemAtPosition(selectedDate));

            }
        });
    }

    /**
     * To display dates in grid based on the button pressed by the user.
     *
     */
    public void updateCalendar()
    {

        updateCalendar(null);
    }

    /**
     * Display dates with events in grid
     */
    public void updateCalendar(HashSet<Date> events)
    {
        //Array list of cells to hold all objects of date
        ArrayList<Date> cells = new ArrayList<>();
        Calendar calendar = (Calendar)currentDate.clone();

        //To determine where the first of month should be
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int monthBeginningCell = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        //To display the correct starting day of the week for the current month
        calendar.add(Calendar.DAY_OF_MONTH, -monthBeginningCell);

        int counter = 0;

        //Fills cells with date objects
        while (cells.size() < DAYS_COUNT)
        {
            cells.add(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH,1);

        }


        //Updates title of the page which displays chosen month and year
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        txtDate.setText(sdf.format(currentDate.getTime()));

        int currentMonth = currentDate.get(Calendar.MONTH);
        int currentYear = currentDate.get(Calendar.YEAR);


        //Updates grid with relevant information
        grid.setAdapter(new MainCalendarView.CalendarAdapter(getContext(), cells, events, currentMonth, currentYear));

        int month = currentDate.get(Calendar.MONTH);

    }

    /**
     * A private class within CalendarView to inflate each date object on the grid
     */
    private class CalendarAdapter extends ArrayAdapter<Date>
    {
        // To hold dates that have events
        private HashSet<Date> eventDays;

        // To inflate the view with updated activity
        private LayoutInflater inflater;

        //To hold which month is currently selected
        private int monthSelected;

        //To hold which year is currently selected
        private int year;

        //The cells from grid view of current display
        private ArrayList<Date> cells;


        /**
         * To inflate the current month view with correct dates and events
         * @param context - current context
         * @param days - all days in current month
         * @param eventDays - days with events in current month
         */
        public CalendarAdapter(Context context, ArrayList<Date> days, HashSet<Date> eventDays, int monthNumber, int year)
        {
            super(context, R.layout.control_calendar_day, days);
            //this.eventDays = eventDays;
            inflater = LayoutInflater.from(context);
            monthSelected = monthNumber;
            this.year = year;
            cells = days;
        }

        /**
         * To create the updated view based on the month and events in each month
         * @param position - position of the day
         * @param view - view to be rendered
         * @param parent - parent element
         * @return View - updated view returned
         */
        @Override
        public View getView(int position, View view, ViewGroup parent)
        {
            //Day based on position: all days in the month
            Date date = getItem(position);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int day = cal.get(Calendar.DAY_OF_WEEK); //Sat, Fri
            int dayNumber = cal.get(Calendar.DAY_OF_MONTH);// 10,11,12
            int month = cal.get(Calendar.MONTH);//June, July
            int year = cal.get(Calendar.YEAR); // 2017,2018

            //This is the new month's information ie month the user is currently on
            Date today = new Date();
            Calendar todayCal = Calendar.getInstance();
            todayCal.set(Calendar.YEAR, this.year);
            todayCal.set(Calendar.MONTH, monthSelected);
            int passedMonth = todayCal.get(Calendar.MONTH);

            //This represents the actual date (ie today's date)
            Date actualDate = new Date();
            Calendar actualCal = Calendar.getInstance();
            actualCal.setTime(actualDate);


            // inflate item if it does not exist yet
            if (view == null)
                view = inflater.inflate(R.layout.control_calendar_day, parent, false);

            // if this day has an event, display event image for relevant date
            // and render on the month's page
            view.setBackgroundResource(0);

            Calendar start = Calendar.getInstance();
            Calendar end = Calendar.getInstance();
            start.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.getActualMinimum(Calendar.MONTH));
            end.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            Event[] arr = EventDB.getInstance().getEventsInRange(start,end);

            //Goes through the events array and compares it to the dates in the grid to
            //highlight days with events.
            for (int i = 0; i < arr.length; i++){

                Calendar currEvent = arr[i].getStart();

                int eventDay = currEvent.get(Calendar.DAY_OF_MONTH);
                int eventMonth = currEvent.get(Calendar.MONTH);
                int eventYear = currEvent.get(Calendar.YEAR);

                if (eventDay == dayNumber && eventMonth == month && eventYear == year){

                    view.setBackgroundColor(Color.parseColor("#FFF9C4"));

                }

            }

            /*System.out.println(arr.length);
            for (Event e : arr){
                if(e == null){
                    continue;
                }
                //System.out.println("Events for this month: " + arr[i]);
                int eventDate = cal.get(Calendar.DAY_OF_MONTH) + 1;

                //view.setBackgroundResource(R.drawable.reminder);
                //view.getBackground().setColorFilter(Color.parseColor("#ffce00"), PorterDuff.Mode.DARKEN);
                view.setBackgroundColor(Color.parseColor("#E57373"));
            }*/


            /*if (eventDays != null)
            {
                for (Date eventDate : eventDays)
                {
                    if (eventDate.getDate() == day &&
                            eventDate.getMonth() == month &&
                            eventDate.getYear() == year)
                    {
                        // mark this day for event
                        view.setBackgroundResource(R.drawable.reminder);
                        break;
                    }
                }
            }*/

            // To style the current month: only user's currently selected month is in
            // black, other areas are greyed out.
            if (passedMonth == month){

                ((TextView)view).setTypeface(null, Typeface.NORMAL);
                ((TextView)view).setTextColor(Color.BLACK);

                //If the date is the actual date, present it in dark blue and bold
                if (dayNumber == actualCal.get(Calendar.DAY_OF_MONTH) &&
                        year == actualCal.get(Calendar.YEAR)
                        && month == actualCal.get(Calendar.MONTH)){

                    ((TextView)view).setTypeface(null, Typeface.BOLD);
                    ((TextView)view).setTextColor(getResources().getColor(R.color.today));

                }

            }

            //If part of the grid has dates that do not belong to current month, grey those
            //days out.
            else {

                ((TextView) view).setTextColor(getResources().getColor(R.color.greyed_out));

            }

            //Renders the actual dates in the month
            ((TextView)view).setText(String.valueOf(dayNumber));

            return view;
        }

    }

    /**
     * Week Handler to handle user's selected week dates for week view
     */
    public void setWeekHandler(MainCalendarView.WeekHandler weeksHandle)
    {
        this.weekHandler = weeksHandle;
    }

    /**
     *
     * @param daysHandle - the handle to user's currently selected date
     */
    public void setDayHandler (MainCalendarView.DayHandler daysHandle){

        this.dayHandler = daysHandle;

    }

    /**
     * Displays current date's week range
     */
    public interface WeekHandler
    {
        void onDayLongPress(Date date);
    }

    /**
     * Displays current date selected by the user
     */
    public interface DayHandler
    {
        void onDayPress(Date date);
    }

}
