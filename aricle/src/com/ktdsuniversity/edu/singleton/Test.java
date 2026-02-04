package com.ktdsuniversity.edu.singleton;

public class Test {
	public static void main(String[] args) {
		Me me = Me.getInstance();
		System.out.println(me);

		Me you = Me.getInstance();
		System.out.println(you);

		System.out.println(me == you);
		// 진짜 인스턴스는 한개 밖에 없는 것이고 이걸 싱글턴 인스턴스를 활용한 싱글턴 패턴으로 만들어진 클래스

		System.out.println(me.getName());
		System.out.println(you.getName());

		you.setName("아무개");

		System.out.println(me.getName());
		System.out.println(you.getName());

	}
}
