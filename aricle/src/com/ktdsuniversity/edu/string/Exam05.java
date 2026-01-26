package com.ktdsuniversity.edu.string;

public class Exam05 {
	public static void main(String[] args) {
		// Q10809
		System.out.println("각각의 알파벳에 대해서, a가 처음 등장하는 위치, b가 처음 등장하는 위치, ... z가 처음 등장하는 위치를 공백으로 구분해서 출력한다.");

		String name = "baekjoon";

		for (char c = 'a'; c <= 'z'; c++) {
			int n = name.indexOf(c); // 해당 알파벳의 첫 등장 위치
			System.out.println(n + " ");
		}

	}

}
