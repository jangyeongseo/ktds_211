package com.project.pjt.restaurant.restaurants;

/**
 * 식당
 */

public class Restaurant {
	private int maxFull; // 식당의 최대 g
	private int nowFull; // 배부름의 정도g

	private int maxAlcohol; // 식당의 최대 알골 수치
	private int nowAlcohol; // 지금 알콜 수치

	public Restaurant(int maxFull, int nowFull, int maxAlcohol, int nowAlcohol) {
		super();
		this.maxFull = maxFull;
		this.nowFull = nowFull;
		this.maxAlcohol = maxAlcohol;
		this.nowAlcohol = nowAlcohol;
	}

	// get / set
	public int getMaxFull() {
		return maxFull;
	}

	public void setMaxFull(int maxFull) {
		this.maxFull = maxFull;
	}

	public int getNowFull() {
		return nowFull;
	}

	public void setNowFull(int nowFull) {
		this.nowFull = nowFull;
	}

	public int getMaxAlcohol() {
		return maxAlcohol;
	}

	public void setMaxAlcohol(int maxAlcohol) {
		this.maxAlcohol = maxAlcohol;
	}

	public int getNowAlcohol() {
		return nowAlcohol;
	}

	public void setNowAlcohol(int nowAlcohol) {
		this.nowAlcohol = nowAlcohol;
	}

	//음식 주문시
	
	
}
