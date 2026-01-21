package oop;

/**
 * <pre>
 * 학생 평균 및 학점 구하기 실습
 * </pre>
 */

public class Student {

	// 멤버 변수
	int java;
	int python;
	int cpp;
	int csharp;

	// 인스턴스
	/**
	 * 멤버변수를 모두 더한 int 타입
	 */
	public int getSumAllScores() {
		if (java <= 100) {
			System.out.println("java 점수 : ");
		}
		if (python <= 100) {
			System.out.println("python 점수 : ");

		}
		if (cpp <= 100) {
			System.out.println("cpp 점수 : ");

		}
		if (csharp <= 100) {
			System.out.println("csharp 점수 : ");

		}

		int sum = java + python + cpp + csharp;
		System.out.println("총 합계 : " + sum + "\n" + "");

		return sum;
	}

	/**
	 * 합계점수 / 4의 결과를 소수점 아래 두 자리까지만 반환.
	 */

	public double getAverage() {
		double average = (java + python + cpp + csharp) / 4.0 * 100;
		System.out.println("평균 점수 : " + average + "\n" + "");

		return average;
	}

	/**
	 * (평균점수 – 55) / 10의 결과를 소수점 아래 두 자리 까지만 반환
	 * 
	 * @return
	 */

	public double getCourseCredit() {
		double course = Math.round((java + python + cpp + csharp) - 55) / 10d * 100;
		System.out.println("평균 점수 : " + course + "\n" + "");
		return course;
	}

	/**
	 * 학점을 아래 기준에 맞춰 반환 4.1 ~ 4.5: A+ / 3.6 ~ 4.0: A ./ 3.1 ~ 3.5: B+ ./ 2.6 ~ 3.0:
	 * B ./ 1.6 ~ 2.5: C ./ 0.6 ~ 1.5: D ./ 0.1 ~ 0.5: F
	 */

	public String getABCDF() {
		double course = Math.round((java + python + cpp + csharp) - 55) / 10d;

		if (4.1 <= course && course <= 4.5) {
			return "A+";

		} else if (3.6 <= course && course <= 4.0) {
			return "A";

		} else if (3.1 <= course && course <= 3.5) {
			return "B+";

		} else if (2.6 <= course && course <= 3.0) {
			return "B";

		} else if (1.6 <= course && course <= 2.5) {
			return "C";

		} else if (0.6 <= course && course <= 1.5) {
			return "D";
		} else {
			return "F";
		}
	}

}
