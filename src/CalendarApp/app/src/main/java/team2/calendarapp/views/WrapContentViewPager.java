package team2.calendarapp.views;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by iqbalkhan on 10/30/17.
 */

public class WrapContentViewPager extends ViewPager {

    public WrapContentViewPager(Context context){

        super(context);

    }

    public WrapContentViewPager(Context context, AttributeSet attributes){

        super(context, attributes);

    }

    @Override
    protected void onMeasure(int width, int height){

        int h = 0;

        if (getChildCount() > 0){

            View child = getChildAt(0);
            child.measure(width, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            h = child.getMeasuredHeight();

        }

        height = MeasureSpec.makeMeasureSpec(h,MeasureSpec.EXACTLY);
        super.onMeasure(width, height);

    }

}
