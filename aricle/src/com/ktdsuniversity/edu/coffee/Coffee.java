package com.ktdsuniversity.edu.coffee;

/**
 * 커피 메누를 나타내는 데이터 클래스.
 * 
 * getter : Coffee 클래스 외보로 멤버 변수의 값을 노출시키는 코드
 * setter : 외부에서 COffee 클래스 내부로 멤버 변수의 값을 변경시키는 코드
 */

public class Coffee {
	private int price; // 커피 메뉴의 가격
	private String name; // 커피 메뉴의 이름
	private int stock; // 하루동안 판매할 수 있는 커피의 양

	public Coffee(String name, int price, int stock) {
		this.name = name;
		this.price = price;
		this.stock = stock;
	}

	// get / set
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

}
