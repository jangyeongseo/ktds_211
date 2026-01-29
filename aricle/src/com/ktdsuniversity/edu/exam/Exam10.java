package com.ktdsuniversity.edu.exam;

public class Exam10 {
	public String solution(int n) {
		return Integer.toString(n);
	}

	public static void main(String[] args) {
		// Q181845
		System.out.println("입출력 예 #1\r\n" + "\r\n" + "123을 문자열로 변환한 \"123\"을 return합니다.\r\n" + "입출력 예 #2\r\n" + "\r\n"
				+ "2573을 문자열로 변환한 \"2573\"을 return합니다.\n");

		Exam10 exam10 = new Exam10();
		System.out.println(exam10.solution(123)); // "123"
		System.out.println(exam10.solution(2573)); // "2573"

//		int number = 123;
//		String trans = String.valueOf(number);
//		or
//		String trans = number + " ";
//		
//		System.out.println(trans);

	}
}
