package com.designpattern.kitchen.starter;

/**
 * Concrete implementation of a Salad starter
 */
public class Salad extends Starter {
    public Salad() {
        this.name = "Garden Salad";
        this.price = 8.99;
        this.description = "Fresh mixed greens with vegetables";
    }

    @Override
    public void prepare() {
        System.out.println("Preparing " + name + ":");
        System.out.println("1. Washing and chopping vegetables");
        System.out.println("2. Mixing greens and vegetables");
        System.out.println("3. Adding dressing");
        System.out.println("4. Garnishing with croutons");
    }
} 