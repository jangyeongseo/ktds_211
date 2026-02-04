package com.project.pjt.restaurant.stores.mart;

import java.time.LocalDate;

public class Item {
	private String name;
	private LocalDate expireDate;

	// 생성자
	public Item(String name, String expireDate) {
		super();
		this.name = name;
		this.expireDate = LocalDate.parse(expireDate);
	}

	// Getter / Setter
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getExpireDate() {
		return this.expireDate;
	}

	public void setExpireDate(LocalDate expireDate) {
		this.expireDate = expireDate;
	}

	// toString
	@Override
	public String toString() {
		return "제품 이름: " + this.name + ", 소비기한: " + this.expireDate;
	}

}
