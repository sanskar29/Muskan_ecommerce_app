package com.sem5.java_proj.controller;
import com.sem5.java_proj.model.Coupon;
import com.sem5.java_proj.model.Product;
import com.sem5.java_proj.model.Shop;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AdminController {

    private Shop shop;
    private List<Product> productList;
    private List<Coupon> couponList;

    private static AdminController INSTANCE;

    public static AdminController getInstance () {
        return INSTANCE;
    }

    private AdminController() {
        productList = new ArrayList<>();
        productList.add (new Product (new BigDecimal("65999"), "iPhone 12"));
        productList.add (new Product (new BigDecimal("45999"), "iPad"));
        productList.add (new Product (new BigDecimal("60000"), "One Plus 9"));
        productList.add (new Product (new BigDecimal("29999"), "Samsung Z2"));
        productList.add (new Product (new BigDecimal("79999"), "iPhone 13"));
        productList.add (new Product (new BigDecimal("105999"), "Samsung S21 Ultra"));
        productList.add (new Product (new BigDecimal("60000"), "Samsung Note 20"));
        productList.add (new Product (new BigDecimal("39999"), "Samsung S20"));

        couponList = new ArrayList<>();
        couponList.add (new Coupon ("SummerOffer", 20));
        couponList.add (new Coupon ("NewUser12542", 25));

        shop = new Shop (productList, couponList);
    }

    public Shop getShop(){
        return shop;
    }

    public Product getProduct (int index) {
        return shop.getExistingProducts().get(index);
    }

    public int getReduction (String code) {
        for (Coupon coupon: shop.getExistingCoupons()) {
            if (coupon.getCode().equals(code))
                return coupon.getPercentageReduction();
        }
        return -1;
    }

    public static void instantiateApp(){
        INSTANCE = new AdminController();
        OrderController.instantiateOrderController();
        BillingController.instantiateBillingController();
    }


}
