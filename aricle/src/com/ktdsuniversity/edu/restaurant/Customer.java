package com.ktdsuniversity.edu.restaurant;

public class Customer {

	private String name;
	private int fullRate;
	private double alcoholRate;
	private int money;

	public Customer(String name, int money) {
		this.name = name;
		this.money = money;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFullRate() {
		return this.fullRate;
	}

	public void setFullRate(int fullRate) {
		this.fullRate = fullRate;
	}

	public double getAlcoholRate() {
		return this.alcoholRate;
	}

	public void setAlcoholRate(double alcoholRate) {
		this.alcoholRate = alcoholRate;
	}

	public int getMoney() {
		return this.money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void order(Restuarant restuarnt, int menuIndex) {
		this.printState();

		Menu menu = restuarnt.servMenu(this, menuIndex);
		if (menu != null) {
			if (menu.getIsAlcohol()) {
				if (!restuarnt.isDrunkenCustomer(this)) {
					System.out.println("주문 성공");
					this.addAlcoholRate(menu.getAlcohol());
				} else {
					System.out.println("주문 실패 - 너무 취함");
				}
			} else {
				if (!restuarnt.isFullCustomer(this)) {
					System.out.println("주문 성공");
					this.fullRate += menu.getWeight();
				} else {
					System.out.println("주문 실패 - 너무 배부름");
				}
			}
		}
	}

	public void addAlcoholRate(double alcohol) {
		this.alcoholRate += alcohol * 0.1;
		this.alcoholRate = (int) (this.alcoholRate * 100) / 100d;
	}

	public boolean isEnoughMoney(int price) {
		return this.money >= price;
	}

	public void giveMoney(Restuarant restaurant, int money) {
		this.money -= money;
		restaurant.addAccount(money);
	}

	public void printState() {
		System.out.println();
		System.out.println("고객명 : " + this.name);
		System.out.println(this.name + "의 취함 정도 : " + this.alcoholRate);
		System.out.println(this.name + "의 배부름 정도 : " + this.fullRate);
		System.out.println(this.name + "의 소지금: " + this.money);
	}
}
