package com.designpattern.model;

/**
 * Represents a menu item in the restaurant.
 * This class will be used as a base for different types of menu items.
 */
public class MenuItem {
    private String id;
    private String name;
    private String description;
    private double price;
    private boolean available;

    public MenuItem(String id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.available = true;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return String.format("%s - %s ($%.2f)", name, description, price);
    }
} 