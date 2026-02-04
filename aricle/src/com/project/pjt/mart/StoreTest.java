package com.project.pjt.mart;

public class StoreTest {
	public static void main(String[] args) {
		Store stoer = new Store();

		stoer.add(new Item("우유", "2026-02-11"));
		stoer.add(new Item("빵", "2026-02-08"));
		stoer.add(new Item("요거트", "2026-02-04"));

		stoer.sell(0);
		stoer.sell(1);
		stoer.sell(2);

	}
}
