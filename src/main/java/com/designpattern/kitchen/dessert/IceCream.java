package com.designpattern.kitchen.dessert;

/**
 * Concrete implementation of an Ice Cream dessert
 */
public class IceCream extends Dessert {
    public IceCream() {
        this.name = "Vanilla Ice Cream";
        this.price = 5.99;
        this.description = "Creamy vanilla ice cream with toppings";
    }

    @Override
    public void prepare() {
        System.out.println("Preparing " + name + ":");
        System.out.println("1. Scooping vanilla ice cream");
        System.out.println("2. Adding chocolate sauce");
        System.out.println("3. Adding whipped cream");
        System.out.println("4. Garnishing with sprinkles");
    }
} 