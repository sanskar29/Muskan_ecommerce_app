package com.sem5.java_proj.controller;

import android.util.Log;

import com.sem5.java_proj.model.Coupon;
import com.sem5.java_proj.model.Customer;

import java.text.DecimalFormat;

public class BillingController {
    private static BillingController INSTANCE;

    public static BillingController getInstance() {
        return INSTANCE;
    }

    private double totalPrice;
    private Coupon coupon;
    private Customer customer;

    private BillingController() {
        totalPrice = 0.0;
    }

    public double getTotalPrice(String code) {
        OrderController orderController = OrderController.getInstance();
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        totalPrice = 0;

        for (int i = 0; i < orderController.getProductsCount(); i++) {
            totalPrice += orderController.getProductPrice(i);
        }

        int discount = AdminController.getInstance().getReduction(code);
        Log.i("info ","Discount is "+String.valueOf(discount));
        if (discount != -1) {
            totalPrice = totalPrice - (totalPrice / 100 * discount);
        }

        return Double.parseDouble(decimalFormat.format(totalPrice));
    }

    public void createCustomer(String name, String email, String address, String postalCode, String telephone) {
        customer = new Customer(name, email, address, postalCode, telephone);
    }

    public Customer getCustomer() {
        return this.customer;
    }

    static void instantiateBillingController(){
        INSTANCE = new BillingController();
    }

}
