package com.ktdsuniversity.edu.array;

public class Q2562 {
	public static void main(String[] args) {
		// 첫째 줄에 최댓값을 출력하고, 둘째 줄에 최댓값이 몇 번째 수인지를 출력한다.
		int[] num = { 3, 29, 38, 12, 57, 74, 40, 85, 61 };

//		int max = num[0];
		int max = Integer.MIN_VALUE;
		int count = 0;

		for (int i = 0; i < num.length; i++) {
			// count++;
			if (num[i] > max) {
				max = num[i];
				count = i;
			}
		}
		System.out.println("최댓값 : " + max + "\n몇 번째 수 : " + (count + 1));

	}

}
