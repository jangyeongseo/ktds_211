package com.project.pjt.restaurant.stores.mart.test;

/**
 * 백화점 rating: 0=일반, 1=VIP, 2=VVIP
 */
public class Department extends Stores {
	public int discount(Customer customer, int price) {
		if (customer.getRating() == 1)
			return (int) (price * 0.97);
		if (customer.getRating() == 2)
			return (int) (price * 0.90);
		return price;
	}

	public int usePoint(Customer customer, int price) {
		if (customer.getPoints() >= 10000) {
			int used = Math.min(customer.getPoints(), price);
			customer.setPoints(customer.getPoints() - used);
			return used;
		}
		return 0;
	}

	public void savePoint(Customer customer, int price) {
		if (customer.getRating() == 0)
			customer.setPoints(customer.getPoints() + (int) (price * 0.005));
		else if (customer.getRating() == 2)
			customer.setPoints(customer.getPoints() + (int) (price * 0.03));
	}
}
