package com.designpattern.kitchen.starter;

import com.designpattern.kitchen.meal.Meal;

/**
 * Base class for all starter meals
 */
public abstract class Starter implements Meal {
    protected String name;
    protected double price;
    protected String description;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public abstract void prepare();
} 