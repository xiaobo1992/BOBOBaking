package com.bobo.normalman.bobobaking;

import android.content.Intent;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.bobo.normalman.bobobaking.model.Recipe;
import com.bobo.normalman.bobobaking.util.ModelUtil;
import com.bobo.normalman.bobobaking.util.RequestUtil;
import com.google.gson.reflect.TypeToken;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by xiaobozhang on 10/4/17.
 */
@RunWith(AndroidJUnit4.class)
public class DescriptionActivityTest {

    @Rule
    public ActivityTestRule<DescriptionActivity> mActivityTestRule
            = new ActivityTestRule<DescriptionActivity>(DescriptionActivity.class, true, false);
    Recipe sampleRecipe;

    @Before
    public void init() throws IOException {
        String recipe = getData();
        List<Recipe> recipeList = ModelUtil.toObject(recipe, new TypeToken<List<Recipe>>() {
        });
        sampleRecipe = recipeList.get(0);
        Intent intent = new Intent();
        intent.putExtra(DescriptionActivity.KEY_RECIPE,
                ModelUtil.toString(sampleRecipe, new TypeToken<Recipe>() {
                }));
        mActivityTestRule.launchActivity(intent);
    }

    @Test
    public void ActivityTest() {
        onView(withId(R.id.ingredient_description)).check(matches(isDisplayed()));
        onView(withId(R.id.recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(withId(R.id.description))
                .check(matches(withText(sampleRecipe.steps.get(0).description)));
    }

    public String getData() throws IOException {
        String url = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
        return RequestUtil.request(url);
    }
}
