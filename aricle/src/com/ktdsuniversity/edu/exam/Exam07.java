package com.ktdsuniversity.edu.exam;

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

		/*
		 * Scanner scanner = new Scanner(System.in); String str = scanner.nextLine();
		 * 
		 * String[] splitStr = str.split(" ");
		 * 
		 * String num1 = splitStr[0]; String num2 = splitStr[1];
		 * 
		 * String result1 = ""; String result2 = "";
		 * 
		 * for(int i = 0 ; i < 3 ; i++) { result1 += num1.charAt(2-i); result2 +=
		 * num2.charAt(2-i); }
		 * 
		 * int num3 = Integer.parseInt(result1); int num4 = Integer.parseInt(result2);
		 * if(num3 > num4) { System.out.println(num3); }else { System.out.println(num4);
		 * }
		 */

	}

}
