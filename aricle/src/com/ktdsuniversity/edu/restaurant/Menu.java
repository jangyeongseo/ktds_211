package com.ktdsuniversity.edu.restaurant;

/**
 * 메뉴 : 배부름, 술
 */
public class Menu {
	private boolean isAlcohol;
	private int weight;
	private double alcohol;
	private int price;
	private int stock;

	public Menu(boolean isAlcohol, int weight, double alcohol, int price, int stock) {
		this.isAlcohol = isAlcohol;
		this.weight = weight;
		this.alcohol = alcohol;
		this.price = price;
		this.stock = stock;
	}

	public boolean getIsAlcohol() {
		return this.isAlcohol;
	}

	public void setIsAlcohol(boolean isAlcohol) {
		this.isAlcohol = isAlcohol;
	}

	public int getWeight() {
		return this.weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public double getAlcohol() {
		return this.alcohol;
	}

	public void setAlcohol(double alcohol) {
		this.alcohol = alcohol;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return this.stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

}
