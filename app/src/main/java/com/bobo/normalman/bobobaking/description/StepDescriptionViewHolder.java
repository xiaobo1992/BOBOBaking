package com.bobo.normalman.bobobaking.description;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bobo.normalman.bobobaking.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xiaobozhang on 9/30/17.
 */

public class StepDescriptionViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.step_description)
    TextView step;

    @BindView(R.id.cover)
    LinearLayout cover;

    public StepDescriptionViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
