package com.bobo.normalman.bobobaking.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.bobo.normalman.bobobaking.model.Recipe;
import com.bobo.normalman.bobobaking.util.ModelUtil;
import com.google.gson.reflect.TypeToken;

/**
 * Created by xiaobozhang on 10/2/17.
 */

public class RecipeService extends IntentService {

    public static final String ACTION_UPLOAD_RECIPE = "update_widget";
    public static final String KEY_RECIPE = "recipe";

    public RecipeService() {
        super("Recipe Service");
    }

    public RecipeService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            Log.d("ACTION", intent.getAction());
            switch (intent.getAction()) {
                case ACTION_UPLOAD_RECIPE:
                    uploadRecipe(intent);
            }
        }
    }


    public static void startUploadRecipe(Context context, Recipe recipe) {
        Intent intent = new Intent(context, RecipeService.class);
        intent.putExtra(KEY_RECIPE, ModelUtil.toString(recipe, new TypeToken<Recipe>() {
        }));
        intent.setAction(ACTION_UPLOAD_RECIPE);
        context.startService(intent);
    }

    private void uploadRecipe(Intent intent) {
        Recipe recipe = ModelUtil.toObject(intent.getStringExtra(KEY_RECIPE), new TypeToken<Recipe>() {
        });
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, RecipeWidgetProvider.class));
        RecipeWidgetProvider.updateRecipeWidget(this, appWidgetManager, appWidgetIds, recipe);
    }
}
