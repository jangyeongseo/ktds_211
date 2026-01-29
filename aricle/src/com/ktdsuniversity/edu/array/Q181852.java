package com.ktdsuniversity.edu.array;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Q181852 {
	public static void main(String[] args) {
		int[] numList = { 12, 4, 15, 46, 38, 1, 14, 56, 32, 10 };
		int temp = 0;

		for (int i = 0; i < numList.length; i++) {
			for (int j = i + 1; j < numList.length; j++) {
				System.out.println(numList[i] + "와 비교중인 숫자는 " + numList[j]);
				if (numList[i] > numList[j]) {
					// numList[i]와 numList[j]의 자리를 바꿔준다
					temp = numList[i];
					numList[i] = numList[j];
					numList[j] = temp;
				}
			}
		}
		System.out.println(Arrays.toString(numList));

		int[] result = new int[numList.length - 5];
		for (int i = 5; i < numList.length; i++) {
			result[i - 5] = numList[i];
		}

		System.out.println(Arrays.toString(result));

	}

}
