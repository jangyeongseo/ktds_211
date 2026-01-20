package java_exam;

public class MethodExam {

	public static int computeAdd(int number1, int number2) {
		int conpiteResult = number1 + number2;
		return conpiteResult;
		// return : 메서드의 값을 반환 및 종료
	}

	public static void printMyName(String name) {
		System.out.println("제 이름은 " + name + "입니다.");

		if (name.length() < 3) {

			return; // void일 때의 return 값은 종료 시킨다는 의미
		}

		System.out.println("제 이름은 " + name.length() + "글자 입니다.");
	}

	// 프로그램이 실행될 수 있는 메소드를 생성한다.
	public static void main(String[] args) {
		// JVDM이 실행됨

//		System.out.println("제 이름은 ))입니다");
//		System.out.println("제 이름은 : ");
//		System.out.println("재 이름은 : ");
		printMyName("민지");
		printMyName("땡땡이");
		printMyName("물귀신");

		int sum = computeAdd(4, 5);
		System.out.println(sum);
	}

}
