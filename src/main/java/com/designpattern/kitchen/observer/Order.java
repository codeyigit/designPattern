package com.designpattern.kitchen.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Order class that acts as the Subject in the Observer pattern
 */
public class Order {
    private final String orderId;
    private String status;
    private final List<OrderObserver> observers;
    private final List<String> items;

    public Order(String orderId, List<String> items) {
        this.orderId = orderId;
        this.items = items;
        this.status = "RECEIVED";
        this.observers = new ArrayList<>();
    }

    public void registerObserver(OrderObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            System.out.println(observer.getObserverId() + " registered for order " + orderId);
        }
    }

    public void removeObserver(OrderObserver observer) {
        observers.remove(observer);
        System.out.println(observer.getObserverId() + " unregistered from order " + orderId);
    }

    public void setStatus(String newStatus) {
        this.status = newStatus;
        notifyObservers();
    }

    private void notifyObservers() {
        for (OrderObserver observer : observers) {
            if (observer.isActive()) {
                observer.update(orderId, status);
            }
        }
    }

    public String getOrderId() {
        return orderId;
    }

    public String getStatus() {
        return status;
    }

    public List<String> getItems() {
        return items;
    }
} 