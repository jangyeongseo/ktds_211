package com.project.pjt.mart;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Store {
	private List<Item> items;

	/**
	 * @param items
	 */
	public Store() {
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
		// index 유효성 검사
		if (index < 0 || index >= items.size()) {
			System.out.println("존재하지 않는 상품입니다.");
			return;
		}

		Item item = items.get(index); // index 에 해당하는 상품 객체를 가져옴
		LocalDate today = LocalDate.now(); // 오늘 날짜를 LocalDate 타입으로 가져옴 (연/월/일 포함)
		LocalDate expirationDate = item.getExpireDate(); // 상품의 소비기한을 가져옴

		// 오늘 날짜와 소비기한 사이의 일(day) 차이를 계산
		// - 음수: 이미 소비기한이 지남
		// - 0 : 오늘이 소비기한
		// - 양수: 소비기한이 남아 있음
		long days = expirationDate.toEpochDay() - today.toEpochDay();
		System.out.println("상품명: " + item.getName());

		if (days < 0) {
			// 소비기한이 이미 지난 경우
			System.out.println("소비기한이 지나 판매하지 않습니다.\n");

		} else if (days == 0) {
			// 소비기한이 오늘인 경우
			System.out.println("오늘까지 드세요.\n");
		} else if (days <= 3) {
			// 소비기한이 1~3일 남은 경우
			System.out.println("가능한 빨리 드세요.\n");
		} else {
			// 소비기한이 4일 이상 남은 경우
			System.out.println("정상 판매 상품입니다.\n");
		}
	}

}
