package com.project.pjt.restaurant.stores.restaurant;

import com.project.pjt.restaurant.stores.restaurant.custom.DrunkenException;
import com.project.pjt.restaurant.stores.restaurant.custom.FullException;

/**
 * 손님
 */
public class Guests {
	private String name; // 이름
	private double drunken; // 취함 정도
	private int fullness; // 배부름 정도
	private int money; // 소지하고 있는 금액

	private static final double MAX_DRUNKEN = 3.0; // 취함 한계
	private static final int MAX_FULLNESS = 100; // 배부름 한계

	public Guests(String name, int money) {
		this.name = name;
		this.money = money;
	}

	public Guests(String name, double drunken, int fullness, int money) {
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
		return this.fullness;
	}

	public void setFullness(int fullness) {
		this.fullness = fullness;
	}

	public int getMoney() {
		return this.money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	// 술의 알골 정도
	public void drink(double alcohol) throws DrunkenException {
		drunken += alcohol * 0.1;

		if (drunken > MAX_DRUNKEN) {
			throw new DrunkenException(name + " 너무 취했습니다. 더 이상 술 제공 금지");
		}
	}

	// 배부름의 정도
	public void eat(int weight) throws FullException {
		fullness += weight;

		if (fullness > MAX_FULLNESS) {
			throw new FullException(name + " 너무 배불러서 더 이상 먹으면 체해요. 더 이상 음식 제공 금지");
		}
	}

}
