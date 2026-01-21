package oop;

import java.util.Scanner;

public class StudentTest {

	public static void main(String[] args) {
		Student st = new Student();
		st.java = 80;
		st.python = 90;
		st.cpp = 75;
		st.csharp = 75;

		int sum = st.getSumAllScores();
		double average = st.getAverage();
		double course = st.getCourseCredit();
		String total = st.getABCDF();

		System.out.println("총 합계 : " + sum + "평균점수 : " + average + "학점 점수 : " + course + "총 점수 : " + total);

	}

}
