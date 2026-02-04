package com.ktdsuniversity.edu.datetime.kakao;

public class kakaoTalk {
	public static void main(String[] args) {
		FriendList fl = new FriendList();

		fl.add(new Friend("A", "2001-01-10"));
		fl.add(new Friend("B", "2004-11-08"));
		fl.add(new Friend("C", "2001-08-10"));
		fl.add(new Friend("D", "2005-05-05"));
		fl.add(new Friend("E", "2001-12-12"));
		fl.add(new Friend("F", "2001-02-14"));
		fl.add(new Friend("G", "2008-02-01"));
		fl.add(new Friend("H", "2001-02-09"));

		fl.printSpecialFriend(Base.FUTURE); // 생일이 다가오는 친구
		fl.printSpecialFriend(Base.NOW); // 오늘이 생일인 친구
		fl.printSpecialFriend(Base.PAST); // 생일이 지난 친구

		System.out.println(fl);
	}
}
