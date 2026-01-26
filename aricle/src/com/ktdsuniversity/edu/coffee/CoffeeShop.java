package com.ktdsuniversity.edu.coffee;

/**
 * 커피 판매하는 커피숍
 */

public class CoffeeShop {

	/**
	 * 커피숍에서 판매하는 따듯한 아메리카노
	 */
	private Coffee hot;

	/**
	 * 커피숍에서 판매하는 아이스 아메리카노
	 */
	private Coffee ice;

	/**
	 * 파라미터가 없는 생성자
	 */
	public CoffeeShop() {
//		this.hot = new Coffee("기본 아메리카노", 1800, 30);
//		this.ice = new Coffee("아이스 아메리카노", 2000, 30);

		// this 를 사용한 인스턴스
		this(new Coffee("기본 아메리카노", 1800, 30), new Coffee("아이스 아메리카노", 2000, 15));

	}

	public CoffeeShop(Coffee hot, Coffee ice) {
		this.hot = hot;
		this.ice = ice;
	}

	/**
	 * 메소드 오버로딩
	 * 
	 * @return
	 */
	public int orderCoffee() {
		int price = this.orderCoffee(1);
		return price;
	}

	/**
	 * 한개만 주문함
	 * 
	 * @param menu
	 * @return
	 */
	public int orderCoffee(int menu) {
		int price = this.orderCoffee(menu, 1);
		return price;
	}

	/**
	 * 커피숍에서 커피를 판매한다.
	 * 
	 * @param menu     메뉴들의 번호 1. hot, 2. ice
	 * @param quantity 주문 수량
	 * @return 주문 가격
	 */
	public int orderCoffee(int menu, int quantity) {
		if (menu == 1) {
			if (this.hot.getStock() >= quantity) {
				System.out.println(this.hot.getName() + " 음료를 " + quantity + "개 주문 받았습니다." + "\n" + " ");
				this.hot.setStock(this.hot.getStock() - quantity);
				System.out.println(this.hot.getName() + " 음료는 " + this.hot.getStock() + "개 남았습니다.");

				return this.hot.getPrice() * quantity;
				// 맴버 변수의 핫
			} else {
				System.out.println("품절되었습니다.");
				// 판매 수량을 사람이 산 수량에서 "-" 하기
				return 0;
			}

		} else if (menu == 2) {
			if (this.ice.getStock() >= quantity) {
				System.out.println(this.ice.getName() + " 음료를 " + quantity + "개 주문 받았습니다." + "\n" + " ");
				this.ice.setStock(this.ice.getStock() - quantity);
				System.out.println(this.ice.getName() + " 음료는 " + this.ice.getStock() + "개 남았습니다.");

				return this.ice.getPrice() * quantity;
				// 맴버 변수의 아이스
			} else {
				System.out.println("품절되었습니다.");
				return 0;
			}

		} else {
			System.out.println("존재하지 않는 메뉴입니다.");
			return 0;

		}
	}

}
