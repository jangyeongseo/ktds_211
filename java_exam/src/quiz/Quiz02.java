package quiz;

public class Quiz02 {

	public static String computeGrade(int subjA, int subjB, int subjC, int subjD, int subjE) {
		int amount = subjA + subjB + subjC + subjD + subjE;
		double average = calcAverage(amount, 5);
		String grade = calcGrade(average);
		return grade;
	}

	public static double calcAverage(int amount, int subjectCount) {
		// 평균을 구함.
		return amount / (double) subjectCount;

	}

	public static String calcGrade(double average) {
		// 등급을 구함.
		if (90 <= average) {
			return "A";
		} else if (80 <= average) {
			return "B";
		} else if (70 <= average) {
			return "C";
		} else if (60 <= average) {
			return "D";
		} else {
			return "F";
		}
	}

	public static void main(String[] args) {

		String grage = computeGrade(100, 95, 95, 100, 100);
		System.out.println(grage); // "A"

	}

}
