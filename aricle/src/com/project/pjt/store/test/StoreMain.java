package com.project.pjt.store.test;

public class StoreMain {
	public static void main(String[] args) {

		/*
		 * =============================== 편의점 - 포인트 부족
		 */
		Customer c1 = new Customer();
		c1.setMoney(10000);
		c1.setPoints(50); // 100 미만
		c1.setRating(0);

		Convenience conv = new Convenience();

		int price1 = 3000;
		int used1 = conv.usePoint(c1, price1);
		conv.sell(c1, price1 - used1, 3000);
		conv.savePoint(c1, price1);

		System.out.println("결과: 돈= " + c1.getMoney() + ", 포인트= " + c1.getPoints());
		System.out.println("=================================\n");

		/*
		 * =============================== 편의점 - 포인트 전액 사용
		 */
		Customer c2 = new Customer();
		c2.setMoney(10000);
		c2.setPoints(2000);
		c2.setRating(0);

		int price2 = 5000;
		int used2 = conv.usePoint(c2, price2);
		conv.sell(c2, price2 - used2, 3000);
		conv.savePoint(c2, price2);

		System.out.println("결과: 돈= " + c2.getMoney() + ", 포인트= " + c2.getPoints());
		System.out.println("=================================\n");

		/*
		 * =============================== 편의점 - 포인트 > 상품 가격
		 */
		Customer c3 = new Customer();
		c3.setMoney(20000);
		c3.setPoints(8000);
		c3.setRating(0);

		int price3 = 5000;
		int used3 = conv.usePoint(c3, price3);
		conv.sell(c3, price3 - used3, 0);
		conv.savePoint(c3, price3);

		System.out.println("결과: 돈= " + c3.getMoney() + ", 포인트= " + c3.getPoints());
		System.out.println("=================================\n");

		/*
		 * =============================== 백화점 - 일반 고객
		 */
		Customer c4 = new Customer();
		c4.setMoney(30000);
		c4.setPoints(2000);
		c4.setRating(0); // 일반

		Department dept = new Department();

		int price4 = dept.discount(c4, 10000);
		int used4 = dept.usePoint(c4, price4);
		dept.sell(c4, price4 - used4, 10000);
		dept.savePoint(c4, 10000);

		System.out.println("결과: 돈= " + c4.getMoney() + ", 포인트= " + c4.getPoints());
		System.out.println("=================================\n");

		/*
		 * =============================== 백화점 - VIP
		 */
		Customer c5 = new Customer();
		c5.setMoney(30000);
		c5.setPoints(20000);
		c5.setRating(1); // VIP

		int price5 = dept.discount(c5, 10000);
		int used5 = dept.usePoint(c5, price5);
		dept.sell(c5, price5 - used5, 10000);
		dept.savePoint(c5, 10000);

		System.out.println("결과: 돈= " + c5.getMoney() + ", 포인트= " + c5.getPoints());
		System.out.println("=================================\n");

		/*
		 * =============================== 백화점 - VVIP
		 */
		Customer c6 = new Customer();
		c6.setMoney(50000);
		c6.setPoints(15000);
		c6.setRating(2); // VVIP

		int price6 = dept.discount(c6, 20000);
		int used6 = dept.usePoint(c6, price6);
		dept.sell(c6, price6 - used6, 20000);
		dept.savePoint(c6, 20000);

		System.out.println("결과: 돈= " + c6.getMoney() + ", 포인트= " + c6.getPoints());
	}
}
