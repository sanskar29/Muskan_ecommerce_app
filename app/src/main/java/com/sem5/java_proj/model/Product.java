
package com.sem5.java_proj.model;

import java.math.BigDecimal;


public class Product {
	private BigDecimal price;
	private String name;


	public Product(BigDecimal price, String name) {
		this.price = price;
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}
	
	
	
	
	
	
}
