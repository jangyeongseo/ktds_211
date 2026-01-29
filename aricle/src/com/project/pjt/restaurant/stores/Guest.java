package com.project.pjt.restaurant.stores;

/**
 * 고객
 */
public class Guest {
	private int money; // 돈
	private int point; // 포인트

	public Guest(int money, int point) {
		this.money = money;
		this.point = point;
	}

	public int getPoint() {
		return this.point;
	}

	public void usePoint(int point) {
		this.point -= point;
	}

	public void addPoint(int point) {
		this.point += point;
	}

	public void pay(int amount) {
		this.money -= amount;

	}

	public void giveMoney(int remainMoney) {
		this.money += remainMoney;
	}

	@Override
	public String toString() {
		return "고객의 돈 : " + this.money + "고객의 포인트 : " + this.point;
	}

}
