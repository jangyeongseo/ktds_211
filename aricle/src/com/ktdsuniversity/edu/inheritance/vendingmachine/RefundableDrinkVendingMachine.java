package com.ktdsuniversity.edu.inheritance.vendingmachine;

import com.ktdsuniversity.edu.vendingmachine.item.Drinkables;
import com.ktdsuniversity.edu.vendingmachine.machine.VendingMachine;

public class RefundableDrinkVendingMachine extends VendingMachine {
	public RefundableDrinkVendingMachine(Drinkables bacchus, Drinkables monster, Drinkables hotSix, Drinkables milkis) {
		super(bacchus, monster, hotSix, milkis);
	}

	/**
	 * 사용자가 돈을 넣은 후 존재하지 않는 음료를 골랐을 때, 사용자에게 돈을 돌려준다. DrinkVendingMachine의 멤버변수와
	 * order 기능의 수정이 필요
	 */
	public void refund() {
		int result = super.getWithdrawal();
		if (result > 0) {
			System.out.println(result + "이 환불되었습니다.");
		}

	}

}
