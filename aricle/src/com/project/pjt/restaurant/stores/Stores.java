package com.project.pjt.restaurant.stores;

/**
 * 판매점
 */
public class Stores {
	public int sell(Customer customer, int price, int payMoney) {
		int change = payMoney - price;
		customer.setMoney(customer.getMoney() - price);

		System.out.println("상품 가격: " + price);
		System.out.println("거스름돈: " + change);
		return price;
	}
}
