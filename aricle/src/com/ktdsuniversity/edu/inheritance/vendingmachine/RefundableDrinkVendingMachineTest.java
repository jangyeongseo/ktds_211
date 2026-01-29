package com.ktdsuniversity.edu.inheritance.vendingmachine;

import com.ktdsuniversity.edu.vendingmachine.item.Drinkables;
import com.ktdsuniversity.edu.vendingmachine.machine.VendingMachine;

public class RefundableDrinkVendingMachineTest {
	public static void main(String[] args) {
		Drinkables bacchus = new Drinkables("박카스", 900, 15);
		Drinkables monster = new Drinkables("몬스터", 1500, 20);
		Drinkables hotSix = new Drinkables("핫식스", 1300, 10);
		Drinkables milkis = new Drinkables("밀키스", 1400, 5);
		VendingMachine machine = new RefundableDrinkVendingMachine(bacchus, monster, hotSix, milkis);

		int orderMoney = machine.orderDrink(1, 3, 10000);
		System.out.println("주문금액: " + orderMoney);

		if (machine instanceof RefundableDrinkVendingMachine rfMachine) {
			rfMachine.refund();
		}

		orderMoney = machine.orderDrink(6, 3, 10000);
		System.out.println("주문금액: " + orderMoney);

		if (machine instanceof RefundableDrinkVendingMachine rfMachine) {
			rfMachine.refund();
		}

	}
}
