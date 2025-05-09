package com.designpattern.kitchen.maincourse;

import com.designpattern.kitchen.meal.Meal;

/**
 * Base class for all main course meals
 */
public abstract class MainCourse implements Meal {
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