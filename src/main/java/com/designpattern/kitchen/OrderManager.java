package com.designpattern.kitchen;

import com.designpattern.kitchen.starter.StarterFactory;
import com.designpattern.kitchen.maincourse.MainCourseFactory;
import com.designpattern.kitchen.dessert.DessertFactory;
import com.designpattern.kitchen.meal.Meal;
import com.designpattern.kitchen.observer.Order;
import com.designpattern.kitchen.observer.KitchenStaff;
import com.designpattern.kitchen.observer.Waiter;
import com.designpattern.kitchen.translator.Language;
import com.designpattern.kitchen.translator.RestaurantTranslator;
import com.designpattern.kitchen.pricing.PricingStrategy;
import com.designpattern.kitchen.pricing.WeekdayPricingStrategy;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import com.designpattern.kitchen.observer.CustomerInterface;

/**
 * Subsystem class that manages order breakdown into different courses
 */
public class OrderManager {
    private final StarterFactory starterFactory;
    private final MainCourseFactory mainCourseFactory;
    private final DessertFactory dessertFactory;
    private final List<Order> activeOrders;
    private final KitchenStaff chef;
    private final List<Waiter> waiters;
    private final RestaurantTranslator translator;
    private PricingStrategy currentPricingStrategy;
    private final Map<Integer, Waiter> tableAssignments;
    private final Map<String, Double> orderPrices;
    private final Map<String, List<String>> orderItems;

    public OrderManager() {
        this.starterFactory = StarterFactory.INSTANCE;
        this.mainCourseFactory = MainCourseFactory.INSTANCE;
        this.dessertFactory = DessertFactory.INSTANCE;
        this.activeOrders = new ArrayList<>();
        this.translator = new RestaurantTranslator();
        this.currentPricingStrategy = new WeekdayPricingStrategy(); // Default strategy
        this.tableAssignments = new HashMap<>();
        this.orderPrices = new HashMap<>();
        this.orderItems = new HashMap<>();
        
        // Initialize staff with different languages
        this.chef = new KitchenStaff("CHEF001", "John");
        this.waiters = new ArrayList<>();
        
        // Add waiters with different language preferences
        this.waiters.add(new Waiter("WAIT001", "Alice", Language.ENGLISH, translator));
        this.waiters.add(new Waiter("WAIT002", "Mehmet", Language.TURKISH, translator));
        this.waiters.add(new Waiter("WAIT003", "Yuki", Language.JAPANESE, translator));
    }

    public void setPricingStrategy(PricingStrategy strategy) {
        this.currentPricingStrategy = strategy;
        // Update pricing strategy for all waiters
        for (Waiter waiter : waiters) {
            waiter.setPricingStrategy(strategy);
        }
    }

    public Order placeOrder(int tableNumber, String orderItems) {
        List<String> courses = breakDownOrder(orderItems);
        String orderId = "ORD-" + System.currentTimeMillis();
        
        // Create new order
        Order order = new Order(orderId, courses);
        
        // Store order items
        this.orderItems.put(orderId, courses);
        
        // Register kitchen staff observer
        order.registerObserver(chef);
        
        // Assign and register waiter for this table if not already assigned
        Waiter assignedWaiter = tableAssignments.computeIfAbsent(tableNumber, 
            table -> waiters.get((table - 1) % waiters.size()));
        
        if (assignedWaiter.isActive()) {
            order.registerObserver(assignedWaiter);
            System.out.println(assignedWaiter.getName() + " is assigned to Table " + tableNumber);
        }
        
        // Register a CustomerInterface observer
        order.registerObserver(new CustomerInterface("TABLE-" + tableNumber));
        
        // Add to active orders
        activeOrders.add(order);
        
        // Calculate and store the base price for this order
        double basePrice = calculateBasePrice(orderItems);
        orderPrices.put(orderId, basePrice);
        
        // Update order status
        order.setStatus("RECEIVED");
        order.setStatus("PREPARING");
        
        return order;
    }

    public void completeOrder(String orderId) {
        Order order = findOrder(orderId);
        if (order != null) {
            // Get the base price before removing the order
            double basePrice = orderPrices.get(orderId);
            
            order.setStatus("READY");
            activeOrders.remove(order);
            
            // Display price information for the completed order
            displayOrderPrice(orderId, basePrice);
        }
    }

    private void displayOrderPrice(String orderId, double basePrice) {
        // Get the assigned waiter for this table
        String tableId = orderId.split("-")[1];
        int tableNumber = Integer.parseInt(tableId.substring(0, 1));
        Waiter assignedWaiter = tableAssignments.get(tableNumber);
        
        if (assignedWaiter != null && assignedWaiter.isActive()) {
            System.out.println("\n=== Order Details for " + orderId + " ===");
            // Display order items
            List<String> items = orderItems.get(orderId);
            if (items != null) {
                for (String item : items) {
                    System.out.println("- " + item);
                }
            }
            
            System.out.println("\n=== Price Information for Order " + orderId + " ===");
            // Display original price
            String originalPrice = assignedWaiter.getTranslator().formatPrice(basePrice, assignedWaiter.getPreferredLanguage());
            System.out.println(assignedWaiter.getName() + ": Original Price: " + originalPrice);
            
            // Display discounted price
            double finalPrice = assignedWaiter.getPricingStrategy().calculatePrice(basePrice);
            String formattedPrice = assignedWaiter.getTranslator().formatPrice(finalPrice, assignedWaiter.getPreferredLanguage());
            String discountInfo = assignedWaiter.getTranslator().translate(
                assignedWaiter.getPricingStrategy().getDescription(), 
                assignedWaiter.getPreferredLanguage()
            );
            System.out.println(assignedWaiter.getName() + ": Final Price: " + formattedPrice + " (" + discountInfo + ")");
        }
    }

    private double calculateBasePrice(String orderItems) {
        double totalPrice = 0.0;
        String[] items = orderItems.split(",");
        
        for (String item : items) {
            item = item.trim().toLowerCase();
            // Extract quantity if present (e.g., "2x burger" -> quantity = 2)
            int quantity = 1;
            if (item.contains("x")) {
                String[] parts = item.split("x");
                try {
                    quantity = Integer.parseInt(parts[0].trim());
                    item = parts[1].trim();
                } catch (NumberFormatException e) {
                    // If parsing fails, assume quantity is 1
                }
            }
            
            // Add base prices for items
            if (item.contains("salad")) totalPrice += 12.99 * quantity;
            else if (item.contains("soup")) totalPrice += 8.99 * quantity;
            else if (item.contains("burger")) totalPrice += 15.99 * quantity;
            else if (item.contains("steak")) totalPrice += 29.99 * quantity;
            else if (item.contains("ice cream")) totalPrice += 6.99 * quantity;
        }
        
        return totalPrice;
    }

    public Order findOrder(String orderId) {
        return activeOrders.stream()
                .filter(order -> order.getOrderId().equals(orderId))
                .findFirst()
                .orElse(null);
    }

    public List<String> breakDownOrder(String order) {
        List<String> courses = new ArrayList<>();
        
        // Split the order into individual items
        String[] items = order.split(",");
        
        for (String item : items) {
            item = item.trim();
            if (item.toLowerCase().contains("salad") || 
                item.toLowerCase().contains("soup") || 
                item.toLowerCase().contains("appetizer")) {
                // Create starter using the factory
                Meal starter = starterFactory.createMeal(
                    item.toLowerCase().contains("salad") ? "salad" : "soup"
                );
                courses.add("STARTER: " + starter.getName() + " - " + starter.getDescription());
            } else if (item.toLowerCase().contains("burger") || 
                      item.toLowerCase().contains("steak")) {
                // Create main course using the factory
                Meal mainCourse = mainCourseFactory.createMeal(
                    item.toLowerCase().contains("burger") ? "burger" : "steak"
                );
                courses.add("MAIN: " + mainCourse.getName() + " - " + mainCourse.getDescription());
            } else if (item.toLowerCase().contains("ice cream")) {
                // Create dessert using the factory
                Meal dessert = dessertFactory.createMeal("ice cream");
                courses.add("DESSERT: " + dessert.getName() + " - " + dessert.getDescription());
            } else {
                courses.add("SIDE: " + item);
            }
        }
        
        return courses;
    }

    public void setStaffActive(String staffId, boolean active) {
        if (staffId.startsWith("KITCHEN")) {
            chef.setActive(active);
        } else if (staffId.startsWith("WAITER")) {
            waiters.stream()
                  .filter(w -> w.getObserverId().equals(staffId))
                  .findFirst()
                  .ifPresent(w -> w.setActive(active));
        }
    }

    public void displayPrices(double basePrice) {
        // Display prices only for active waiters
        for (Waiter waiter : waiters) {
            if (waiter.isActive()) {
                waiter.displayPrice(basePrice);
            }
        }
    }
} 