package com.project.pjt.restaurant.menu;

/**
 * 메뉴 : 배부름, 술
 */
public class Menu {
	private int name; // 음식 이름
	private int alcohol; // 술
	private int full; // 배부름

	public Menu() {
		super();
	}

	public Menu(int name, int alcohol, int full) {
		super();
		this.name = name;
		this.alcohol = alcohol;
		this.full = full;
	}

	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}

	public int getAlcohol() {
		return alcohol;
	}

	public void setAlcohol(int alcohol) {
		this.alcohol = alcohol;
	}

	public int getFull() {
		return full;
	}

	public void setFull(int full) {
		this.full = full;
	}
	
	
	

}
