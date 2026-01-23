package oop;

/**
 * 커피 판매하는 커피숍 : 배열
 */

public class CoffeeShopArray {

	Coffee[] drinks; // 베열

	public CoffeeShopArray(Coffee hot, Coffee ice, Coffee tea) {
		this.drinks = new Coffee[3];
		this.drinks[0] = hot;
		this.drinks[1] = ice;
		this.drinks[2] = tea;

	}

	/**
	 * 커피숍에서 커피를 판매한다.
	 * 
	 * @param menu     메뉴들의 번호 0. hot, 1. ice 2. tea
	 * @param quantity 주문 수량
	 * @return 주문 가격
	 */

	public int orderCoffee(int menu, int quantity) {
		if (menu < 0 && menu > this.drinks.length) {
			System.out.println("존재하지 않는 메뉴입니다");
			return 0;
		}

		Coffee drink = this.drinks[menu];
		System.out.println(drink.name + "음료를 " + quantity + "개 주문했습니다.");
		return drink.price * quantity;
	}

}
