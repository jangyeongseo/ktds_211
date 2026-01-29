package com.ktdsuniversity.edu.exam;

public class Exam14 {
	public String solution(String myString) {
		return myString.toLowerCase();
	}

	public static void main(String[] args) {
		// Q181876
		System.out.println(
				"알파벳으로 이루어진 문자열 myString이 주어집니다. 모든 알파벳을 소문자로 변환하여 return 하는 solution 함수를 완성해 주세요.\r\n" + "\r\n" + "");

		Exam14 exam14 = new Exam14();
		System.out.println(exam14.solution("aBcDeFg")); // "abcdefg"
		System.out.println(exam14.solution("aaa")); // "aaa"

	}

}
