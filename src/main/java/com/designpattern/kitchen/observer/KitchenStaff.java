package com.designpattern.kitchen.observer;

/**
 * Observer class for kitchen staff
 */
public class KitchenStaff implements OrderObserver {
    private final String staffId;
    private final String name;
    private boolean active;

    public KitchenStaff(String staffId, String name) {
        this.staffId = staffId;
        this.name = name;
        this.active = true;
    }

    @Override
    public void update(String orderId, String status) {
        System.out.println("Kitchen Staff " + name + " received update for order " + orderId + ": " + status);
        if (status.equals("PREPARING")) {
            System.out.println(name + " is starting to prepare order " + orderId);
        }
    }

    @Override
    public String getObserverId() {
        return "KITCHEN-" + staffId;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
        System.out.println(name + " is now " + (active ? "active" : "inactive"));
    }
} 