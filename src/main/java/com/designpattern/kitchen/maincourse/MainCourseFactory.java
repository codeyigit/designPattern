package com.designpattern.kitchen.maincourse;

import com.designpattern.kitchen.meal.Meal;
import com.designpattern.kitchen.meal.MealFactory;

/**
 * Factory class for creating main course meals
 */
public enum MainCourseFactory implements MealFactory {
    INSTANCE;

    @Override
    public Meal createMeal(String type) {
        return switch (type.toLowerCase()) {
            case "burger" -> new Burger();
            case "steak" -> new Steak();
            default -> throw new IllegalArgumentException("Unknown main course type: " + type);
        };
    }
} 