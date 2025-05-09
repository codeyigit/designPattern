package com.designpattern.kitchen.meal;

/**
 * Base interface for all meal types
 */
public interface Meal {
    String getName();
    double getPrice();
    String getDescription();
    void prepare();
} 