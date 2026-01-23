package com.project.pjt.restaurant.guests;

/**
 * 손님
 */
import com.project.pjt.restaurant.menu.Menu;

public class Guests {
	Menu[] restaurant; // 메뉴 개수 제한없음

	// 메뉴 배열 맞추기
	// 메뉴마다 배부름과 취함 정도가 다름
	/**
	 * 배열에 메뉴넣기
	 * 
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 */
	public Guests(Menu a, Menu b, Menu c, Menu d) {
		this.restaurant = new Menu[4];
		this.restaurant[0] = a;
		this.restaurant[1] = b;
		this.restaurant[2] = c;
		this.restaurant[3] = d;
	}

	// 손님이 음식을 주문하면 음식마다의 무게만틈 배부름 증가
	// 손님이 술을 주문하면 술 마다의 알콜 비율 10% 만큼 취함 증가
//	public int orderingFood() {
//		
//	}
	

	// 식당에서 음식을 시키기전 배부른 손심과 과하게 취한 손님 주문 못받음

}
