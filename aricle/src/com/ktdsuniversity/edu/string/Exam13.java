package com.ktdsuniversity.edu.string;

public class Exam13 {
	public int solution(String num_str) {
		int sum = 0;
		for (int i = 0; i < num_str.length(); i++) {
			sum += num_str.charAt(i) - '0'; // 문자 → 숫자 변환
		}
		return sum;
	}

	public static void main(String[] args) {
		// Q181849
		System.out.println("입출력 예 #1\r\n" + "\r\n" + "문자열 안의 모든 숫자를 더하면 45가 됩니다.\r\n" + "입출력 예 #2\r\n" + "\r\n"
				+ "문자열 안의 모든 숫자를 더하면 1이 됩니다.");

		Exam13 exam13 = new Exam13();
		System.out.println(exam13.solution("123456789"));
		System.out.println(exam13.solution("1000000"));

	}

}
