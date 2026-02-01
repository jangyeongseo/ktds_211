package com.ktdsuniversity.edu.exceptions;

public class ExceptionExam {
	public static void nullExam(String str) {
		// 모든 인스턴스에서 .을 사용할 때, 무조건 null체크를 해야하는가?
		// . 을 사용하려는 인스턴스가 null 이 될 수 있는 가능성이 조금이라도 존재할 때만 null체크 한다.

		// str이 비어있지 않으면 내용을 출력한다.
		if (str != null && !str.isEmpty()) {
			System.out.println(str);
		}
	}

	public static void arrayIndexExam(String[] arr, int index) {
		if (arr != null && index >= 0 && index < arr.length) {
			System.out.println(arr[index]);
		}
	}

	public static void numberFormatExam(String str) {

		if (str == null) {
			System.out.println(0);
			return;
		}

		str = str.replace("_", "");
		// 11자리, 22억 ==> 0으로 치환
		if (str.matches("^[0-9]+${1,10}")) {
			// 숫자를 정수로 변경한다.
			long tempNum = Long.parseLong(str);
			if (tempNum > Integer.MAX_VALUE) {
				tempNum = 0;
			}

			int num = (int) tempNum;
			System.out.println(num);
		} else {
			System.out.println(0);
		}

	}

	public static void main(String[] args) {
//		nullExam("sfsdf");
//		nullExam("    ");
//		nullExam("");
//		nullExam("12893uiosfd");
//		nullExam(null);

//		arrayIndexExam(new String[] {"a", "b"}, 0);
//		arrayIndexExam(new String[] {"a", "b"}, 1);
//		arrayIndexExam(new String[] {"a", "b"}, 2);
//		arrayIndexExam(new String[] {"a", "b"}, -1);
//		arrayIndexExam(null, -1);
//		arrayIndexExam(null, 0);

//		numberFormatExam("10");
//		numberFormatExam("2000");
//		numberFormatExam("3_000_000");
//		numberFormatExam("3000000000");
//		numberFormatExam("1.213213");
//		numberFormatExam("ㄴㅇ마ㅓㅗㄴㅇㅁ라ㅓㅗㄴㅇ");
		numberFormatExam(null);

	}
}
