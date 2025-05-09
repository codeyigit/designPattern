package com.designpattern.kitchen.observer;

import com.designpattern.kitchen.translator.Language;
import com.designpattern.kitchen.translator.TranslationService;
import com.designpattern.kitchen.pricing.PricingStrategy;

/**
 * Observer class for waiters with language support
 */
public class Waiter implements OrderObserver {
    private final String waiterId;
    private final String name;
    private boolean active;
    private final Language preferredLanguage;
    private final TranslationService translator;
    private PricingStrategy pricingStrategy;

    public Waiter(String waiterId, String name, Language preferredLanguage, 
                 TranslationService translator, PricingStrategy pricingStrategy) {
        this.waiterId = waiterId;
        this.name = name;
        this.active = true;
        this.preferredLanguage = preferredLanguage;
        this.translator = translator;
        this.pricingStrategy = pricingStrategy;
    }

    @Override
    public void update(String orderId, String status) {
        // Translate status to waiter's preferred language
        String translatedStatus = translator.translateOrderStatus(status, preferredLanguage);
        System.out.println("Waiter " + name + " received update for order " + orderId + ": " + translatedStatus);
        
        if (status.equals("READY")) {
            String message = translator.translate("Picking up order for delivery", preferredLanguage);
            System.out.println(name + ": " + message + " - " + orderId);
        }
    }

    public void displayPrice(double basePrice) {
        // Apply pricing strategy and format in preferred language
        double finalPrice = pricingStrategy.calculatePrice(basePrice);
        String formattedPrice = translator.formatPrice(finalPrice, preferredLanguage);
        String discountInfo = translator.translate(pricingStrategy.getDescription(), preferredLanguage);
        System.out.println(name + ": " + formattedPrice + " (" + discountInfo + ")");
    }

    @Override
    public String getObserverId() {
        return "WAITER-" + waiterId;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
        String status = translator.translate(active ? "active" : "inactive", preferredLanguage);
        System.out.println(name + " is now " + status);
    }

    public void setPricingStrategy(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }
} 