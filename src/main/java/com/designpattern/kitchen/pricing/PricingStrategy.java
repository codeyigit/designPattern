package com.designpattern.kitchen.pricing;

/**
 * Strategy interface for different pricing calculations
 */
public interface PricingStrategy {
    double calculatePrice(double basePrice);
    String getDescription();
} 