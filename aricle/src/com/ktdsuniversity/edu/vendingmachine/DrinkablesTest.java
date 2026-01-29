package com.ktdsuniversity.edu.vendingmachine;

import com.ktdsuniversity.edu.vendingmachine.item.Drinkables;
import com.ktdsuniversity.edu.vendingmachine.machine.VendingMachine;

/**
 * 음료수 자판기 실행
 */

public class DrinkablesTest {
	public static void main(String[] args) {
		Drinkables bacchus = new Drinkables("박카스", 900, 15);
		Drinkables monster = new Drinkables("몬스터", 1500, 20);
		Drinkables hotSix = new Drinkables("핫식스", 1300, 10);
		Drinkables milkis = new Drinkables("밀키스", 1400, 5);

		VendingMachine lotte = new VendingMachine(bacchus, monster, hotSix, milkis);

		lotte.printAllDrinkInfo();

		int price = lotte.orderDrink(0, 3, 5000);
		System.out.println(price);

		price = lotte.orderDrink(1, 5, 1000);
		System.out.println(price);

		price = lotte.orderDrink(2, 15, 2000);
		System.out.println(price);

		price = lotte.orderDrink(3, 55, 2100);
		System.out.println(price);

		price = lotte.orderDrink(4, 1, 1400);
		System.out.println(price);

		lotte.printAllDrinkInfo();

		lotte.inDrinkables(0, 10);
		lotte.inDrinkables(1, 10);
		lotte.inDrinkables(2, 10);
		lotte.inDrinkables(3, 10);
		lotte.inDrinkables(9, 10);

		lotte.printAllDrinkInfo();

		lotte.inDrinkables(0, 0);
	}

}
