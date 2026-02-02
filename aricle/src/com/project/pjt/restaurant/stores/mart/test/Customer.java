package com.project.pjt.restaurant.stores.mart.test;

/**
 * 고객
 */
public class Customer {
	private String name; // 고객 성함
	private int money; // 돈
	private int points; // 포인트 : 가지고 있는
	private int rating; // 등급

	public Customer() {
	}

	// get/ set
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
}
