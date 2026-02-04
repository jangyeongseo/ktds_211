package com.project.pjt.restaurant.stores.store.test;

/**
 * 편의점
 */
public class Convenience extends Stores {
	/**
	 * 구매자의 포인트 사용 로직
	 *  - 고객의 포인트가 100점 이상일 경우에만 사용 가능
	 *  - 결제 금액보다 포인트가 많을 경우 결제 금액까지만 사용
	 *  - 포인트가 100점 미만이면 사용 불가
	 *  
	 * @param customer 현재 보유 포인트
	 * @param price 포인트
	 * @return
	 */
	public int usePoint(Customer customer, int price) {
		if (customer.getPoints() >= 100) {
			int used = customer.getPoints();
			customer.setPoints(0);
			return Math.min(used, price);
		}
		return 0;
	}

	/**
	 * 구매자에게 포인트 적립 로직
	 * 	- 결제 금액의 0.1%를 포인트로 적립 ex> 10,000원 결제 시 10포인트 적립
	 * 
	 * @param customer 현재 보유 포인트
	 * @param price 포인트
	 */
	public void savePoint(Customer customer, int price) {
		customer.setPoints(customer.getPoints() + (int) (price * 0.001));
	}

}
