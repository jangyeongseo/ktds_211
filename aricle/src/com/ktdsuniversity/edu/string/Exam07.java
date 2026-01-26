package com.ktdsuniversity.edu.string;

public class Exam07 {
	public static void main(String[] args) {
		// Q2908 - 다시 공부해보기
		System.out.println("첫째 줄에 상수의 대답을 출력한다.\n");

		String a = "734 893";
		String[] ab = a.split(" ");

		// 문자열 뒤집기
		String aRev = new StringBuilder(ab[0]).reverse().toString();
		String bRev = new StringBuilder(ab[1]).reverse().toString();

		int numA = Integer.parseInt(aRev);
		int numB = Integer.parseInt(bRev);

		// 더 큰 값 출력
		System.out.println(Math.max(numA, numB));

	}

}
