package com.ktdsuniversity.edu.exam;

public class Exam16 {
	public String[] solution(String[] strArr) {
		for (int i = 0; i < strArr.length; i++) {
			if (i % 2 == 0) {
				strArr[i] = strArr[i].toLowerCase(); // 짝수 인덱스 → 소문자
			} else {
				strArr[i] = strArr[i].toUpperCase(); // 홀수 인덱스 → 대문자
			}
		}
		return strArr;
	}

	public static void main(String[] args) {
		// Q181875
		System.out.println("strArr[0]과 strArr[2]는 짝수번째 인덱스의 문자열이므로 모두 소문자로 바꿔서 \"aaa\"와 \"ccc\"가 됩니다.\r\n"
				+ "strArr[1]과 strArr[3]는 홀수번째 인덱스의 문자열인데 원래 대문자이므로 그대로 둡니다.\r\n"
				+ "따라서 [\"aaa\",\"BBB\",\"ccc\",\"DDD\"]를 return 합니다.\n");

		Exam16 exam16 = new Exam16();
		System.out.println(exam16.solution(new String[] { "AAA", "BBB", "CCC", "DDD" }));
		// 출력: [aaa, BBB, ccc, DDD]

		System.out.println(exam16.solution(new String[] { "aBc", "AbC" }));
		// 출력: [abc, ABC]
		
		/*
		 * Scanner sc = new Scanner(System.in);
		 * 
		 * String[] strArr = sc.nextLine().split(" "); // 한 줄 전체 입력
		 * 
		 * for (int i = 0; i < strArr.length; i++) { if (i % 2 == 0) {
		 * System.out.print(strArr[i].toLowerCase() + " "); } else {
		 * System.out.print(strArr[i].toUpperCase() + " "); } }
		 */

	}

}
