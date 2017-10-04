package com.bobo.normalman.bobobaking.model;

/**
 * Created by xiaobozhang on 9/30/17.
 */

public class Ingredient {
    public double quantity;
    public String measure;
    public String ingredient;

    public String toString() {
        return quantity + " " + measure + " " + ingredient;
    }
}
