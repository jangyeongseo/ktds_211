package com.ktdsuniversity.edu.fp.basic.kakao;

import java.time.LocalDate;
import java.time.Period;

public class kakaoTalk {
	public static void main(String[] args) {
		FriendList fl = new FriendList();

		fl.add(new Friend("A", "2001-01-10"));
		fl.add(new Friend("B", "2004-11-08"));
		fl.add(new Friend("C", "2001-08-10"));
		fl.add(new Friend("이상한", "2005-05-05"));
		fl.add(new Friend("E", "2001-12-12"));
		fl.add(new Friend("F", "2001-02-14"));
		fl.add(new Friend("김돌쇠", "2008-02-01"));
		fl.add(new Friend("H", "2001-02-09"));

		System.out.println("7일 이내에 생일을 맞이하는 친구 목록");
		// fl.printSpecialFriend( birthdate -> birthdate.isAfter(LocalDate.now()) &&
		// birthdate.isBefore(LocalDate.now().plusDays(8)));
		fl.printSpecialFriend2(friend -> {
			LocalDate birthdate = friend.getBirthdate().withYear(LocalDate.now().getYear());

			return birthdate.isAfter(LocalDate.now()) && birthdate.isBefore(LocalDate.now().plusDays(8));
		});

		System.out.println("\n오늘이 생일인 친구 목록");
		// fl.printSpecialFriend(birthdate -> birthdate.isEqual(LocalDate.now()));
		fl.printSpecialFriend2(friend -> {
			LocalDate birthdate = friend.getBirthdate().withYear(LocalDate.now().getYear());

			return birthdate.isEqual(LocalDate.now());
		});

		System.out.println("\n7일 이내에 생일이 지난 친구 목록");
		// fl.printSpecialFriend( birthdate -> birthdate.isBefore(LocalDate.now()) &&
		// birthdate.isAfter(LocalDate.now().minusDays(8)));
		fl.printSpecialFriend2(friend -> {
			LocalDate birthdate = friend.getBirthdate().withYear(LocalDate.now().getYear());

			return birthdate.isBefore(LocalDate.now()) && birthdate.isAfter(LocalDate.now().minusDays(8));
		});

		System.out.println("\n오늘 만 30세가 된 친구 목록");
		fl.printSpecialFriend(friend -> {
			Period period = Period.between(friend.getBirthdate(), LocalDate.now());

			return period.getYears() == 30 && period.getMonths() == 0 && period.getDays() == 0;
		});

		System.out.println("\n이름이 'A'인 친구 목록");
		fl.printSpecialFriend(friend -> friend.getName().equals("A"));

		System.out.println("\n이름이 '김'으로 시작하는 친구 목록");
		fl.printSpecialFriend(friend -> friend.getName().startsWith("김"));

		System.out.println("\n이름이 '이'로 시작하면서 나이가 20세 이상인 친구 목록");
		fl.printSpecialFriend(friend -> {
			Period period = Period.between(friend.getBirthdate(), LocalDate.now());
			return period.getYears() >= 20 && period.getMonths() == 0 && period.getDays() == 0
					&& friend.getName().startsWith("이");
		});

		System.out.println("\n전체 친구 목록");
		fl.printSpecialFriend(friend -> true);
	}
}
