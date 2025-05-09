package com.designpattern.kitchen.starter;

/**
 * Concrete implementation of a Soup starter
 */
public class Soup extends Starter {
    public Soup() {
        this.name = "Tomato Soup";
        this.price = 6.99;
        this.description = "Creamy tomato soup with herbs";
    }

    @Override
    public void prepare() {
        System.out.println("Preparing " + name + ":");
        System.out.println("1. Heating the soup base");
        System.out.println("2. Adding fresh herbs");
        System.out.println("3. Garnishing with croutons");
        System.out.println("4. Adding a dollop of cream");
    }
} 