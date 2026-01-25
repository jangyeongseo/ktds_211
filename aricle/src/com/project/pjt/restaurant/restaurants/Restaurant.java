package com.project.pjt.restaurant.restaurants;

import com.project.pjt.restaurant.guests.Guests;
import com.project.pjt.restaurant.menu.Menu;

/**
 * 식당
 */

public class Restaurant {
	String name;
	double drunkLimit;
	int fullLimit;
	int sales = 0;

	Menu[] menus = new Menu[10];  // 크기 10으로 고정

	public Restaurant(String name, double drunkLimit, int fullLimit) {
		this.name = name;
		this.drunkLimit = drunkLimit;
		this.fullLimit = fullLimit;
	}
	
	int menuCount = 0;

	public void addMenu(Menu menu) {
	    if (menuCount < menus.length) {
	        menus[menuCount++] = menu;
	    } else {
	        System.out.println("메뉴 배열이 가득 찼습니다!");
	    }
	}


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

		if (m.getType().equals("주류")) {
			System.out.println(name + "식당의 취함 기준: " + drunkLimit);
			if (g.getDrunken() >= drunkLimit) {
				System.out.println("주문 실패 - 너무 취함\n");
				return;
			}
			g.drink(m.getAlcohol());
		}

		if (m.getType().equals("식사류")) {
			System.out.println(name + "식당의 배부름 기준: " + fullLimit);
			if (g.getFullness() >= fullLimit) {
				System.out.println("주문 실패 - 너무 배부름\n");
				return;
			}
			g.eat(m.getWeight());
		}

		g.setMoney(g.getMoney() - m.getPrice());
		m.setStock(m.getStock() - 1);
		sales += m.getPrice();

		System.out.println("주문 성공\n");
	}

	public void showStatus() {
		System.out.println(name + "상태 확인");
		System.out.println("\nMenu ========");
		int i = 1;
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
