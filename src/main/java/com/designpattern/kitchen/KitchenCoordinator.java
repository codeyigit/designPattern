package com.designpattern.kitchen;

/**
 * Subsystem class that coordinates between different kitchen stations
 */
public class KitchenCoordinator {
    private String determineStation(String course) {
        if (course.startsWith("STARTER")) {
            return "Prep Station";
        } else if (course.startsWith("MAIN")) {
            if (course.toLowerCase().contains("burger") || 
                course.toLowerCase().contains("steak")) {
                return "Grill Station";
            } else if (course.toLowerCase().contains("pasta")) {
                return "Pasta Station";
            } else if (course.toLowerCase().contains("pizza")) {
                return "Pizza Station";
            }
            return "Hot Line";
        } else if (course.startsWith("DESSERT")) {
            return "Dessert Station";
        } else if (course.startsWith("SIDE")) {
            if (course.toLowerCase().contains("fries")) {
                return "Fry Station";
            }
            return "Prep Station";
        }
        return "General Station";
    }

    public void routeToStation(String course, String station) {
        String targetStation = determineStation(course);
        System.out.println("Routing " + course + " to " + targetStation);
        
        // Simulate some processing time based on the course type
        try {
            if (course.startsWith("MAIN")) {
                Thread.sleep(1000); // Simulate longer processing for main courses
            } else {
                Thread.sleep(500); // Simulate shorter processing for other items
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
} 