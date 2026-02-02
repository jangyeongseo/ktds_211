package com.ktdsuniversity.edu.restaurant;

import java.util.List;

/**
 * 식당
 */
public class Restuarant {
	private String name;
	private List<Menu> menus;
	private int account;
	private int maxFullRate;
	private double maxDrunkenRate;

	public Restuarant(String name, List<Menu> menus, int maxFullRate, double maxDrunkenRate) {
		this.name = name;
		this.menus = menus;
		this.maxFullRate = maxFullRate;
		this.maxDrunkenRate = maxDrunkenRate;
	}

	public List<Menu> getMenus() {
		return this.menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public int getAccount() {
		return this.account;
	}

	public void setAccount(int account) {
		this.account = account;
	}

	public int getMaxFullRate() {
		return this.maxFullRate;
	}

	public void setMaxFullRate(int maxFullRate) {
		this.maxFullRate = maxFullRate;
	}

	public double getMaxDrunkenRate() {
		return this.maxDrunkenRate;
	}

	public void setMaxDrunkenRate(double maxDrunkenRate) {
		this.maxDrunkenRate = maxDrunkenRate;
	}

	public void addAccount(int money) {
		this.account += money;
	}

	public Menu servMenu(Customer customer, int menuIndex) {
		if (menuIndex < 0 || menuIndex >= this.menus.size()) {
			return null; // 없는 메뉴
		}

		Menu menu = this.menus.get(menuIndex);
		if (menu.getStock() == 0) {
			return null; // 품절
		}

		System.out.println("주문 금액: " + menu.getPrice());
		if (customer.isEnoughMoney(menu.getPrice())) {
			menu.setStock(menu.getStock() - 1);
			customer.giveMoney(this, menu.getPrice());
			return menu;
		}
		System.out.println(customer.getName() + "의 소지금 부족");

		return null;
	}

	public boolean isFullCustomer(Customer customer) {
		System.out.println(this.name + "식당의 배부름 기준: " + this.maxFullRate);
		return customer.getFullRate() >= this.maxFullRate;
	}

	public boolean isDrunkenCustomer(Customer customer) {
		System.out.println(this.name + "식당의 취함 기준: " + this.maxDrunkenRate);
		return customer.getAlcoholRate() >= this.maxDrunkenRate;
	}

	public void printStatus() {
		System.out.println();
		System.out.println(this.name + "상태 확인");
		System.out.println("Menu ========");
		for (int i = 0; i < this.menus.size(); i++) {

			System.out.print((i + 1) + ". ");
			if (this.menus.get(i).getIsAlcohol()) {
				System.out.print("주류");
				System.out.print(" / " + this.menus.get(i).getPrice() + "원");
				System.out.print(" / " + this.menus.get(i).getAlcohol() + "%");
			} else {
				System.out.print("식사류");
				System.out.print(" / " + this.menus.get(i).getPrice() + "원");
				System.out.print(" / " + this.menus.get(i).getWeight() + "g");
			}
			System.out.print(" / " + this.menus.get(i).getStock() + "개");
			System.out.println();
		}

		System.out.println("매출금: " + this.account);
	}

}
