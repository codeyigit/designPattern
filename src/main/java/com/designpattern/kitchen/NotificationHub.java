package com.designpattern.kitchen;

/**
 * Subsystem class that handles notifications for order status
 */
public class NotificationHub {
    public void notifyOrderStatus(String orderId, String status) {
        System.out.println("Order " + orderId + " status: " + status);
    }
} 