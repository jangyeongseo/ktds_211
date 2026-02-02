package com.ktdsuniversity.edu.generics;

import java.util.Arrays;

public class ScoreListTest {
	public static void main(String[] args) {
		// 뭔 타입을 줄것인지를 아렬줘야함
		ScoreList<Integer, Integer> list = new ScoreList<>();
		// list.add(list); // 무엇이든 넣을 수 있다 지금은 object 이기에
		// int 만 넣고 샆다 integer / String - String
		list.add(1531);
		list.add(121654);
		list.add(121654);

		Reducer<Integer, Integer> listReducer = new Reducer<>() {
			@Override
			public Integer reduce(Integer input, Integer output) {
				return input + output;
			}
		};

		int sum = list.sum(listReducer, 0); // int 의 값으로
		System.out.println(sum);

		int value = list.get(2);
		System.out.println(value);

		ScoreList<String, String> strList = new ScoreList<>();
		strList.add("\n나중에 결정한다.");
		strList.add("배열이 2개 설정되어 사용가능해짐");

		String concat = "";
		for (int i = 0; i < 2; i++) {
			concat += strList.get(i);
		}
		System.out.println(concat);

		String strValue = strList.get(0);
		System.out.println(strValue);

		ScoreList<String[], String> arrayList = new ScoreList<>();
		arrayList.add(new String[] { "ABC", "abcd" });
		arrayList.add(new String[] { "DEF", "abcd" });

		Reducer<String[], String> arrayReducer = new Reducer<>() {

			@Override
			public String reduce(String[] input, String output) {
				for (int i = 0; i < input.length; i++) {
					output += input[i];
				}
				return output;
			}
		};

		String arrayResult = arrayList.sum(arrayReducer, ""); // String 문자열로 변한화여 값이 나오도록 설정
		System.out.println(arrayResult);

		String[] arrayValue = arrayList.get(1);
		System.out.println(Arrays.toString(arrayValue));
		String[] arrayValue2 = arrayList.get(2); // 개수가 안맞아서 오루 발생
		System.out.println(Arrays.toString(arrayValue2));

	}
}
