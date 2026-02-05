package com.ktdsuniversity.edu.fp.basic;

import com.ktdsuniversity.edu.fp.basic.impl.CallAge2;
import com.ktdsuniversity.edu.fp.basic.impl.PrintName2;

public class CallFunction {
	public void callInterface(PrintSomething ps) {
		String something = "124124";
		ps.print(something);
	}

	public void callAbstractClass(CallSomething cs) {
		String something = "444";
		int result = cs.call(something);
		System.out.println("글자의 길이 : " + result);
	}

	public static void main(String[] args) {
		CallFunction cf = new CallFunction();
		cf.callInterface(new PrintName2());
		cf.callAbstractClass(new CallAge2());

		cf.callInterface(new PrintSomething() {

			@Override
			public void print(String message) {
				System.out.println(message);

				if (message != null) {
					System.out.println(message + "는 " + message.length() + "글자 입니다.");
				}
			}
		});

		cf.callAbstractClass(new CallSomething() {

			@Override
			public int call(String message) {
				if (message != null) {
					return message.length();
				}
				return 0;
			}
		});

		// 메소드만 전달
		// 추상 메소드가 한 개만 존재할 때 사용이 가능 / 여러개일 경우 람다를 쓸 수 없다. / 추상 메소드가 아예 없어도 안됨.
		// 함수형 인터페이스
		cf.callInterface((String message) -> System.out.println(message + " 입니다."));
		cf.callInterface(message -> message.length()); // 자바 문법상으로는 가능하다.
		// 위에 식을 사용할 경우 일치하기에 이렇게 가능하며 일치하지 않은 경우 {}사용

		/*
		 * cf.callAbstractClass((String message) -> { return 0; });
		 */

		PrintSomething function = (String message) -> {
			if (message == null) {
				System.out.println("파라미터 잘못됨");
			} else {
				System.out.println(message.repeat(10));
			}
		};

		System.out.println(function);
		cf.callInterface(function);

	}

}
