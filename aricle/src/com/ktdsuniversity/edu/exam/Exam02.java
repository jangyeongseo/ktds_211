package com.ktdsuniversity.edu.exam;

public class Exam02 {
	private String one;

	public Exam02() {
		this.one = "pulljima";
	}

	public void start() {
		System.out.println("첫째 줄에 입력으로 주어진 단어의 길이를 출력한다.");
		String b = this.one.length() + "";
		System.out.println(b);
	}

	public static void main(String[] args) {
		// Q2743
		Exam02 exam02 = new Exam02();
		exam02.start();
	}

}
