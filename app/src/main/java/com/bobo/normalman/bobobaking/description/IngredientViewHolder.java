package com.bobo.normalman.bobobaking.description;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bobo.normalman.bobobaking.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xiaobozhang on 9/30/17.
 */

public class IngredientViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.ingredient_description)
    TextView ingredient;

    public IngredientViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
