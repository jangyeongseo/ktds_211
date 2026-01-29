package com.ktdsuniversity.edu.array;

public class Q10807 {
	public static void main(String[] args) {
		int[] numArray = { 1, 4, 1, 2, 4, 2, 4, 2, 3, 4, 4 };

		int findNum = 2;

		int count = 0;
		for (int i = 0; i < numArray.length; i++) {
			if (numArray[i] == findNum) {
				count++;
			}
		}

		System.out.println("겹치는 개수 : " + count);

	}

}
