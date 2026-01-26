package com.ktdsuniversity.edu.string;

public class Exam03 {
	private String ascii;

	public Exam03() {
		this.ascii = "A";
	}

	public void start() {
		System.out.println("입력으로 주어진 글자의 아스키 코드 값을 출력한다.");

		char a = this.ascii.charAt(0);
		int asciiCode = (int) a; // char 를 int 로 변환
		System.out.println(asciiCode);

	}

	public static void main(String[] args) {
		// Q11654
		Exam03 exam03 = new Exam03();
		exam03.start();

	}

}
