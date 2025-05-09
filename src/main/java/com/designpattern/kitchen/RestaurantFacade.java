package com.designpattern.kitchen;

import java.util.List;

/**
 * Facade class that provides a simplified interface to the complex kitchen subsystem.
 * This class implements the Facade pattern by hiding the complexity of the kitchen operations
 * behind a simple interface.
 */
public class RestaurantFacade {
    private final OrderManager orderManager;
    private final KitchenCoordinator kitchenCoordinator;
    private final NotificationHub notificationHub;
    private final PricingEngine pricingEngine;
    private final TranslatorAdapter translatorAdapter;

    public RestaurantFacade() {
        this.orderManager = new OrderManager();
        this.kitchenCoordinator = new KitchenCoordinator();
        this.notificationHub = new NotificationHub();
        this.pricingEngine = new PricingEngine();
        this.translatorAdapter = new TranslatorAdapter();
    }

    /**
     * Places an order in the system
     * @param order The order to be placed
     * @return The order ID
     */
    public String placeOrder(String order) {
        // Break down the order into courses
        List<String> courses = orderManager.breakDownOrder(order);
        
        // Translate the order for kitchen staff
        String translatedOrder = translatorAdapter.translateOrder(order);
        
        // Route each course to appropriate station
        for (String course : courses) {
            kitchenCoordinator.routeToStation(course, "appropriate station");
        }
        
        // Calculate price
        double price = pricingEngine.calculatePrice(order);
        
        // Notify about order placement
        String orderId = "ORD-" + System.currentTimeMillis();
        notificationHub.notifyOrderStatus(orderId, "PLACED");
        
        return orderId;
    }

    /**
     * Tracks the status of an order
     * @param orderId The ID of the order to track
     */
    public void trackOrder(String orderId) {
        notificationHub.notifyOrderStatus(orderId, "IN PROGRESS");
    }

    /**
     * Applies a discount to an order
     * @param orderId The ID of the order
     * @param discountPercentage The discount percentage to apply
     * @return The discounted price
     */
    public double applyDiscount(String orderId, double discountPercentage) {
        double originalPrice = pricingEngine.calculatePrice(orderId);
        return pricingEngine.applyDiscount(originalPrice, discountPercentage);
    }
} 