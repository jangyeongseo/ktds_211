package com.project.pjt.restaurant.stores.mart;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Stoer {
	private List<Item> items;

	/**
	 * @param items
	 */
	public Stoer() {
		this.items = new ArrayList<>();
	}

	public void add(Item item) {
		// 추가
		this.items.add(item);
	}

	/*
	 * index 에 할당되어 있는 제품의 소비기한이 당일이라면 "오늘까지 드세요" 를 출력
	 * 
	 * 소비기한이 3일 이내에 도래한다면 "가능한 빨리 드세요"를 출력
	 * 
	 * 소비기한이 지났다면 "소비기한이 지나 판매하지 않습니다"를 출력
	 */
	public void sell(int index) {
		LocalDate expirationDate = null;

		for (Item i : items) {
			expirationDate = LocalDate.parse(i.getExpireDate().toString());
			expirationDate.withYear(LocalDate.now().getYear()); // 오늘 날짜로 변경

		}
	}

}
