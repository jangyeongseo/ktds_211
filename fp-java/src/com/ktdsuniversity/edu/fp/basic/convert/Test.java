package com.ktdsuniversity.edu.fp.basic.convert;

public class Test {
	public static void main(String[] args) {
		Converter converter = new Converter();

		// 숫자로 변환한 결과를 반환
		// 스테틱 static 메소드
		converter.printConvertResult("10", str -> Integer.parseInt(str));

		// 메소도의 원형이 같아서 가능
		// 메소드 레퍼런스
		converter.printConvertResult("12", Integer::parseInt);

		// 파라미터의 타입을 따라간다.
		converter.printConvertResult2("askldjf", str -> str.length());
		converter.printConvertResult2("askldjf", String::length);

		// static 메소드를 이용할 경우 파라미터와 타입이 일치해야한다.

	}
}
