package com.designpattern.kitchen.meal;

/**
 * Abstract factory interface for creating meals
 */
public interface MealFactory {
    Meal createMeal(String type);
} 