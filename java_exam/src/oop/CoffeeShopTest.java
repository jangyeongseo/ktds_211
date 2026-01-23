package oop;

/**
 * 작동 테스트
 */

public class CoffeeShopTest {
	public static void main(String[] args) {
		Coffee hotcoffee = new Coffee("따듯한 아메리카노", 2_000);
		Coffee icecoffee = new Coffee("아이스티", 4_000);
		Coffee tea = new Coffee("캐모마일 티", 5000);

		CoffeeShopArray starbuck = new CoffeeShopArray(hotcoffee, icecoffee, tea);

		int price = starbuck.orderCoffee(1, 3);
		// this : starbuck 의 orderCoffee 호출한 인스턴스이다.
		System.out.println(price);

		price = starbuck.orderCoffee(0, 10);
		System.out.println(price);

		price = starbuck.orderCoffee(2, 6);
		System.out.println(price);

		price = starbuck.orderCoffee(3, 4);
		System.out.println(price);

	}

}
