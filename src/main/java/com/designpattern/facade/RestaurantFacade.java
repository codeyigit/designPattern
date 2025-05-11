package com.designpattern.facade;

import com.designpattern.kitchen.OrderManager;
import com.designpattern.kitchen.observer.Order;
import com.designpattern.kitchen.pricing.PricingStrategy;
import com.designpattern.kitchen.pricing.WeekdayPricingStrategy;
import com.designpattern.kitchen.pricing.StudentPricingStrategy;

/**
 * Facade class that provides a simplified interface to the restaurant system.
 * This is the main entry point for all restaurant operations.
 */
public class RestaurantFacade {
    private final OrderManager orderManager;

    public RestaurantFacade() {
        this.orderManager = new OrderManager();
    }

    /**
     * Places a new order in the system
     */
    public Order placeOrder(int tableNumber, String orderItems) { 
        return orderManager.placeOrder(tableNumber, orderItems);
    }

    /**
     * Tracks the status of an existing order
     */
    public void trackOrder(String orderId) {
        Order order = orderManager.findOrder(orderId);
        if (order != null) {
            System.out.println("Order " + orderId + " status: " + order.getStatus());
        } else {
            System.out.println("Order not found: " + orderId);
        }
    }

    /**
     * Marks an order as complete
     */
    public void completeOrder(String orderId) {
        orderManager.completeOrder(orderId);
    }

    /**
     * Sets the active status of a staff member
     */
    public void setStaffAvailability(String staffId, boolean active) {
        orderManager.setStaffActive(staffId, active);
    }

    /**
     * Changes the pricing strategy
     */
    public void setPricingStrategy(boolean isStudent) {
        PricingStrategy strategy = isStudent ? 
            new StudentPricingStrategy() : new WeekdayPricingStrategy();
        orderManager.setPricingStrategy(strategy);
    }

    /**
     * Displays prices for all active waiters
     */
    public void displayPrices(double basePrice) {
        orderManager.displayPrices(basePrice);
    }
} 