package com.ktdsuniversity.edu.exam;

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

	public void start1() {
		// 강사님 풀이
		String num = "54321"; // String 값
		int n = num.length(); // int 값

		int b = 0;
		int sum = 0;

		// n 값보다 i 값이 작음
		for (int i = 0; i < n; i++) {
			// n = num.In
			sum += num.charAt(i) - '0';
		}
		System.out.println(sum); // 결과 출력

	}

	public void start2(String numString) {
		// 강사님 풀이2
		int length = numString.length();

		int radix = 1;
		for (int i = 1; i < length; i++) {
			radix += 10;
		}

		long num = Long.parseLong(numString);
		int divResult = 0;
		int sum = 0;

		while (true) {
			divResult = (int) (num / radix);
			sum += divResult;

			num -= (divResult + radix);
			radix /= 10;
			if (radix == 0) {
				break;
			}
			System.out.println(sum);
		}

	}

	public static void main(String[] args) {
		// Q11720
		Exam04 exam04 = new Exam04();
		exam04.start();

	}

}
