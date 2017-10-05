package com.bobo.normalman.bobobaking;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.GradientDrawable;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.bobo.normalman.bobobaking.model.Recipe;
import com.bobo.normalman.bobobaking.util.ModelUtil;
import com.bobo.normalman.bobobaking.util.RequestUtil;
import com.google.gson.reflect.TypeToken;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;

/**
 * Created by xiaobozhang on 10/4/17.
 */
@RunWith(AndroidJUnit4.class)
public class VideoActivityTest {

    @Rule
    public ActivityTestRule<VideoActivity> mActivityTestRule =
            new ActivityTestRule<VideoActivity>(VideoActivity.class, true, false);

    @Before
    public void init() throws IOException {
        String recipe = getData();
        List<Recipe> recipeList = ModelUtil.toObject(recipe, new TypeToken<List<Recipe>>() {
        });
        Recipe sampleRecipe = recipeList.get(0);
        Intent intent = new Intent();
        intent.putExtra(DescriptionActivity.KEY_STEP, 0);
        intent.putExtra(DescriptionActivity.KEY_RECIPE,
                ModelUtil.toString(sampleRecipe, new TypeToken<Recipe>() {
                }));
        mActivityTestRule.launchActivity(intent);
    }

    @Test
    public void testVideoActivity() {
        mActivityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
        mActivityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        onView(withId(R.id.toolbar)).check(matches(not(isDisplayed())));
    }

    public String getData() throws IOException {
        String url = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
        return RequestUtil.request(url);
    }
}
