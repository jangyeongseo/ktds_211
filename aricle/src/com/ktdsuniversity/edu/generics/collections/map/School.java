package com.ktdsuniversity.edu.generics.collections.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class School {

	public static void printStudente(Map<Integer, List<Student>> classes, int classNum) {
		List<Student> students = classes.get(classNum);

		if (students != null) {
			for (Student student : students) {
				System.out.println(classNum + "반의 학생 : " + student);
			}
		}
	}

	public static void main(String[] args) {
		Map<Integer, List<Student>> classes = new HashMap<>();

		classes.put(1, new ArrayList<>()); // 1반에 있는 학생의 목록
		classes.put(2, new ArrayList<>()); // 1반에 있는 학생의 목록

		List<Student> students = classes.get(1);
		students.add(new Student("김범수", 1));
		students.add(new Student("나얼", 2));
		students.add(new Student("박효신", 3));
		students.add(new Student("이수", 4));

		printStudente(classes, 0);
		printStudente(classes, 1);
		printStudente(classes, 2);
		printStudente(classes, 3);
		
		/*
		 * System.out.println(classes); 
		 * System.out.println(classes.size());
		 * 
		 * // Map은 for문으로 반복을 할 수 없음 
		 * // 반복시킬 일이 없다 
		 * for (Integer key : classes.keySet()){ 
		 * System.out.println(classes.get(key)); 
		 * }
		 */

	}
}
