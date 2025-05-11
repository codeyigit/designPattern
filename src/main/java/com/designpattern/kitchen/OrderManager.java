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
import com.designpattern.kitchen.pricing.WeekdayPricingStrategy;
import com.designpattern.kitchen.pricing.StudentPricingStrategy;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
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

    public OrderManager() {
        this.starterFactory = StarterFactory.INSTANCE;
        this.mainCourseFactory = MainCourseFactory.INSTANCE;
        this.dessertFactory = DessertFactory.INSTANCE;
        this.activeOrders = new ArrayList<>();
        this.translator = new RestaurantTranslator();
        
        // Initialize staff with different languages and pricing strategies
        this.chef = new KitchenStaff("CHEF001", "John");
        this.waiters = new ArrayList<>();
        
        // Add waiters with different language preferences
        this.waiters.add(new Waiter("WAIT001", "Alice", Language.ENGLISH, 
                                   translator, new WeekdayPricingStrategy()));
        this.waiters.add(new Waiter("WAIT002", "Mehmet", Language.TURKISH, 
                                   translator, new WeekdayPricingStrategy()));
        this.waiters.add(new Waiter("WAIT003", "Yuki", Language.JAPANESE, 
                                   translator, new StudentPricingStrategy()));
    }

    public Order placeOrder(int tableNumber, String orderItems) {
        List<String> courses = breakDownOrder(orderItems);
        String orderId = "ORD-" + System.currentTimeMillis();
        
        // Create new order
        Order order = new Order(orderId, courses);
        
        // Register observers
        order.registerObserver(chef);
        for (Waiter waiter : waiters) {
            if (waiter.isActive()) {
                order.registerObserver(waiter);
            }
        }
        
        // Register a CustomerInterface observer
        order.registerObserver(new CustomerInterface("TABLE-" + tableNumber));
        
        // Add to active orders
        activeOrders.add(order);
        
        // Update order status
        order.setStatus("RECEIVED");
        order.setStatus("PREPARING");
        
        return order;
    }

    public void completeOrder(String orderId) {
        Order order = findOrder(orderId);
        if (order != null) {
            order.setStatus("READY");
            activeOrders.remove(order);
        }
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
        for (Waiter waiter : waiters) {
            if (waiter.isActive()) {
                waiter.displayPrice(basePrice);
            }
        }
    }
} 