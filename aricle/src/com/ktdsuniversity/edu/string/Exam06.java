package com.ktdsuniversity.edu.string;

public class Exam06 {

	public static void main(String[] args) {
		// Q1152
		System.out.println("첫째 줄에 단어의 개수를 출력한다.");

		String t = "The Curious Case of Benjamin Button";
		String[] b = t.split(" "); // 공백 기준으로

		int c = b.length; // 단어의 개수
		System.out.println(c);

	}

}
