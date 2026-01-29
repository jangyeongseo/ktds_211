package com.ktdsuniversity.edu.vendingmachine.machine;

import com.ktdsuniversity.edu.vendingmachine.item.Drinkables;

/**
 * 자판기
 */

public class VendingMachine {
	// 환불과 입금 받을 금액
	private int withdrawal; // 출금 줘야할 돈
	private int deposit; // 돈 입급
	Drinkables[] drinkables; // 음료수 배열

	public int getWithdrawal() {
		return withdrawal;
	}

	public void setWithdrawal(int withdrawal) {
		this.withdrawal = withdrawal;
	}

	public int getDeposit() {
		return deposit;
	}

	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}

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
	 * @param count    소비자가 구매한 음료
	 * @return
	 */
	public int orderDrink(int menu, int quantity, int inmoney) {
		int deposit = inmoney; // 입금된 돈
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

		// 돈 = 상품의 가격 * 산 개수
		this.deposit += drink.getPrice() * quantity; // 사용자가 넣은 돈
		this.withdrawal -= drink.getPrice() * quantity; // 사용자한테 줄 돈

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
			System.out.println(this.drinkables[i].getName() + " " + this.drinkables[i].getStock() + "개 남았습니다.\n");
		}
	}

}
