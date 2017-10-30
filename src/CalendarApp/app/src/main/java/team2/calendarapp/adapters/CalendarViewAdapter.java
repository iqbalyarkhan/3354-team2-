/**
 * Created by iqbalkhan on 10/29/17.
 */
package team2.calendarapp.adapters;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

import static android.view.View.FIND_VIEWS_WITH_CONTENT_DESCRIPTION;
import team2.calendarapp.interfaces.OnDateSelectedListener;
import team2.calendarapp.interfaces.OnDayViewClickListener;
import team2.calendarapp.objects.CalendarDate;
import team2.calendarapp.objects.CalendarMonth;
import team2.calendarapp.views.CalendarDayView;
import team2.calendarapp.views.CalendarMonthView;



public class CalendarViewAdapter extends PagerAdapter implements OnDayViewClickListener  {

    private ViewPager viewPager;
    private List<CalendarMonth> monthInfo;
    private CalendarDate currDate;
    private OnDateSelectedListener listener;

    public CalendarViewAdapter(List<CalendarMonth> list, ViewPager vPager){

        monthInfo = list;
        viewPager = vPager;
        currDate = new CalendarDate(Calendar.getInstance());

    }

    @Override
    public Object instantiateItem (ViewGroup container, int position){

        CalendarMonth month = monthInfo.get(position);
        CalendarMonthView monthView = new CalendarMonthView(container.getContext());
        monthView.setSelectedDate(currDate);
        monthView.setOnDayViewClickListener(this);
        monthView.buildView(month);
        (container).addView(monthView, 0);
        monthView.setTag(month);
        return monthView;

    }

    @Override
    public int getCount(){

        return monthInfo.size();

    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view){

        collection.removeView((View) view);

    }

    @Override
    public boolean isViewFromObject(View view, Object object){

        return (view == object);

    }

    @Override
    public int getItemPosition(Object object){

        View view = (View) object;
        CalendarMonth month = (CalendarMonth) view.getTag();
        int position = monthInfo.indexOf(month);

        if (position >= 0){

            return position;
        }

        else{

            return POSITION_NONE;
        }


    }

    public void addNext(CalendarMonth month){

        monthInfo.add(month);
        notifyDataSetChanged();

    }

    public void addPrev(CalendarMonth month){

        monthInfo.add(0, month);
        notifyDataSetChanged();

    }

    public String getItemPageHeader(int position){

        return monthInfo.get(position).toString();

    }

    public CalendarMonth getItem(int position){

        return monthInfo.get(position);

    }

    public void setOnDateSelecteListener (OnDateSelectedListener l){

        listener = l;
        if (listener != null){

            listener.onDateSelected(new CalendarDate(currDate));

        }

    }

    @Override
    public void onDayViewClick(CalendarDayView view){

        decorateSelection(currDate.toString(), false);

        currDate = view.getDate();
        decorateSelection(currDate.toString(), true);

        if (listener != null){

            listener.onDateSelected(new CalendarDate(currDate));

        }

    }


    private void decorateSelection(String tag, boolean isSelected){

        ArrayList<View> output = new ArrayList<>();
        viewPager.findViewsWithText(output, tag, FIND_VIEWS_WITH_CONTENT_DESCRIPTION);
        for (View outputView : output){

            CalendarDayView dayView = (CalendarDayView) outputView;
            if (isSelected){

                dayView.setPurpleSolidOvalBackground();
            }

            else{

                dayView.unsetPurpleSolidOvalBackground();

            }

        }

    }

}
