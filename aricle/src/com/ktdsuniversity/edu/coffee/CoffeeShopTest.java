package com.ktdsuniversity.edu.coffee;

/**
 * 작동 테스트
 */

public class CoffeeShopTest {
	public static void main(String[] args) {
		Coffee hotcoffee = new Coffee("따듯한 아메리카노", 2_000, 100);
		Coffee icecoffee = new Coffee("아이스티", 4_000, 60);

		CoffeeShop starbuck = new CoffeeShop(hotcoffee, icecoffee);

		int price = starbuck.orderCoffee(1, 3);
		// this : starbuck 의 orderCoffee 호출한 인스턴스이다.
		System.out.println(price + "\n" + " ");

		price = starbuck.orderCoffee(0, 10);
		System.out.println(price + "\n" + " ");

		price = starbuck.orderCoffee(2, 6);
		System.out.println(price + "\n" + " ");

		price = starbuck.orderCoffee(3, 4);
		System.out.println(price + "\n" + " "
		);

	}

}
