package com.ktdsuniversity.edu.array;

/**
 * 입출력 예 #1
 * 
 * 예제 1번의 arr의 길이는 5로 홀수입니다. 따라서 arr의 짝수 인덱스 0, 2, 4에 주어진 n 값인 27을 더하면 [76, 12,
 * 127, 276, 60]이 됩니다. 따라서 [76, 12, 127, 276, 60]를 return 합니다. 입출력 예 #1
 * 
 * 예제 2번의 arr의 길이는 4로 짝수입니다. 따라서 arr의 홀수 인덱스 1, 3에 주어진 n 값인 100을 더하면 [444, 655,
 * 666, 877]이 됩니다. 따라서 [444, 655, 666, 877]를 return 합니다.
 */

public class Q181854 {
	public static void main(String[] args) {
		int[] arr = { 49, 12, 100, 276, 33 };

		int n = 27;
		for (int i = 0; i < arr.length; i++) {
			arr[i] += n;
			System.out.println(arr[i]);
		}

	}

}
