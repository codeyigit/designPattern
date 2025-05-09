package com.designpattern.kitchen;

import java.util.HashMap;
import java.util.Map;

/**
 * Subsystem class that handles order pricing calculations
 */
public class PricingEngine {
    private final Map<String, Double> itemPrices;

    public PricingEngine() {
        itemPrices = new HashMap<>();
        // Initialize with some basic prices
        itemPrices.put("burger", 12.99);
        itemPrices.put("fries", 4.99);
        itemPrices.put("coke", 2.99);
        itemPrices.put("salad", 8.99);
        itemPrices.put("soup", 6.99);
        itemPrices.put("steak", 24.99);
        itemPrices.put("pasta", 14.99);
        itemPrices.put("pizza", 16.99);
        itemPrices.put("ice cream", 5.99);
        itemPrices.put("cake", 7.99);
    }

    public double calculatePrice(String order) {
        double totalPrice = 0.0;
        String[] items = order.split(",");
        
        for (String item : items) {
            item = item.trim().toLowerCase();
            // Extract quantity if present (e.g., "2x burger" -> quantity = 2)
            int quantity = 1;
            if (item.contains("x")) {
                String[] parts = item.split("x");
                try {
                    quantity = Integer.parseInt(parts[0].trim());
                    item = parts[1].trim();
                } catch (NumberFormatException e) {
                    // If parsing fails, assume quantity is 1
                }
            }
            
            // Find the base price for the item
            for (Map.Entry<String, Double> entry : itemPrices.entrySet()) {
                if (item.contains(entry.getKey())) {
                    totalPrice += entry.getValue() * quantity;
                    break;
                }
            }
        }
        
        return totalPrice;
    }

    public double applyDiscount(double price, double discountPercentage) {
        return price * (1 - discountPercentage / 100);
    }
} 