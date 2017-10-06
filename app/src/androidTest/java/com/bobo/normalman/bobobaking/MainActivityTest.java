package com.bobo.normalman.bobobaking;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.bobo.normalman.bobobaking.idlingResource.SimpleIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by xiaobozhang on 10/5/17.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule
            = new ActivityTestRule<MainActivity>(MainActivity.class);

    private SimpleIdlingResource mIdlingResource;

    @Before
    public void registerIdlingResource() {
        mIdlingResource = mActivityTestRule.getActivity().getIdlingResource();
        Espresso.registerIdlingResources(mIdlingResource);
    }

    @Test
    public void activityTest() {
        onView(withId(R.id.recycler_view))
                .perform(RecyclerViewActions.scrollToPosition(0))
                .check(matches(isDisplayed()))
                .check(matches(hasDescendant(withText("Nutella Pie"))));

        onView(withId(R.id.recycler_view))
                .perform(RecyclerViewActions.scrollToPosition(1))
                .check(matches(isDisplayed()))
                .check(matches(hasDescendant(withText("Brownies"))));

        onView(withId(R.id.recycler_view))
                .perform(RecyclerViewActions.scrollToPosition(2))
                .check(matches(isDisplayed()))
                .check(matches(hasDescendant(withText("Yellow Cake"))));

        onView(withId(R.id.recycler_view))
                .perform(RecyclerViewActions.scrollToPosition(3))
                .check(matches(isDisplayed()))
                .check(matches(hasDescendant(withText("Cheesecake"))));

        onView(withId(R.id.recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.toolbar))
                .check(matches(hasDescendant(withText("Nutella Pie"))));
        onView(withId(R.id.ingredient_description))
                .check(matches(isDisplayed()));
    }


    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            Espresso.unregisterIdlingResources(mIdlingResource);
        }
    }
}
