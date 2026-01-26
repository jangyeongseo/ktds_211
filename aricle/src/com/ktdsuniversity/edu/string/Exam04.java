package com.ktdsuniversity.edu.string;

public class Exam04 {
	public void start() {
		int n = 5; // int 값
		String num = "54321"; // String 값

		System.out.println("입력으로 주어진 숫자 N개의 합을 출력한다.");

		int sum = 0;

		// n 값보다 i 값이 작음
		for (int i = 0; i < n; i++) {
			// String 값을 활용해 출력
			sum += num.charAt(i) - '0';
		}
		System.out.println(sum); // 결과 출력
	}

	public static void main(String[] args) {
		// Q11720
		Exam04 exam04 = new Exam04();
		exam04.start();

	}

}
