package team2.calendarapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static org.junit.Assert.*;
/**
 * Created by Andrew on 12/4/2017.
 */
@RunWith(AndroidJUnit4.class)
public class UITestClass {
    @Rule
    public ActivityTestRule<BaseView> mDayView = new ActivityTestRule<>(BaseView.class);

    @Test
    public void checkMonthView(){
        for(int i = 0; i < 12; i++)
            onView(withId(R.id.calendar_next_button)).perform(click());
        for(int i = 0; i < 12;i++)
            onView(withId(R.id.calendar_prev_button)).perform(click());
        /*onView(withId(R.id.addButton)).perform(click());
        onView(withId(R.id.tvName)).perform(typeText("Software Engineering Test"));
        onView(withId(R.id.etEventMonth)).perform(typeText("12"));
        onView(withId(R.id.etEventDay)).perform(typeText(""))
        onView(withId(R.id.content_container)).perform(pressBack())*/
    }
}
