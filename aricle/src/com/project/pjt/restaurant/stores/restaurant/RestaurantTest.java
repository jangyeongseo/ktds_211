package com.project.pjt.restaurant.stores.restaurant;

import com.project.pjt.restaurant.stores.restaurant.custom.DrunkenException;
import com.project.pjt.restaurant.stores.restaurant.custom.FullException;
import com.project.pjt.restaurant.stores.restaurant.custom.NotEnoughMoneyException;
import com.project.pjt.restaurant.stores.restaurant.custom.OutOfStockException;

public class RestaurantTest {
	public static void main(String[] args)
			throws OutOfStockException, NotEnoughMoneyException, DrunkenException, FullException {
		// JVM이 Java 파일을 실행시킬 수 있도록 클래스 메소드로 정의
		// throws 로 불러오는것을 지양함 -> try-catch를 사용하는것을 더 선호

		// 손님 인정 알콜과 배부름의 정도 Max
		Restaurant vips = new Restaurant("VIPS", 10.0, 1000);

		// 손님 인정 알콜과 배부름의 정도 Min
		Restaurant outback = new Restaurant("OUTBACK", 5.0, 300);

		// 타입, 메뉴 가격, 주류, 삭사, 자본금
		Menu soju1 = new Menu(Item.ALCOHOL, 5000, 19.0, 0, 100);
		Menu soju2 = new Menu(Item.ALCOHOL, 6000, 6.0, 0, 80);
		Menu soju3 = new Menu(Item.ALCOHOL, 5000, 40.0, 0, 15);
		Menu food1 = new Menu(Item.FOOD, 10000, 0, 500, 200);
		Menu food2 = new Menu(Item.FOOD, 8000, 0, 300, 70);

		// 1 ~ 5가지의 식당
		vips.addMenu(soju1);
		vips.addMenu(soju2);
		vips.addMenu(soju3);
		vips.addMenu(food1);
		vips.addMenu(food2);

		// 배열에 넣기
		outback.addMenu(new Menu(Item.ALCOHOL, 1000, 19.0, 0, 60));
		outback.addMenu(new Menu(Item.ALCOHOL, 2000, 6.0, 0, 80));
		outback.addMenu(new Menu(Item.ALCOHOL, 5000, 40.0, 0, 15));
		outback.addMenu(food1);
		outback.addMenu(food2);

		// 고객
		Guests g1 = new Guests("고객1", 50000);
		Guests g2 = new Guests("고객2", 30000);
		Guests g3 = new Guests("고객3", 10000);

		vips.order(g1, soju1);
		vips.order(g1, soju1);
		outback.order(g1, soju1);
		vips.order(g1, soju3);
		vips.order(g1, soju2);
		vips.order(g1, food2);
		outback.order(g1, food2);
		vips.order(g1, food1);

		outback.order(g2, food2);
		vips.order(g2, soju2);
		vips.order(g2, soju1);
		outback.order(g2, food1);
		vips.order(g2, soju1);

		vips.order(g3, soju3);
		vips.order(g3, food1);

		// 상태 확인
		vips.showStatus();
		outback.showStatus();

	}
}
