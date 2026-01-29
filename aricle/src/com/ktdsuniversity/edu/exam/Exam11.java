package com.ktdsuniversity.edu.exam;

public class Exam11 {
	public int olution(String nstr) {
		return Integer.parseInt(nstr);
	}

	public static void main(String[] args) {
		// Q181848
		System.out.println("입출력 예 #1\r\n" + "\r\n" + "\"10\"을 정수로 바꾸면 10입니다.\r\n" + "입출력 예 #2\r\n" + "\r\n"
				+ "\"8542\"를 정수로 바꾸면 8542입니다.\n");

		Exam11 exam11 = new Exam11();
		System.out.println(exam11.olution("10"));
		System.out.println(exam11.olution("8542"));

	}

}
