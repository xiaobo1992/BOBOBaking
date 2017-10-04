package com.bobo.normalman.bobobaking.video;

import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bobo.normalman.bobobaking.R;
import com.bobo.normalman.bobobaking.model.Recipe;
import com.bobo.normalman.bobobaking.model.Step;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import butterknife.ButterKnife;

/**
 * Created by xiaobozhang on 9/30/17.
 */

public class VideoAdapter extends RecyclerView.Adapter {
    Recipe recipe;
    int currentStep;
    boolean isTwoPane;
    SimpleExoPlayer mExoPlayer;

    public VideoAdapter(Recipe recipe, int currentStep, boolean isTwoPane) {
        this.recipe = recipe;
        this.currentStep = currentStep;
        this.isTwoPane = isTwoPane;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (isTwoPane) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_video, parent, false);
        } else if (getOrientation(parent.getContext()) == Configuration.ORIENTATION_PORTRAIT) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_video, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.full_screen_video_view, parent, false);
        }
        ButterKnife.bind(this, view);
        return new VideoViewHolder(view);
    }

    public int getOrientation(Context context) {
        Configuration configuration = context.getResources().getConfiguration();
        return configuration.orientation;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d("adapter spte", currentStep + "");
        Step step = recipe.steps.get(currentStep);
        VideoViewHolder viewHolder = (VideoViewHolder) holder;
        if (isTwoPane || getOrientation(viewHolder.itemView.getContext()) == Configuration.ORIENTATION_PORTRAIT) {
            viewHolder.description.setText(step.description);
            if (currentStep == 0) {
                viewHolder.next.setVisibility(View.VISIBLE);
                viewHolder.prev.setVisibility(View.GONE);
            } else if (currentStep == recipe.steps.size() - 1) {
                viewHolder.next.setVisibility(View.GONE);
                viewHolder.prev.setVisibility(View.VISIBLE);
            } else {
                viewHolder.next.setVisibility(View.VISIBLE);
                viewHolder.prev.setVisibility(View.VISIBLE);
            }

            viewHolder.next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    nextStep();
                }
            });

            viewHolder.prev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    prevStep();
                }
            });
        }

        if (isTwoPane) {
            viewHolder.navi.setVisibility(View.GONE);
        }

        setmExoPlayer(viewHolder);
        if (!TextUtils.isEmpty(step.thumbnailURL)) {
            initVideoPlayer(viewHolder, Uri.parse(step.thumbnailURL));
        } else if (!TextUtils.isEmpty(step.videoURL)) {
            initVideoPlayer(viewHolder, Uri.parse(step.videoURL));
        }
    }

    public void setmExoPlayer(VideoViewHolder holder) {
        Context context = holder.itemView.getContext();
        TrackSelector trackSelector = new DefaultTrackSelector();
        LoadControl loadControl = new DefaultLoadControl();
        mExoPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector, loadControl);
        holder.video.setPlayer(mExoPlayer);
        holder.video.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);
    }

    public void initVideoPlayer(VideoViewHolder holder, Uri uri) {
        Context context = holder.itemView.getContext();
        String userAgent = Util.getUserAgent(context, context.getString(R.string.app_name));
        MediaSource mediaSource = new ExtractorMediaSource(uri, new DefaultDataSourceFactory(context
                , userAgent), new DefaultExtractorsFactory(), null, null);
        mExoPlayer.prepare(mediaSource);
        mExoPlayer.setPlayWhenReady(true);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public void nextStep() {
        currentStep += 1;
        mExoPlayer.release();
        notifyDataSetChanged();
    }

    public void prevStep() {
        currentStep -= 1;
        mExoPlayer.release();
        notifyDataSetChanged();
    }
}
