package oop;

/**
 * 식당 손님 관리 클래스 1. 자리 착석 2. 주문 3. 식기 나열 4. 퇴실
 */
public class Restaurant {
	boolean seating; // 착석 여부
	int customer; // 손님 인원
	int order; // 주문 수
	int tableware; // 식기 수

	/**
	 * 손님 착석 및 주문 처리
	 * 
	 * @param newCustomers 새로 들어온 손님 수
	 */
	public void customerEnter(int newCustomers) {
		seating = true;
		customer += newCustomers;
		tableware += newCustomers; // 손님 수만큼 식기 추가
		order += newCustomers; // 기본적으로 손님 수만큼 주문한다고 가정

		System.out.println("현재 인원 : " + customer + "명" + "\n" + "");
		System.out.println("들어간 주문의 갯수 : " + order);
		System.out.println("테이블의 식기 수 : " + tableware + "\n" + "");
	}

	/**
	 * 손님 퇴실 처리
	 * 
	 * @param exit 나간 손님의 수
	 */
	public void customerExit(int exit) {
		if (seating) {
			customer -= exit;
			tableware -= exit;
			order -= exit;

			if (customer < 0) {
				customer = 0;
			}
			if (tableware < 0) {
				tableware = 0;
			}
			if (order < 0) {
				order = 0;
			}

			System.out.println("현재 인원 : " + customer + "\n" + "");

			if (customer == 0) {
				seating = false; // 모든 손님이 나가면 착석 상태 해제
			}
		}
	}
}