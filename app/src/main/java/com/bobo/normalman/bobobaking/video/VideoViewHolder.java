package com.bobo.normalman.bobobaking.video;

import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bobo.normalman.bobobaking.R;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xiaobozhang on 9/30/17.
 */

public class VideoViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.recipe_video)
    SimpleExoPlayerView video;

    @Nullable
    @BindView(R.id.description)
    TextView description;

    @Nullable
    @BindView(R.id.navi)
    LinearLayout navi;

    @Nullable
    @BindView(R.id.next)
    TextView next;

    @Nullable
    @BindView(R.id.prev)
    TextView prev;

    @Nullable
    @BindView(R.id.description_card)
    CardView descriptionCard;

    public VideoViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
