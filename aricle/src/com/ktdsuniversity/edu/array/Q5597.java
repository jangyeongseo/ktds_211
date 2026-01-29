package com.ktdsuniversity.edu.array;

public class Q5597 {
	public static void main(String[] args) {
		// 출력은 2줄이다. 1번째 줄엔 제출하지 않은 학생의 출석번호 중 가장 작은 것을 출력하고,
		// 2번째 줄에선 그 다음 출석번호를 출력한다.
		int[] num = { 3, 1, 4, 5, 7, 9, 6, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28,
				29, 30 };

		boolean[] submitted = new boolean[31]; // 제출한 학생

		// 제출한 학생
		for (int i = 0; i < num.length; i++) {
			submitted[num[i]] = true;
		}

		int count = 0;
		// 제출 안한 학생
		for (int i = 1; i < 30; i++) {
			// false인 경우
			if (!submitted[i]) {
				System.out.println(i);
				count++;

				// 출력할 학생 수
				if (count == 2) {
					break;
				}
			}

		}
	}

}
