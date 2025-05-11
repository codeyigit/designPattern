/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.designpattern.kitchen.observer;

/**
 *
 * @author damla
 */

public class CustomerInterface implements OrderObserver {
    private final String customerId;
    public CustomerInterface(String customerId) {
        this.customerId = customerId;
    }
    @Override
    public void update(String orderId, String status) {
        System.out.printf("[For Customer %s] Order %s status: %s%n",
                          customerId, orderId, status);
    }
    @Override public String getObserverId() { return customerId; }
    @Override public boolean isActive()     { return true; }
}
