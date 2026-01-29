package com.ktdsuniversity.edu.array;

import java.util.Arrays;

/**
 * 예제 1번의 my_string은 " i love you"로 공백을 기준으로 단어를 나누면 "i", "love", "you" 3개의 단어가
 * 있습니다. 따라서 ["i", "love", "you"]를 return 합니다.
 */
public class Q181868 {
	public static void main(String[] args) {
		String myString = "  i  love you";
		String[] m = myString.split(" ");

		// while() 반복문을 넣어서 뛰어쓰기가 계속 반복되는 구간을 삭제할 수 있음.

		for (int i = 0; i < m.length; i++) {
			System.out.print(m[i]);
		}
		System.out.println();
		System.out.println(Arrays.toString(m));

	}

}
