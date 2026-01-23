package com.ktdsuniversity.edu.vendingmachine.item;

/**
 * 음료수 종료
 */

public class Drinkables {
	private int price; // 음료수 가격
	private String name; // 음료수 이름
	private int stock; // 재고

	public Drinkables(String name, int price, int stock) {
		this.name = name;
		this.price = price;
		this.stock = stock;
	}

	// get / set
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

}
