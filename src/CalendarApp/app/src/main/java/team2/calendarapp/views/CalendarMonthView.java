package team2.calendarapp.views;

import android.view.View;
import android.widget.FrameLayout;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import team2.calendarapp.R;
import team2.calendarapp.interfaces.OnDayViewClickListener;
import team2.calendarapp.objects.CalendarDate;
import team2.calendarapp.objects.CalendarMonth;
import team2.calendarapp.utils.Utils;

/**
 * Created by iqbalkhan on 10/29/17.
 */

public class CalendarMonthView extends FrameLayout implements View.OnClickListener {

    private GridLayout gridLayout;
    private ViewGroup layoutDays;
    private OnDayViewClickListener listener;
    private CalendarDate selectedDate;

    public CalendarMonthView(Context context){

        super(context);
        initialize();

    }

    private void initialize(){

        inflate (getContext(), R.layout.view_calendar_month, this);
        gridLayout = (GridLayout) findViewById(R.id.view_calendar_month_grid);
        layoutDays = (ViewGroup) findViewById(R.id.view_calendar_month_layout_days);

    }

    public void setOnDayViewClickListener(OnDayViewClickListener l){

        listener = l;
    }

    public void setSelectedDate (CalendarDate sDate){

        selectedDate = sDate;

    }

    public void buildView(CalendarMonth calendarMonth){

        buildDaysLayout();
        buildGridView(calendarMonth);

    }

    private void buildDaysLayout(){

        String[] days;
        days = getResources().getStringArray(R.array.days_sunday_array);

        for (int i = 0; i < layoutDays.getChildCount(); i++){

            TextView textView = (TextView) layoutDays.getChildAt(i);
            textView.setText(days[i]);

        }

    }

    private void buildGridView(CalendarMonth month){

        int row = CalendarMonth.NUMBER_OF_WEEKS_IN_MONTH;
        int col = CalendarMonth.NUMBER_OF_DAYS_IN_WEEK;
        gridLayout.setRowCount(row);
        gridLayout.setColumnCount(col);

        int screenWidth = Utils.getScreenWidth(getContext());
        int width = screenWidth / col;

        for (CalendarDate date : month.getDays()){

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = width;
            params.height = LayoutParams.WRAP_CONTENT;

            CalendarDayView dayView = new CalendarDayView(getContext(), date);
            dayView.setContentDescription(date.toString());
            dayView.setLayoutParams(params);
            dayView.setOnClickListener(this);
            decorateDayView(dayView, date, month.getMonth());
            gridLayout.addView(dayView);

        }

    }

    private void decorateDayView(CalendarDayView dayView, CalendarDate day, int theMonth){

        if (day.getMonth() != theMonth){

            dayView.setOtherMonthTextColor();

        }

        else{

            dayView.setThisMonthTextColor();

        }

        if (selectedDate != null && selectedDate.isDateEqual(day)){

            dayView.setPurpleSolidOvalBackground();

        }

        else{

            dayView.unsetPurpleSolidOvalBackground();

        }
    }

    @Override
    public void onClick(View view){

        if (listener != null){

            listener.onDayViewClick((CalendarDayView) view);

        }

    }


}
