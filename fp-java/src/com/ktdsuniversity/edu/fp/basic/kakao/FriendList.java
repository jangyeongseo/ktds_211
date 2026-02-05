package com.ktdsuniversity.edu.fp.basic.kakao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class FriendList {
	private List<Friend> friends;

	public FriendList() {
		this.friends = new ArrayList<>();
	}

	public void add(Friend friend) {
		this.friends.add(friend);
	}

	public void printSpecialFriend(Search search) {
		// LocalDate tempBirthdate = null;

		for (Friend f : friends) {
			// 생일을 올해 기준으로 맞춤
			// tempBirthdate = LocalDate.parse(f.getBirthdate().toString());
			// tempBirthdate = tempBirthdate.withYear(LocalDate.now().getYear());
			// 올해의 연도롤 변경

			// 친구들의 정보를 활용하여 출력하기 위한 방법
			if (search.check(f)) {
				System.err.println(f);
			}

		}
	}

	public void printSpecialFriend2(Predicate<Friend> predicate) {
		for (Friend f : friends) {
			if (predicate.test(f)) {
				// 함수형 인터페이스
				// public interface Predicate<T> { boolean test(T t); }
				System.err.println(f);
			}
		}
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();

		if (this.friends.size() == 0) {
			System.out.println("등록된 친구가 없습니다.");
		} else {
			// 프랜드에 있는 toString 사용하기 위해서
			for (Friend f : friends) {
				buffer.append(f);
				buffer.append("\n");
			}
		}

		return buffer.toString();
	}

}
