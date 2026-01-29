package com.ktdsuniversity.edu.array;

import java.util.Arrays;

public class Q181853 {
	public static void main(String[] args) {
		// [12, 4, 15, 46, 38, 1, 14]를 정렬하면 [1, 4, 12, 14, 15, 38, 46]이 되고, 앞에서 부터 5개를
		// 고르면 [1, 4, 12, 14, 15]가 됩니다.

		int[] numList = { 12, 4, 15, 46, 38, 1, 14 };
		int result = 5;

		int temp = 0;
		for (int i = 0; i < numList.length; i++) {
			for (int j = i + 1; j < numList.length; j++) {
				if (numList[i] > numList[j]) {
					// numList[i]와 numList[j]의 자리를 바꿔준다
					temp = numList[i];
					numList[i] = numList[j];
					numList[j] = temp;
				}
			}
		}
		System.out.println(Arrays.toString(numList)); // 결과 보기

		for (int i = 0; i < result; i++) {
			System.out.print(numList[i] + " ");
		}
	}

}
