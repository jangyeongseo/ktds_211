package com.ktdsuniversity.edu.restaurant;

import java.util.ArrayList;
import java.util.List;

/**
 * 배열 - List로 변경
 */
public class RestaurantTest {
	public static void main(String[] args) {

		// 이럴때만 사용한다
		String phone = "010-2655-4564";
		String[] phoneSplitd = phone.split("-");

		List<Menu> vipsMenus = new ArrayList<>();
		vipsMenus.add(new Menu(true, 0, 19, 5000, 100)); // 주류
		vipsMenus.add(new Menu(true, 0, 6, 6000, 80)); // 주류
		vipsMenus.add(new Menu(true, 0, 40, 5000, 15)); // 주류
		vipsMenus.add(new Menu(false, 500, 0, 10000, 200)); // 식사류
		vipsMenus.add(new Menu(false, 300, 0, 8000, 70)); // 식사류

		List<Menu> outbackMenus = new ArrayList<>();
		outbackMenus.add(new Menu(true, 0, 19, 1000, 60)); // 주류
		outbackMenus.add(new Menu(true, 0, 6, 2000, 80)); // 주류
		outbackMenus.add(new Menu(true, 0, 40, 5000, 15)); // 주류
		outbackMenus.add(new Menu(false, 500, 0, 10000, 200)); // 식사류
		outbackMenus.add(new Menu(false, 300, 0, 8000, 70)); // 식사류

		Restuarant vips = new Restuarant("VIPS", vipsMenus, 1000, 10);
		Restuarant outback = new Restuarant("OUTBACK", outbackMenus, 300, 5);

		Customer customer1 = new Customer("고객1", 50000);
		Customer customer2 = new Customer("고객2", 30000);
		Customer customer3 = new Customer("고객3", 10000);

		customer1.order(vips, 0);
		customer1.order(vips, 0);
		customer1.order(outback, 2);
		customer1.order(vips, 2);
		customer1.order(vips, 1);
		customer1.order(vips, 4);
		customer1.order(outback, 3);
		customer1.order(vips, 3);

		customer2.order(outback, 4);
		customer2.order(vips, 1);
		customer2.order(vips, 2);
		customer2.order(outback, 3);
		customer2.order(vips, 2);

		customer3.order(vips, 2);
		customer3.order(outback, 3);
		customer3.order(vips, 4);
		customer3.order(vips, 4);
		customer3.order(vips, 3);

		vips.printStatus();
		outback.printStatus();
	}
}
