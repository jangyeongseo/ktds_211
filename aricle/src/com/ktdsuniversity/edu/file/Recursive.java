package com.ktdsuniversity.edu.file;

/**
 * 같은 메세지가 쌓이는 것 : 재귀 호출 내가 나를 호출하는 것 (자기 자신을 반복적으로 실행) 
 * 제기호출은 무한반복에서 주의해서 사용해줘야한다.
 */
public class Recursive {

	public int sumToZero(int start) {
		// start => 5
		// start => 1

		if (start == 1) {
			return 1;
		}

		return start + sumToZero(start - 1);

	}

	public void printNumber(int number) {
		System.out.println(number);

		if (number > 0) {
			printNumber(number - 1);
		}
	}

	// print 메서드: 자기 자신을 계속 호출하는 구조
	public void print(int number) {
		System.out.println("출력입니다. - " + number + "\n"); // 콘솔에 메시지 출력
		if (number < 3) {
			print(number + 1); // 자기 자신을 다시 호출 → 종료 조건이 없으므로 무한 호출 발생
			// 스택 메모리가 가득 차면 StackOverflowError 발생

			System.out.println(number + " 스택을 실행을 완료했습니다.");
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Recursive r = new Recursive(); // Recursive 객체 생성
		r.print(1); // print 메서드 실행 → 무한 재귀 호출 시작
		r.printNumber(5); // 결과 -> 2000 ~ 0

		int result = r.sumToZero(5);
		System.out.println(result);
	}
}
