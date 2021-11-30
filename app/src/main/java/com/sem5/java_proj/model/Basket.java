
package com.sem5.java_proj.model;
import java.util.List;

public class Basket {
	private List<Product> productsToOrder;

	public Basket(List<Product> productsToOrder){
		this.productsToOrder = productsToOrder;
	}

	public List<Product> getProductsToOrder() {
		return productsToOrder;
	}

	public void addProduct(Product product){
		productsToOrder.add(product);
	}
}
