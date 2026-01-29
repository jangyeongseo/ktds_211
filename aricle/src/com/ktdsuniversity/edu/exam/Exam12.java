package com.ktdsuniversity.edu.exam;

public class Exam12 {
	public String solution(String nstr) {
		return String.valueOf(Integer.parseInt(nstr));
		// or return Integer.parseInt(nstr) + " ";

	}

	public static void main(String[] args) {
		// Q181847
		System.out.println("입출력 예 #1\r\n" + "\r\n" + "\"0010\"의 가장 왼쪽에 연속으로 등장하는 \"0\"을 모두 제거하면 \"10\"이 됩니다.\r\n"
				+ "입출력 예 #2\r\n" + "\r\n" + "\"854020\"는 가장 왼쪽에 0이 없으므로 \"854020\"을 return합니다.\n");

		Exam12 exam12 = new Exam12();
		System.out.println(exam12.solution("0010")); // "10"
		System.out.println(exam12.solution("854020")); // "854020"

	}

}
