package com.ktdsuniversity.edu.string;

public class Exam08 {
	public int solution(String str1, String str2) {
		if (str2.contains(str1)) {
			return 1;
		} else {
			return 0;
		}
	}

	public static void main(String[] args) {
		// Q181842
		System.out.println("입출력 예 #1\r\n" + "\r\n" + "본문과 동일합니다.\r\n" + "입출력 예 #2\r\n" + "\r\n"
				+ "\"tbbttb\"에는 \"tbt\"가 없으므로 0을 return합니다.\n");

		Exam08 s = new Exam08();
		System.out.println(s.solution("abc", "aabcc")); // 1
		System.out.println(s.solution("tbt", "tbbttb")); // 0

	}
}
