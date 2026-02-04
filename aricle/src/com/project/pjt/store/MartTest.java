package com.project.pjt.store;
/**
 * 매점?
 */
public class MartTest {
	public static void main(String[] args) {
		Guest guest = new Guest(200_000, 0);
		Guest guest2 = new Guest(7000, 300);
		Guest guest3 = new Guest(0, 12000);

		// sub클래스를 sup클래스에
		AbstractMart mart = new DepartmentStore(1500);
		mart.sell(guest, 5, 10000);
		mart.sell(guest2, 5, 7000);
		mart.sell(guest3, 5, 0);

	}

}
