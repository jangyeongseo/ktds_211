package com.ktdsuniversity.edu.constants;

/**
 * 열거형 테스트
 */
public class CalculatorTest {
	public static void main(String[] args) {

		Calculator calc = new Calculator(40, 20);

		System.out.println(calc.compute(Calculator.ADD));
		System.out.println(calc.compute(Calculator.SUB));
		System.out.println(calc.compute(Calculator.MUL));
		System.out.println(calc.compute(Calculator.DIV));

		System.out.println();

		// 이런 문제가 있고
		System.out.println(calc.compute(1));
		System.out.println(calc.compute(2));
		System.out.println(calc.compute(3));
		System.out.println(calc.compute(4));

		System.out.println();

		// enum 을 한것을 불러와서 지정해준다
		System.out.println(calc.compute2(Operator.ADD));
		System.out.println(calc.compute2(Operator.SUB));
		System.out.println(calc.compute2(Operator.MUL));
		System.out.println(calc.compute2(Operator.DIV));

	}
}
