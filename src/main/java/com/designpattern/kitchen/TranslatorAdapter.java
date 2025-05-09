package com.designpattern.kitchen;

import java.util.HashMap;
import java.util.Map;

/**
 * Subsystem class that handles order translations for kitchen staff
 */
public class TranslatorAdapter {
    private final Map<String, String> kitchenTerms;

    public TranslatorAdapter() {
        kitchenTerms = new HashMap<>();
        // Initialize kitchen-specific terms
        kitchenTerms.put("burger", "BGR");
        kitchenTerms.put("fries", "FRS");
        kitchenTerms.put("coke", "COK");
        kitchenTerms.put("salad", "SLD");
        kitchenTerms.put("soup", "SOP");
        kitchenTerms.put("steak", "STK");
        kitchenTerms.put("pasta", "PST");
        kitchenTerms.put("pizza", "PZZ");
        kitchenTerms.put("ice cream", "ICR");
        kitchenTerms.put("cake", "CKE");
    }

    public String translateOrder(String order) {
        String translatedOrder = order;
        
        // Replace common terms with kitchen codes
        for (Map.Entry<String, String> entry : kitchenTerms.entrySet()) {
            translatedOrder = translatedOrder.replaceAll(
                "(?i)" + entry.getKey(),
                entry.getValue()
            );
        }
        
        // Add kitchen-specific instructions
        if (translatedOrder.toLowerCase().contains("burger")) {
            translatedOrder += " [GRILL]";
        }
        if (translatedOrder.toLowerCase().contains("fries")) {
            translatedOrder += " [FRYER]";
        }
        if (translatedOrder.toLowerCase().contains("salad")) {
            translatedOrder += " [COLD]";
        }
        
        return translatedOrder;
    }
} 