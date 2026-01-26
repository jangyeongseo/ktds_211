package com.ktdsuniversity.edu.string;

public class Exam01 {
	private String name;

	public Exam01() {
		this.name = "Sprout";
	}

	public void start() {
		System.out.println("S의 i번째 글자를 출력한다.");
		String a = this.name.substring(2, 3); // 2번째 위치부터 3번 위치에 있는 글 출력
		System.out.println(a);
	}

	public static void main(String[] args) {
		// Q 27866
		Exam01 exam1 = new Exam01();
		exam1.start();

	}
}
