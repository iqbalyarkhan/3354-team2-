package team2.calendarapp;

import android.content.Context;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.core.AllOf;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.contrib.DrawerActions.*;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
/**
 * Created by Andrew on 12/4/2017.
 */
@RunWith(AndroidJUnit4.class)
public class CheckExpectedFlowOfMonthAndDay {
    @Rule
    public ActivityTestRule<BaseView> mDayView = new ActivityTestRule<>(BaseView.class);

    @Test
    public void checkMonthView(){
        /*for(int i = 0; i < 12; i++){
            onView(withId(R.id.calendar_next_button)).perform(click());
        }
        for(int i = 0; i < 12;i++){
            onView(withId(R.id.calendar_prev_button)).perform(click());
        }*/
        //onData(anything()).inAdapterView(withId(R.id.calendar_grid)).atPosition(0).perform(click());
        onView(withId(R.id.addButton)).perform(click());
        onView(withId(R.id.etEventName)).perform(typeText("Software Engineering Test"));
        onView(withId(R.id.etEventMonth)).perform(typeText("12"));
        onView(withId(R.id.etEventDay)).perform(typeText("13"));
        onView(withId(R.id.etEventYear)).perform(typeText("2017"));
        onView(withId(R.id.etEventStart)).perform(typeText("11:30"));
        onView(withId(R.id.tbStartAM)).perform(click());
        onView(withId(R.id.etEventEnd)).perform(typeText("1:30"));
        onView(withId(R.id.etEventLocation)).perform(typeText("UTD"));
        onView(withId(R.id.etEventDescription)).perform(typeText("Difficult af"));
        onView(withId(R.id.bCreateCategory)).perform(click());
        onView(withId(R.id.NewCatgeoryInputId)).perform(typeText("SE"));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.sEventCategory)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.sEventCategory)).atPosition(1).perform(click());
        onView(withId(R.id.bSaveEvent)).perform(click());
        onView(withText("13")).perform(click());
        onView(withId(R.id.day_container)).perform(ViewActions.scrollTo(),click());
        pressBack();
        onView(withId(R.id.addButton)).perform(click());
        onView(withId(R.id.etEventName)).perform(typeText("OPL Test"));
        onView(withId(R.id.etEventMonth)).perform(typeText("12"));
        onView(withId(R.id.etEventDay)).perform(typeText("13"));
        onView(withId(R.id.etEventYear)).perform(typeText("2017"));
        onView(withId(R.id.etEventStart)).perform(typeText("11:30"));
        onView(withId(R.id.tbStartAM)).perform(click());
        onView(withId(R.id.etEventEnd)).perform(typeText("1:30"));
        onView(withId(R.id.etEventLocation)).perform(typeText("UTD"));
        onView(withId(R.id.etEventDescription)).perform(typeText("Need to study"));
        onView(withId(R.id.bSaveEvent)).perform(click());
        onView(withId(R.id.bCancel)).perform(click());
    }
    @Test
    public void checkDayView(){
        Calendar cal = Calendar.getInstance();
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withText("Day")).perform(click());
        onView(withId(R.id.addButton)).perform(click());
        onView(withId(R.id.etEventName)).perform(typeText("Study"));
        onView(withId(R.id.etEventMonth)).perform(typeText(cal.get(Calendar.MONTH) + 1 + ""));
        onView(withId(R.id.etEventDay)).perform(typeText(cal.get(Calendar.DAY_OF_MONTH) + ""));
        onView(withId(R.id.etEventYear)).perform(typeText(cal.get(Calendar.YEAR) + ""));
        onView(withId(R.id.etEventStart)).perform(typeText("1:00"));
        onView(withId(R.id.tbStartAM)).perform(click());
        onView(withId(R.id.etEventEnd)).perform(typeText("5:00"));
        onView(withId(R.id.etEventLocation)).perform(typeText("Home"));
        onView(withId(R.id.etEventDescription)).perform(typeText("OPL, Automata"));
        pressBack();
        onView(withId(R.id.bSaveEvent)).perform(click());
        pressBack();
        onData(anything()).inAdapterView(withId(R.id.calendar_grid)).atPosition(cal.get(Calendar.DAY_OF_WEEK) + 6).perform(click());
        onView(withId(R.id.day_container)).perform(swipeUp());
        onView(withId(R.id.day_container)).perform(swipeDown());
        onView(withText(containsString("Study"))).perform(click());
        onView(withId(R.id.bDelete)).perform(click());
        pressBack();
        pressBack();
    }
}
