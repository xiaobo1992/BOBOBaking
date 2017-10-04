package com.bobo.normalman.bobobaking.recipecard;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.os.AsyncTaskCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bobo.normalman.bobobaking.R;
import com.bobo.normalman.bobobaking.model.Recipe;
import com.bobo.normalman.bobobaking.util.ModelUtil;
import com.bobo.normalman.bobobaking.util.RequestUtil;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xiaobozhang on 9/29/17.
 */

public class RecipeCardFragment extends Fragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    RecipeCardAdapter adapter;

    public final int WIDTH = 600;
    public final int MIN_COLUMN = 1;

    public static RecipeCardFragment newInstance() {
        Bundle args = new Bundle();
        RecipeCardFragment fragment = new RecipeCardFragment();
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
        AsyncTaskCompat.executeParallel(new LoadRecipes());
        adapter = new RecipeCardAdapter(new ArrayList<Recipe>());
        recyclerView.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), getColumns());
        recyclerView.setLayoutManager(layoutManager);
    }

    class LoadRecipes extends AsyncTask<Void, Void, List<Recipe>> {
        @Override
        protected List<Recipe> doInBackground(Void... voids) {
            String url = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
            try {
                String response = RequestUtil.request(url);
                return ModelUtil.toObject(response, new TypeToken<List<Recipe>>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Recipe> recipes) {
            if (recipes != null) {
                adapter.setData(recipes);
            }
        }
    }

    public int getColumns() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int widthDivider = WIDTH;
        int width = displayMetrics.widthPixels;
        int nColumns = width / widthDivider;
        if (nColumns < MIN_COLUMN) return MIN_COLUMN;
        return nColumns;
    }

}
