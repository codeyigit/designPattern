package com.designpattern.kitchen.starter;

import com.designpattern.kitchen.meal.Meal;
import com.designpattern.kitchen.meal.MealFactory;

/**
 * Enum-based Singleton Factory class for creating starter meals.
 * This implementation is thread-safe by default and prevents reflection-based attacks.
 */
public enum StarterFactory implements MealFactory {
    INSTANCE;  // The single instance of the enum

    /**
     * Creates a starter based on the type
     * @param type The type of starter to create
     * @return A new Starter instance
     */
    @Override
    public Meal createMeal(String type) {
        return switch (type.toLowerCase()) {
            case "salad" -> new Salad();
            case "soup" -> new Soup();
            default -> throw new IllegalArgumentException("Unknown starter type: " + type);
        };
    }
} 