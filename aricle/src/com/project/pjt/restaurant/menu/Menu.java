package com.project.pjt.restaurant.menu;

/**
 * 메뉴 : 배부름, 술
 */
public class Menu {
	private String type; // 주류 / 식사류
	private int price; // 메뉴 가격
	private double alcohol; // 주류용
	private int weight; // 식사용
	private int stock; // 자본금

	public Menu(String type, int price, double alcohol, int weight, int stock) {
		this.type = type;
		this.price = price;
		this.alcohol = alcohol;
		this.weight = weight;
		this.stock = stock;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public double getAlcohol() {
		return alcohol;
	}

	public void setAlcohol(double alcohol) {
		this.alcohol = alcohol;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

}
