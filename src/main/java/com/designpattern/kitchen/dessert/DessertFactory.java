package com.designpattern.kitchen.dessert;

import com.designpattern.kitchen.meal.Meal;
import com.designpattern.kitchen.meal.MealFactory;

/**
 * Factory class for creating dessert meals
 */
public enum DessertFactory implements MealFactory {
    INSTANCE;

    @Override
    public Meal createMeal(String type) {
        return switch (type.toLowerCase()) {
            case "ice cream" -> new IceCream();
            default -> throw new IllegalArgumentException("Unknown dessert type: " + type);
        };
    }
} 