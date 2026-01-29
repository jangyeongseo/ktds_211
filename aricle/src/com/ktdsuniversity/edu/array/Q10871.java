package com.ktdsuniversity.edu.array;

public class Q10871 {
	public static void main(String[] args) {
		int[] num = { 1, 10, 4, 9, 2, 3, 8, 5, 7, 6 };
		int baseNum = 5;

		for (int i = 0; i < num.length; i++) {
			if (num[i] < baseNum) {
				System.out.println(num[i] + " ");
			}
		}

	}

}
