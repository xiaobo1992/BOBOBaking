package com.bobo.normalman.bobobaking.description;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class DescriptionFragment extends Fragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    public static final String KEY_RECIPE = "recipe";
    public static final String KEY_STATE = "state";

    DescriptionAdapter adapter;
    OnStepClickListener listener;
    LinearLayoutManager layoutManager;

    public interface OnStepClickListener {
        void onStepSelected(int position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (OnStepClickListener) context;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static DescriptionFragment newInstance(String recipe) {
        Bundle args = new Bundle();
        DescriptionFragment fragment = new DescriptionFragment();
        args.putString(KEY_RECIPE, recipe);
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
        adapter = new DescriptionAdapter(recipe, listener);
        recyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Parcelable state = layoutManager.onSaveInstanceState();
        outState.putParcelable(KEY_STATE, state);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            Parcelable state = savedInstanceState.getParcelable(KEY_STATE);
            layoutManager.onRestoreInstanceState(state);
        }
    }
}
