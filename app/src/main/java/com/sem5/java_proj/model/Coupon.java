
package com.sem5.java_proj.model;


public class
Coupon {
	
	private String couponCode;
	private int percentageReduction;

	public Coupon() {
	}

	public Coupon(String code, int percentageReduction) {
		this.couponCode = code;
		this.percentageReduction = percentageReduction;
	}

	public String getCode() {
		return couponCode;
	}

	public void setCode(String code) {
		this.couponCode = code;
	}

	public int getPercentageReduction() {
		return percentageReduction;
	}

	public void setPercentageReduction(int percentageReduction) {
		this.percentageReduction = percentageReduction;
	}
	
	
	
	
	
}
