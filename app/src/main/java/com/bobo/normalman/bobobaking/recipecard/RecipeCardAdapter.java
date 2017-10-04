package com.bobo.normalman.bobobaking.recipecard;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bobo.normalman.bobobaking.DescriptionActivity;
import com.bobo.normalman.bobobaking.R;
import com.bobo.normalman.bobobaking.model.Recipe;
import com.bobo.normalman.bobobaking.util.ModelUtil;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by xiaobozhang on 9/29/17.
 */

public class RecipeCardAdapter extends RecyclerView.Adapter {
    List<Recipe> recipes;

    public RecipeCardAdapter(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_item_recipe_card, parent, false);
        return new RecipeCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Recipe recipe = recipes.get(position);
        final RecipeCardViewHolder viewHolder = (RecipeCardViewHolder) holder;
        viewHolder.recipeName.setText(recipe.name);
        viewHolder.cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = viewHolder.itemView.getContext();
                String recipeStr = ModelUtil.toString(recipe, new TypeToken<Recipe>() {
                });
                Intent intent = new Intent(context, DescriptionActivity.class);
                intent.putExtra(DescriptionActivity.KEY_RECIPE, recipeStr);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public void setData(List<Recipe> recipes) {
        this.recipes = recipes;
        notifyDataSetChanged();
    }
}
