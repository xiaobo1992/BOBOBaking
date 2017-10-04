package com.bobo.normalman.bobobaking.video;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bobo.normalman.bobobaking.R;
import com.bobo.normalman.bobobaking.model.Recipe;
import com.bobo.normalman.bobobaking.util.ModelUtil;
import com.google.gson.reflect.TypeToken;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xiaobozhang on 9/30/17.
 */

public class VideoFragment extends Fragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    VideoAdapter adapter;
    public static final String KEY_RECIPE = "recipe";
    public static final String KEY_STEP = "step";
    public static final String KEY_TWOPANE = "two_pane";
    public static final String KEY_STATE = "state";

    public static VideoFragment newInstance(String recipe, int currentStep, boolean twoPane) {
        Bundle args = new Bundle();
        VideoFragment fragment = new VideoFragment();
        args.putString(KEY_RECIPE, recipe);
        args.putInt(KEY_STEP, currentStep);
        args.putBoolean(KEY_TWOPANE, twoPane);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Recipe recipe = ModelUtil.toObject(getArguments().getString(KEY_RECIPE), new TypeToken<Recipe>() {
        });
        int currentStep = getArguments().getInt(KEY_STEP, 0);
        boolean isTwopane = getArguments().getBoolean(KEY_TWOPANE);
        getActivity().setTitle(recipe.name);
        adapter = new VideoAdapter(recipe, currentStep, isTwopane);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("step destry", adapter.currentStep + "");
        adapter.mExoPlayer.release();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(KEY_STEP, adapter.currentStep);
        outState.putBoolean(KEY_TWOPANE, adapter.isTwoPane);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            int currentStep = savedInstanceState.getInt(KEY_STEP);
            boolean isTwoPane = savedInstanceState.getBoolean(KEY_TWOPANE);
            adapter.currentStep = currentStep;
            adapter.isTwoPane = isTwoPane;
        }
    }
}
