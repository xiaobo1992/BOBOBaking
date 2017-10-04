package com.bobo.normalman.bobobaking;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.bobo.normalman.bobobaking.description.DescriptionFragment;
import com.bobo.normalman.bobobaking.model.Recipe;
import com.bobo.normalman.bobobaking.util.ModelUtil;
import com.bobo.normalman.bobobaking.video.VideoFragment;
import com.bobo.normalman.bobobaking.widget.RecipeService;
import com.google.gson.reflect.TypeToken;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xiaobozhang on 9/30/17.
 */

public class DescriptionActivity extends AppCompatActivity implements DescriptionFragment.OnStepClickListener {

    public static final String KEY_RECIPE = "recipe";
    public static final String KEY_STEP = "step";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    boolean twoPane;
    Recipe recipe;
    String recipeStr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        ButterKnife.bind(this);
        recipeStr = getIntent().getStringExtra(KEY_RECIPE);
        recipe = ModelUtil.toObject(recipeStr, new TypeToken<Recipe>() {
        });
        isTwoPane();
        setupToolbar();
        setupWidget();
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.description_fragment, DescriptionFragment.newInstance(recipeStr))
                    .commit();
            if (twoPane) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.video_fragment, VideoFragment.newInstance(recipeStr, 0, true))
                        .commit();
            }
        }
    }

    public void setupToolbar() {
        setTitle(recipe.name);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public void setupWidget() {
        RecipeService.startUploadRecipe(this, recipe);
    }

    public void isTwoPane() {
        twoPane = findViewById(R.id.video_fragment) != null;
    }

    @Override
    public void onStepSelected(int position) {
        if (twoPane) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.video_fragment, VideoFragment.newInstance(recipeStr, position, true))
                    .commit();
        } else {
            Intent intent = new Intent(getApplicationContext(), VideoActivity.class);
            intent.putExtra(KEY_RECIPE, recipeStr);
            intent.putExtra(KEY_STEP, position);
            startActivity(intent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
