package com.project.pjt.restaurant.stores.generics;

/**
 * 비행기
 */
public class Airplane {
	private String name; // 비행기 편
	private int seats; // 비행기 좌석

	public Airplane(String name, int seats) {
		super();
		this.name = name;
		this.seats = seats;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSeats() {
		return this.seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

}
