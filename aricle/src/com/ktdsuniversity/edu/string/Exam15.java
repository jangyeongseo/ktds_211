package com.ktdsuniversity.edu.string;

public class Exam15 {
	public int solution(String myString, String pat) {
		// 모두 소문자로 변환
		String lowerMyString = myString.toLowerCase();
		String lowerPat = pat.toLowerCase();

		if (lowerMyString.contains(lowerPat)) {
			return 1;
		} else {
			return 0;
		}
	}

	public static void main(String[] args) {
		// Q181878
		System.out.println("입출력 예 #1\r\n" + "\r\n"
				+ "\"AbCdEfG\"의 0~2번 인덱스의 문자열은 \"AbC\"이며, 이는 pat인 \"aBc\"와 같습니다. 따라서 1을 return 합니다.\r\n"
				+ "입출력 예 #2\r\n" + "\r\n"
				+ "myString의 길이가 pat보다 더 짧기 때문에 myString의 부분 문자열 중 pat와 같은 문자열이 있을 수 없습니다. 따라서 0을 return 합니다.\n");

		Exam15 exam15 = new Exam15();
		System.out.println(exam15.solution("AbCdEfG", "aBc")); // 1
		System.out.println(exam15.solution("aaAA", "aaaaa")); // 0

	}

}
