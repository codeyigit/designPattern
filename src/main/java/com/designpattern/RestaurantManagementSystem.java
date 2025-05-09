package com.designpattern;

import com.designpattern.facade.RestaurantFacade;
import com.designpattern.kitchen.observer.Order;

/**
 * Main class for the Restaurant Management System.
 * This is the entry point of our application.
 */
public class RestaurantManagementSystem {
    public static void main(String[] args) {
        System.out.println("=== Restaurant Management System ===\n");
        
        // Initialize restaurant
        RestaurantFacade restaurant = new RestaurantFacade();
        
        System.out.println("Opening the restaurant...");
        System.out.println("Setting up staff schedules...");
        
        // Set up staff
        restaurant.setStaffAvailability("WAITER-WAIT001", true); // Alice (English)
        restaurant.setStaffAvailability("WAITER-WAIT002", true); // Mehmet (Turkish)
        restaurant.setStaffAvailability("WAITER-WAIT003", true); // Yuki (Japanese)
        
        try {
            // First customer - Regular customer during weekday
            System.out.println("\n=== Customer 1 (Regular) ===");
            restaurant.setPricingStrategy(false); // Regular pricing
            
            System.out.println("Taking order from Table 1...");
            Thread.sleep(1000);
            
            Order order1 = restaurant.placeOrder("1x Salad, 2x Burger");
            System.out.println("Order placed for Table 1");
            
            // Second customer - Student
            System.out.println("\n=== Customer 2 (Student) ===");
            restaurant.setPricingStrategy(true); // Student pricing
            
            System.out.println("Taking order from Table 2...");
            Thread.sleep(800);
            
            Order order2 = restaurant.placeOrder("1x Soup, 1x Steak, 1x Ice Cream");
            System.out.println("Order placed for Table 2");
            
            // Show initial prices for both orders
            System.out.println("\n=== Price Information ===");
            System.out.println("Table 1 (Regular Customer):");
            restaurant.setPricingStrategy(false);
            restaurant.displayPrices(45.99);
            
            System.out.println("\nTable 2 (Student):");
            restaurant.setPricingStrategy(true);
            restaurant.displayPrices(45.99);
            
            // Track orders progress
            System.out.println("\n=== Kitchen Progress ===");
            Thread.sleep(1500);
            restaurant.trackOrder(order1.getOrderId());
            Thread.sleep(800);
            restaurant.trackOrder(order2.getOrderId());
            
            // First order complete
            Thread.sleep(2000);
            System.out.println("\nCompleting order for Table 1...");
            restaurant.completeOrder(order1.getOrderId());
            
            // Third customer arrives - Regular
            System.out.println("\n=== Customer 3 (Regular) ===");
            restaurant.setPricingStrategy(false);
            
            System.out.println("Taking order from Table 3...");
            Thread.sleep(1000);
            
            Order order3 = restaurant.placeOrder("2x Soup, 1x Ice Cream");
            System.out.println("Order placed for Table 3");
            
            // Complete remaining orders
            Thread.sleep(1500);
            System.out.println("\nCompleting order for Table 2...");
            restaurant.completeOrder(order2.getOrderId());
            
            Thread.sleep(1500);
            System.out.println("\nCompleting order for Table 3...");
            restaurant.completeOrder(order3.getOrderId());
            
            System.out.println("\n=== End of Service ===");
            System.out.println("All orders completed successfully!");
            
        } catch (InterruptedException e) {
            System.out.println("Restaurant service interrupted!");
        }
    }
} 