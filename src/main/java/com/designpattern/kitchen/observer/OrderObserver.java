package com.designpattern.kitchen.observer;

/**
 * Observer interface for order status updates
 */
public interface OrderObserver {
    void update(String orderId, String status);
    String getObserverId();
    boolean isActive();
} 