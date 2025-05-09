package com.designpattern.kitchen.maincourse;

/**
 * Concrete implementation of a Burger main course
 */
public class Burger extends MainCourse {
    public Burger() {
        this.name = "Classic Burger";
        this.price = 12.99;
        this.description = "Juicy beef patty with fresh vegetables";
    }

    @Override
    public void prepare() {
        System.out.println("Preparing " + name + ":");
        System.out.println("1. Grilling the beef patty");
        System.out.println("2. Toasting the bun");
        System.out.println("3. Assembling with vegetables");
        System.out.println("4. Adding special sauce");
    }
} 