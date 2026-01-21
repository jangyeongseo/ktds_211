package oop;

public class RestaurantTest {
	public static void main(String[] args) {
		Restaurant rs = new Restaurant();

		rs.customerEnter(5);
		rs.customerExit(4);

		boolean customer = rs.seating; // 착석 여부
		System.out.println(customer + "\n" + "");

		int customerNum = rs.customer;
		System.out.println(customerNum + "\n" + "");

	}
}
