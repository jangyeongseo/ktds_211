package exam;

/**
 * 음료수 종료
 */

public class Drinkables {
	int price; // 음료수 가격
	String name; // 음료수 이름
	int stock; // 재고

	public Drinkables(String name, int price, int stock) {
		this.name = name;
		this.price = price;
		this.stock = stock;
	}

}
