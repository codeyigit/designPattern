package com.designpattern.kitchen.pricing;

/**
 * Concrete strategy for weekday pricing (10% discount)
 */
public class WeekdayPricingStrategy implements PricingStrategy {
    private static final double WEEKDAY_DISCOUNT = 0.10; // 10% discount

    @Override
    public double calculatePrice(double basePrice) {
        return basePrice * (1 - WEEKDAY_DISCOUNT);
    }

    @Override
    public String getDescription() {
        return "Weekday Discount (10% off)";
    }
} 