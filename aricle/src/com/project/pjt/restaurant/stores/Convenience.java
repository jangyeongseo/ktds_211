package com.project.pjt.restaurant.stores;

/**
 * 편의점 고객을 대리고와야 한는가?
 */
public class Convenience extends Stores {
	// 구매자의 포인트가 100점이 넘을 경우
	public int usePoint(Customer customer, int price) {
		if (customer.getPoints() >= 100) {
			int used = customer.getPoints();
			customer.setPoints(0);
			return Math.min(used, price);
		}
		return 0;
	}

	// 구매자한테 포인트 지급
	public void savePoint(Customer customer, int price) {
		customer.setPoints(customer.getPoints() + (int) (price * 0.001));
	}

}
