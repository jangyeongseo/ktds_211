package com.project.pjt.restaurant;

import com.project.pjt.restaurant.guests.Guests;
import com.project.pjt.restaurant.menu.Menu;
import com.project.pjt.restaurant.restaurants.Restaurant;

public class RestaurantTest {
	public static void main(String[] args) {

		Restaurant vips = new Restaurant("VIPS", 10.0, 1000);
		Restaurant outback = new Restaurant("OUTBACK", 5.0, 300);

		Menu soju1 = new Menu("주류", 5000, 19.0, 0, 100);
		Menu soju2 = new Menu("주류", 6000, 6.0, 0, 80);
		Menu soju3 = new Menu("주류", 5000, 40.0, 0, 15);
		Menu food1 = new Menu("식사류", 10000, 0, 500, 200);
		Menu food2 = new Menu("식사류", 8000, 0, 300, 70);

		vips.addMenu(soju1);
		vips.addMenu(soju2);
		vips.addMenu(soju3);
		vips.addMenu(food1);
		vips.addMenu(food2);

		outback.addMenu(new Menu("주류", 1000, 19.0, 0, 60));
		outback.addMenu(new Menu("주류", 2000, 6.0, 0, 80));
		outback.addMenu(new Menu("주류", 5000, 40.0, 0, 15));
		outback.addMenu(food1);
		outback.addMenu(food2);

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

		vips.showStatus();
		outback.showStatus();

	}
}
