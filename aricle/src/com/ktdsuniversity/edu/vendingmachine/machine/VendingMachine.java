package com.ktdsuniversity.edu.vendingmachine.machine;

import com.ktdsuniversity.edu.vendingmachine.item.Drinkables;

/**
 * 자판기
 */

public class VendingMachine {

	Drinkables[] drinkables; // 음료수 배열

	/**
	 * 배열에 음료 개수 넣기
	 * 
	 * @param bacchus 박카스
	 * @param monster 몬스터
	 * @param hotSix  핫식스
	 * @param milkis  밀키스
	 */

	public VendingMachine(Drinkables bacchus, Drinkables monster, Drinkables hotSix, Drinkables milkis) {
		this.drinkables = new Drinkables[4];
		this.drinkables[0] = bacchus;
		this.drinkables[1] = monster;
		this.drinkables[2] = hotSix;
		this.drinkables[3] = milkis;

	}

	/**
	 * 음료 주문하기
	 * 
	 * @param menu     구매 금액
	 * @param quantity 음려의 개수
	 * @param count    소비자가 구매한 음려
	 * @return
	 */

	public int orderDrink(int menu, int quantity) {
		if (menu < 0 && menu > drinkables.length) {
			System.out.println("없는 제품입니다.");
			return 0;
		}

		Drinkables drink = this.drinkables[menu];
		System.out.println(drink.getName() + "음료를 " + quantity + "개 구매했습니다.");

		if (drink.getStock() == 0) {
			System.out.println("상품이 품절되었습니다.");
		} else if (drink.getStock() < quantity) {
			quantity = drink.getStock();
		}

		drink.setStock(drink.getStock() - quantity);
		System.out.println(drink.getName() + "음료의 " + drink.getStock() + "개 남았습니다.");

		return drink.getPrice() * quantity;

	}

	/**
	 * 음료수 입고
	 */

	public void inDrinkables(int menu, int quantity) {
		if (menu < 0 || menu >= this.drinkables.length) {
			return;
		}

		// 0 1 2 3
		Drinkables drink = this.drinkables[menu];
		drink.setStock(drink.getStock() - quantity);

	}

	/**
	 * 모든 상품 재고 출력
	 */

	public void printAllDrinkInfo() {
		for (int i = 0; i < this.drinkables.length; i++) {
			System.out.println(this.drinkables[i].getName() + " " + this.drinkables[i].getStock() + "개 남았습니다.");
		}
	}

}
