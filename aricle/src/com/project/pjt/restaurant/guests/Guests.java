package com.project.pjt.restaurant.guests;

/**
 * 손님
 */
public class Guests {
	private String name; // 이름
	private double drunken; // 취함 정도
	private int fullness; // 배부름 정도
	private int money; // 소지하고 있는 금액

	public Guests(String name, int money) {
		this.name = name;
		this.money = money;
		this.drunken = 0.0;
		this.fullness = 0;
	}

	public Guests(String name, double drunken, int fullness, int money) {
		super();
		this.name = name;
		this.drunken = drunken;
		this.fullness = fullness;
		this.money = money;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getDrunken() {
		return drunken;
	}

	public void setDrunken(double drunken) {
		this.drunken = drunken;
	}

	public int getFullness() {
		return fullness;
	}

	public void setFullness(int fullness) {
		this.fullness = fullness;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	// 술의 알골 정도
	public void drink(double alcohol) {
		drunken += alcohol * 0.1;
	}

	// 배부름의 정도
	public void eat(int weight) {
		fullness += weight;
	}

}
