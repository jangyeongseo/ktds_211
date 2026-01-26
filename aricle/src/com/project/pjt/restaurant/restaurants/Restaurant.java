package com.project.pjt.restaurant.restaurants;

import com.project.pjt.restaurant.guests.Guests;
import com.project.pjt.restaurant.menu.Menu;

/**
 * 식당
 */
public class Restaurant {
	String name; // 이름
	double drunkLimit; // 술 취함도
	int fullLimit; // 배부름의 정도
	int sales = 0; // 매출

	Menu[] menus = new Menu[10]; // 크기 10으로 고정

	public Restaurant(String name, double drunkLimit, int fullLimit) {
		this.name = name;  
		this.drunkLimit = drunkLimit;
		this.fullLimit = fullLimit;
	}

	int menuCount = 0; // 메뉴 개수

	// 현재 menuCount 위치에 메뉴 저장 / 저장 후 menuCount를 1 증가
	public void addMenu(Menu menu) {
		// 메뉴 시킨거
		if (menuCount < menus.length) {
			menus[menuCount++] = menu;
		} else {
			System.out.println("메뉴 가득 찼습니다!");
		}
	}

	// "%.1f" : 소수점 1까지만 나오게 하도록 가능 / 현재 점수는 %d %s 입니다.
	// "%.1f", customer.getDrunkenness() -> 이렇게 하는 방법이 있다.
	// %d 도 가능
	public void order(Guests g, Menu m) {
		System.out.println("고객명 : " + g.getName());
		System.out.println(g.getName() + "의 취함 정도 : " + g.getDrunken());
		System.out.println(g.getName() + "의 배부름 정도 : " + g.getFullness());
		System.out.println(g.getName() + "의 소지금: " + g.getMoney());
		System.out.println("주문 금액: " + m.getPrice());

		// 재고 확인 과정
		if (m.getStock() <= 0) {
			System.out.println("주문 실패 - 재고 없음");
		}

		if (g.getMoney() < m.getPrice()) {
			System.out.println(g.getName() + "의 소지금 부족\n");
			return;
		}

		// 타입 분류
		// String 으로 하지 않고 분류해서 놔누는것도 괜춘
		if (m.getType().equals("주류")) {
			System.out.println(name + "식당의 취함 기준: " + drunkLimit);

			// 손님의 취함정도가 식당의 취함보다 같거나 높을 경우
			if (g.getDrunken() >= drunkLimit) {
				System.out.println("주문 실패 - 너무 취함\n");
				return;
			}
			// 손님한테 주류 판매
			g.drink(m.getAlcohol());
		}

		if (m.getType().equals("식사류")) {
			System.out.println(name + "식당의 배부름 기준: " + fullLimit);

			// 손님의 배부름이 식당의 배부름보다 같거나 높을 경우
			if (g.getFullness() >= fullLimit) {
				System.out.println("주문 실패 - 너무 배부름\n");
				return;
			}
			// 손남한테 음식 판매
			g.eat(m.getWeight());
		}

		// 손님의 돈 - 메뉴 가격
		g.setMoney(g.getMoney() - m.getPrice());
		m.setStock(m.getStock() - 1);
		// 메출에 판가격 더하기
		sales += m.getPrice();

		System.out.println("주문 성공\n");
	}

	// 효율적으로 좋은쪽으로 한다면 보완이 필요할 수 있다.
	public void showStatus() {
		System.out.println(name + "상태 확인");
		System.out.println("\nMenu ========");
		int i = 1;

		// 만약 메뉴의 배열 메뉴의 m 이라고 할때 m 의 타이 주류랑 같다면
		for (Menu m : menus) {
			if (m.getType().equals("주류")) {
				System.out.println(
						i++ + ". 주류 / " + m.getPrice() + "원 / " + m.getAlcohol() + "% / " + m.getStock() + "개");
			} else {
				System.out.println(
						i++ + ". 식사류 / " + m.getPrice() + "원 / " + m.getWeight() + "g / " + m.getStock() + "개");
			}
		}
		System.out.println("매출금: " + sales + "\n");
	}

}
