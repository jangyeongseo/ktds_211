package com.ktdsuniversity.edu.coffee;

/**
 * 작동 테스트
 */

public class CoffeeShopTest {
	public static void main(String[] args) {
		Coffee hotcoffee = new Coffee("따듯한 아메리카노", 2_000, 100);
		Coffee icecoffee = new Coffee("아이스티", 4_000, 60);

		// CoffeeShop starbuck = new CoffeeShop(hotcoffee, icecoffee);
		CoffeeShop starbuck = new CoffeeShop();

		int price = starbuck.orderCoffee();
		// this : starbuck 의 orderCoffee 호출한 인스턴스이다.
		System.out.println(price + "\n" + " ");

		// hot 주문
		price = starbuck.orderCoffee(2);
		System.out.println(price + "\n" + " ");

		price = starbuck.orderCoffee(2, 6);
		System.out.println(price + "\n" + " ");

		price = starbuck.orderCoffee(3, 4);
		System.out.println(price + "\n" + " ");

	}

}
