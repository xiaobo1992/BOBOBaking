package com.bobo.normalman.bobobaking.model;

import java.util.List;

/**
 * Created by xiaobozhang on 9/30/17.
 */

public class Recipe {
    public String id;
    public String name;
    public List<Step> steps;
    public List<Ingredient> ingredients;
    //public int servings;
    public String image;
}
