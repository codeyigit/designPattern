package com.designpattern.kitchen.maincourse;

/**
 * Concrete implementation of a Steak main course
 */
public class Steak extends MainCourse {
    public Steak() {
        this.name = "Ribeye Steak";
        this.price = 24.99;
        this.description = "Premium cut ribeye steak with herbs";
    }

    @Override
    public void prepare() {
        System.out.println("Preparing " + name + ":");
        System.out.println("1. Seasoning the steak");
        System.out.println("2. Grilling to perfection");
        System.out.println("3. Resting the meat");
        System.out.println("4. Adding herb butter");
    }
} 