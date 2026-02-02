package com.ktdsuniversity.edu.generics.collections.list;

/**
 * 컬렉션
 */
import java.util.ArrayList;
import java.util.List;

import com.ktdsuniversity.edu.abstracts.Mart;

public class ListExam {
	public static void main(String[] args) {

		List<String> names = new ArrayList<>();
		names.add("sj");
		names.add("sj");
		names.add("sj");
		names.add("sj");
		names.add("sj");

		System.out.println(names);

		String name = null;
		for (int i = 0; i < names.size(); i++) {
			name = names.get(i);
			System.out.println(name);
		}

		for (String name2 : names) {
			System.out.println(name2);
		}

		// 인덱스 몇번째에 있는지 알고 싶을때
		int[] arr = new int[] { 1, 2, 3 };
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}

		// for-each문
		// 인덱스 필요없어
		// 단순 출력
		for (int value : arr) {
			System.out.println(value);
		}

		List<Mart> product = new ArrayList<>();

	}
}
