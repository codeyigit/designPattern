package com.designpattern.kitchen.pricing;

/**
 * Concrete strategy for student pricing (20% discount)
 */
public class StudentPricingStrategy implements PricingStrategy {
    private static final double STUDENT_DISCOUNT = 0.20; // 20% discount

    @Override
    public double calculatePrice(double basePrice) {
        return basePrice * (1 - STUDENT_DISCOUNT);
    }

    @Override
    public String getDescription() {
        return "Student Discount (20% off)";
    }
} 