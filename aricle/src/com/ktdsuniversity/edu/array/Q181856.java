package com.ktdsuniversity.edu.array;

/**
 * 입출력 예 #1
 * 
 * 예제 1번에서는 arr1의 길이는 2이고 arr2의 길이는 3으로 arr2의 길이가 더 깁니다. 따라서 arr2가 arr1보다 크므로
 * -1을 return 합니다. 입출력 예 #2
 * 
 * 예제 2번에서는 arr1의 길이과 arr2의 길이가 4로 같습니다. arr1의 모든 원소의 합은 100 + 17 + 84 + 1 =
 * 202이고 arr2의 모든 원소의 합은 55 + 12 + 65 + 36 = 168으로 arr1의 모든 원소의 합이 더 큽니다. 따라서
 * arr1이 arr2보다 크므로 1을 return 합니다. 입출력 예 #3
 * 
 * 예제 3번에서는 arr1의 길이와 arr2의 길이가 5로 같고 각 배열의 모든 원소의 합 또한 15로 같습니다. 따라서 arr1과
 * arr2가 같으므로 0을 return 합니다.
 */
public class Q181856 {
	public static void main(String[] args) {
		int[] arr1 = { 49, 13 };
		int[] arr2 = { 70, 11, 2 };

		System.out.println(compareArrays(arr1, arr2));
	}

	public static int compareArrays(int[] arr1, int[] arr2) {
		// 길이 비교
		if (arr1.length > arr2.length) {
			return 1;
		} else if (arr1.length < arr2.length) {
			return -1;
		}

		// 길이 같으면 더하기 비교
		int sum1 = 0;
		int sum2 = 0;

		for (int i = 0; i < arr1.length; i++) {
			sum1 += arr1[i];
		}
		for (int i = 0; i < arr2.length; i++) {
			sum2 += arr2[i];
		}

		if (sum1 > sum2) {
			return 1;
		} else if (sum1 < sum2) {
			return -1;
		} else {
			return 0;
		}

	}

}
