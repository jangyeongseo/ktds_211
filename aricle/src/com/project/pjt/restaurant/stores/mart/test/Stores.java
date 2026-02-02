package com.project.pjt.restaurant.stores.mart.test;

/**
 * 판매점
 */
public class Stores {
    /**
     * 상품을 판매하는 메서드
     * - 고객이 결제한 금액(payMoney)에서 상품 가격(price)을 차감하여 거스름돈을 계산
     * - 고객의 보유 금액(money)에서 상품 가격만큼 차감
     * - 판매 내역(상품 가격, 거스름돈)을 콘솔에 출력
     *
     * @param customer 구매자 객체
     * @param price 상품 가격
     * @param payMoney 고객이 지불한 금액
     * @return 실제 결제된 상품 가격
     */
	public int sell(Customer customer, int price, int payMoney) {
		int change = payMoney - price; // 거스름돈 계산: 지불한 금액 - 상품 가격
		customer.setMoney(customer.getMoney() - price); // 고객의 보유 금액에서 상품 가격만큼 차감

		System.out.println("상품 가격: " + price);
		System.out.println("거스름돈: " + change);
		return price;
	}
}
