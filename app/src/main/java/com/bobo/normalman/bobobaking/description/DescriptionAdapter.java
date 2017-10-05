package com.bobo.normalman.bobobaking.description;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bobo.normalman.bobobaking.R;
import com.bobo.normalman.bobobaking.model.Recipe;
import com.bobo.normalman.bobobaking.model.Step;
import com.bobo.normalman.bobobaking.util.ImageUtil;

/**
 * Created by xiaobozhang on 9/30/17.
 */

public class DescriptionAdapter extends RecyclerView.Adapter {

    public static final int VIEW_TYPE_INGREDIENT = 0;
    public static final int VIEW_TYPE_STEP = 1;
    Recipe recipe;
    DescriptionFragment.OnStepClickListener listener;

    public DescriptionAdapter(Recipe recipe, DescriptionFragment.OnStepClickListener listener) {
        this.listener = listener;
        this.recipe = recipe;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case VIEW_TYPE_INGREDIENT:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_ingredient, parent, false);
                return new IngredientViewHolder(view);
            case VIEW_TYPE_STEP:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_step, parent, false);
                return new StepDescriptionViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case VIEW_TYPE_INGREDIENT:
                IngredientViewHolder ingredientViewHolder = (IngredientViewHolder) holder;
                String ingredientDescription = getIngredients();
                ingredientViewHolder.ingredient.setText(ingredientDescription);
                break;
            case VIEW_TYPE_STEP:
                StepDescriptionViewHolder stepDescriptionViewHolder = (StepDescriptionViewHolder) holder;
                Step step = recipe.steps.get(position - 1);
                stepDescriptionViewHolder.step.setText(step.id + " " + step.shortDescription);
                stepDescriptionViewHolder.cover.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onStepSelected(position - 1);
                    }
                });
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_TYPE_INGREDIENT;
        } else {
            return VIEW_TYPE_STEP;
        }
    }

    @Override
    public int getItemCount() {
        return 1 + recipe.steps.size();
    }

    public String getIngredients() {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < recipe.ingredients.size(); i++) {
            buffer.append(recipe.ingredients.get(i).toString()).append("\n");
        }
        return buffer.toString();
    }
}
