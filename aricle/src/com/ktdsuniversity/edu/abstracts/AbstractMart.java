package com.ktdsuniversity.edu.abstracts;

/**
 * 일반 마트 public class name : 인스턴스로 생성할 수 있는 일반 클래스 정의 public abstract class name
 * : 인스턴스를 생성할 수 없는 추상 클래스 정의
 */

public abstract class AbstractMart {
	private int safe; // 금고
	private int productPrice; // 상품 가격
	private int remainMoney;// 거스름 돈

	// 상품의 가격
	public AbstractMart(int productPrice) {
		this.productPrice = productPrice;
	}

	public void sell(Guest guest, int sellCount, int money) {
		this.remainMoney = money;

		int guestPoint = this.usePoint(guest); // 돈이랑 합치거나 상품가격에서 빼는 방법

		// 판매가격
		int amount = sellCount * this.productPrice;

		amount -= this.discount(guest, amount); // 할인된 가격 : 할인 비율만큼 빼준다

		// 돈 + 고객의 포인트 보다 가격이 더 크다
		if (money + guestPoint < amount) {
			System.out.println("돈이 모자랍니다. 구매가격 : " + amount + ", 손님이 낸 돈 : " + money + "\n");
		}

		// 손님이 마트에 지불한 돈
		guest.pay(money);

		this.givePoint(guest, amount); // 구매를 하면은 저 금액에 몇 %를 지급
		if(amount > guestPoint) {
			this.remainMoney -= amount - guestPoint;
		}
		
		this.remainMoney -= (amount - guestPoint);
		this.safe += money - this.remainMoney;
		System.out.println("매출액 : " + this.safe); // 마트 자본
		System.out.println("거슬러 줄 돈 : " + this.remainMoney + "\n");

		// 마트가 준 거스름 돈
		guest.giveMoney(this.remainMoney);
		this.remainMoney = 0;

	}

	// public int usePoint(Guest guest); -> 어떻게 동작을 할지 몰라서 놔두는 사상?
	// public abstract int usePoint(Guest guest); -> 이렇게 만들경우 에러가 생기지 않는다.
	public abstract int usePoint(Guest guest);

	// 구매가격을 알고 고객한테 포인트를 지급해라
	public abstract void givePoint(Guest guest, int amount);

	public abstract int discount(Guest guest, int amount);

}
