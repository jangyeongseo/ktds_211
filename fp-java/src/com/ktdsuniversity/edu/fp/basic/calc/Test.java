package com.ktdsuniversity.edu.fp.basic.calc;

public class Test {
	public static void main(String[] args) {
		Calculator calc = new Calculator();

		// num1, num2를 더해 반환한다.
		int result = calc.calc2(1, 2, (num1, num2) -> num1 + num2);
		System.out.println("더하기 : " + result);

		// 제곱 : Math.pow(n,n) / 큰 수 찾기 : Math.max(); / 작은 수 찾기 : Math.min();
		// num1 의 num2를 재곱헌 수를 반환
		result = calc.calc2(1, 2, (num1, num2) -> (int) Math.pow(num1, num2));
		// result = calc.calc(3, 15, Math::powExact);

		System.out.println("\n제곱 : " + result);

		// num1, num2 중 큰수를 반환
		result = calc.calc2(1, 2, (num1, num2) -> Math.max(num1, num2));
		result = calc.calc2(1, 2, Math::max);

		// 형태가 같다면 메서드 레퍼런스를 사용하는데 지장이 없음 / 파라미터 형태 반환 타입이 같다
		// Math 에 있는 min 이라는 메서드를
		result = calc.calc(1, 2, Math::min);

		System.out.println("\n큰 수 : " + result);

		// num1, num2 중 작은 수를 반환
		result = calc.calc(1, 2, (num1, num2) -> Math.min(num1, num2));
		System.out.println("\n작은 수: " + result);

		// num1이 num2의 배수라면 0을 반환, 아니라면 1을 반환
		result = calc.calc(2, 2, (num1, num2) -> num1 % num2 == 0 ? 0 : 1);
		System.out.println("\n배수라면 0, 아니면 1 : " + result);

	}
}
