package com.ktdsuniversity.edu.array;

public class Q10818 {
	public static void main(String[] args) {
		// 첫째 줄에 주어진 정수 N개의 최솟값과 최댓값을 공백으로 구분해 출력한다.
		int[] num = { 20, 10, 35, 30, 7 };
//		int max = num[0];
//		int min = num[0];

		// 이것도 가능
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;

		for (int i = 1; i < num.length; i++) {
			if (num[i] > max) {
				max = num[i];
			}
			if (num[1] < min) {
				min = num[i];
			}
		}
		System.out.println("min : " + min + "\nmax : " + max);

	}

}
