package com.bobo.normalman.bobobaking.recipecard;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bobo.normalman.bobobaking.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xiaobozhang on 9/29/17.
 */

public class RecipeCardViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.recipe_name)
    TextView recipeName;
    @BindView(R.id.cover)
    View cover;

    public RecipeCardViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
