package com.project.pjt.restaurant.stores.store.test;

/**
 * 백화점 rating: 0=일반, 1=VIP, 2=VVIP
 */
public class Department extends Stores {
	
    /**
     * 고객 등급에 따라 가격을 할인해주는 메서드
     * - VIP(1): 3% 할인
     * - VVIP(2): 10% 할인
     * - 일반(0): 할인 없음
     *
     * @param customer 고객 객체
     * @param price 원래 가격
     * @return 할인 적용된 가격
     */
	public int discount(Customer customer, int price) {
		// VIP 등급일 경우 3% 할인
		if (customer.getRating() == 1)
			return (int) (price * 0.97);

		// VVIP 등급일 경우 10% 할인
		if (customer.getRating() == 2)
			return (int) (price * 0.90);
		return price;
	}

	/**
     * 고객이 보유한 포인트를 사용하여 결제 금액을 줄이는 메서드
     * - 포인트가 10,000 이상일 때만 사용 가능
     * - 결제 금액보다 포인트가 많으면 결제 금액만큼만 사용
     *
     * @param customer 고객 객체
     * @param price 결제 금액
     * @return 실제 사용된 포인트 금액
     */
	public int usePoint(Customer customer, int price) {
		if (customer.getPoints() >= 10000) {
			int used = Math.min(customer.getPoints(), price);
			customer.setPoints(customer.getPoints() - used);
			return used;
		}
		return 0;
	}

	/**
     * 고객의 등급에 따라 포인트를 적립하는 메서드
     * - 일반(0): 결제 금액의 0.5% 적립
     * - VVIP(2): 결제 금액의 3% 적립
     * - VIP(1): 적립 없음
     *
     * @param customer 고객 객체
     * @param price 결제 금액
     */
	public void savePoint(Customer customer, int price) {
		if (customer.getRating() == 0)
			customer.setPoints(customer.getPoints() + (int) (price * 0.005));
		else if (customer.getRating() == 2)
			customer.setPoints(customer.getPoints() + (int) (price * 0.03));
	}
}
