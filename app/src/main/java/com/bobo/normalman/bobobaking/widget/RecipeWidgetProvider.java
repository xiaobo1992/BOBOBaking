package com.bobo.normalman.bobobaking.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import com.bobo.normalman.bobobaking.R;
import com.bobo.normalman.bobobaking.model.Recipe;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeWidgetProvider extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId, Recipe recipe) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget);

        if (recipe != null) {
            views.setTextViewText(R.id.recipe, recipe.name);
            views.setTextViewText(R.id.ingredient, recipe.ingredients.toString());
        } else {
            views.setTextViewText(R.id.recipe, "");
            views.setTextViewText(R.id.ingredient, "");
        }
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId, null);
        }
    }

    public static void updateRecipeWidget(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds, Recipe recipe) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId, recipe);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

}

