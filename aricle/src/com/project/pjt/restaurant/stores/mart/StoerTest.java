package com.project.pjt.restaurant.stores.mart;

public class StoerTest {
	public static void main(String[] args) {
		Stoer stoer = new Stoer();

		stoer.add(new Item("우유", "2026-02-11"));
		stoer.add(new Item("우유", "2026-02-11"));
		stoer.add(new Item("우유", "2026-02-11"));
		stoer.add(new Item("우유", "2026-02-11"));
		stoer.add(new Item("우유", "2026-02-11"));

		System.out.println(stoer);

	}
}
