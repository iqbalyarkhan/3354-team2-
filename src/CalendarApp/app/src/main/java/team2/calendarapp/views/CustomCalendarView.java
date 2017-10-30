package team2.calendarapp.views;

import android.content.Context;
import android.media.Image;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import team2.calendarapp.R;
import team2.calendarapp.adapters.CalendarViewAdapter;
import team2.calendarapp.interfaces.OnDateSelectedListener;
import team2.calendarapp.objects.CalendarMonth;

/**
 * Created by iqbalkhan on 10/29/17.
 */

public class CustomCalendarView extends FrameLayout implements View.OnClickListener{

    private TextView pagerTextMonth;
    private ImageButton buttonLeftArrow;
    private ImageButton buttonRightArrow;
    private ViewPager viewPager;
    private CalendarViewAdapter viewAdapter;
    private OnDateSelectedListener listener;

    public CustomCalendarView(@NonNull Context context){

        super(context);
        initialize();

    }

    private void initialize(){

        inflate(getContext(), R.layout.view_custom_calendar, this);
        viewPager = (ViewPager) findViewById(R.id.activity_main_view_pager);
        pagerTextMonth = (TextView) findViewById(R.id.activity_main_pager_text_month);
        buttonLeftArrow = (ImageButton) findViewById(R.id.activity_main_pager_button_left_arrow);
        buttonRightArrow = (ImageButton) findViewById(R.id.activity_main_pager_button_right_arrow);
        buttonLeftArrow.setOnClickListener(this);
        buttonRightArrow.setOnClickListener(this);
        buildCalendarView();

    }

    public CustomCalendarView(@NonNull Context context, @Nullable AttributeSet attributes){


        super(context, attributes);
        initialize();

    }

    public CustomCalendarView(@NonNull Context context, @Nullable AttributeSet attribute, @AttrRes int styleAttribute) {
        super(context, attribute, styleAttribute);
        initialize();
    }


    public void setOnDateSelectedListener(OnDateSelectedListener l){

        viewAdapter.setOnDateSelecteListener(l);

    }

    @Override
    public void onClick(View view){

        switch (view.getId()){

            case R.id.activity_main_pager_button_right_arrow:
                int next = viewPager.getCurrentItem() + 1;
                viewPager.setCurrentItem(next, true);
                break;

            case R.id.activity_main_pager_button_left_arrow:
                int prev = viewPager.getCurrentItem() - 1;
                viewPager.setCurrentItem(prev, true);
                break;

        }

    }


    private void buildCalendarView(){

        List<CalendarMonth> list = new ArrayList<>();
        CalendarMonth today = new CalendarMonth(Calendar.getInstance());

        list.add(new CalendarMonth (today, -2));
        list.add(new CalendarMonth (today, -1));
        list.add(today);
        list.add(new CalendarMonth (today, 1));
        list.add(new CalendarMonth (today, 2));

        viewAdapter = new CalendarViewAdapter(list, viewPager);
        viewPager.setAdapter(viewAdapter);
        viewPager.addOnPageChangeListener(pageChangeListener);
        viewPager.setOffscreenPageLimit(1);
        viewPager.setCurrentItem(2);
        pagerTextMonth.setText(viewAdapter.getItemPageHeader(2));
    }

    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener(){

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels){


        }

        @Override
        public void onPageSelected(int position){

        }

        @Override
        public void onPageScrollStateChanged(int state){

            int position = viewPager.getCurrentItem();
            pagerTextMonth.setText(viewAdapter.getItemPageHeader(position));

            if (state == ViewPager.SCROLL_STATE_IDLE && position == 1){

                addPrev();

            }

            if (state == ViewPager.SCROLL_STATE_IDLE && position == viewAdapter.getCount() - 2){

                addNext();
            }

        }
    };

    private void addNext(){

        CalendarMonth month = viewAdapter.getItem(viewAdapter.getCount() - 1);
        viewAdapter.addNext(new CalendarMonth(month, 1));

    }

    private void addPrev(){

        CalendarMonth month = viewAdapter.getItem(0);
        viewAdapter.addPrev(new CalendarMonth(month, -1));

    }


}
