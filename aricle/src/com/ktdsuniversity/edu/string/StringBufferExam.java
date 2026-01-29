package com.ktdsuniversity.edu.string;

public class StringBufferExam {
	public static void main(String[] args) {
		// StringBuffer
		StringBuffer sb = new StringBuffer();

		// 인스턴스 - 이어붙인 현상이 나온다
		sb.append("아무거나 사용");
		sb.append("아무거나 사용");
		sb.append("아무거나 사용");
		sb.append("아무거나 사용");
		sb.append("아무거나 사용");
		sb.append("아무거나 사용");
		sb.append("아무거나 사용");

		String str = sb.toString(); // 문자열로 변경 - 이게 더 안전

		System.out.println(sb);
		System.out.println(str);

		StringBuffer longString = new StringBuffer();

//		for (int i = 0; i < 100; i++) {
//			longString.append("아무거나");
//		}

		// 메모리 사용을 줄이기 때문에 이 방법이 더 좋다.
		String resultString = longString.toString();
		System.out.println(">>" + resultString); // + 로 인하여 메모리 사용이 많이 사용된다.
	}

	public static void appendString(StringBuffer buffer) {
		buffer.append("12345");
	}

}
