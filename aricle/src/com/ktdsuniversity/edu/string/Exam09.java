package com.ktdsuniversity.edu.string;

public class Exam09 {
	public int solution(String my_string, String target) {
		if (target.contains(my_string)) {
			return 1;
		} else {
			return 0;
		}
	}

	public static void main(String[] args) {
		// Q181843
		System.out.println("입출력 예 #1\r\n" + "\r\n" + "문제 설명과 같습니다.\r\n" + "입출력 예 #2\r\n" + "\r\n" + "문제 설명과 같습니다.\n");

		Exam09 exam09 = new Exam09();
		System.out.println(exam09.solution("abc", "aabcc")); // 1
		System.out.println(exam09.solution("tbt", "tbbttb")); // 0

	}

}
