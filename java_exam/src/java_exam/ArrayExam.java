package java_exam;

public class ArrayExam {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] scoreArray = new int[7];
		// new : 참조형 변수(Reference type)를 만들어라
		// String 도 참조형 변수
		// scoreArray.length -> . : 연산자 / 객체의 속성/메서드 접근 연산자
		// 0|0|0|0|0|0|0
		scoreArray[0] = (int) Math.random() * 45 + 1;
		scoreArray[1] = 22;
		scoreArray[2] = 55;
		scoreArray[3] = 133;
		scoreArray[4] = 333;
		scoreArray[5] = 777;
		scoreArray[6] = 888;

		System.out.println(scoreArray[0]);
		System.out.println(scoreArray[1]);
		System.out.println(scoreArray[2]);
		System.out.println(scoreArray[3]);
		System.out.println(scoreArray[4]);
		System.out.println(scoreArray[5]);
		System.out.println(scoreArray[6]);

		System.out.println();

		for (int i = 0; i < scoreArray.length; i++) {
			// length 보다 작다라고 적기
			System.out.println(scoreArray[i]);
		}

		System.out.println();

		// scoreArray[0] = (int) Math.random() * 45 + 1;

		for (int i = 0; i < scoreArray.length; i++) {
			scoreArray[i] = (int) (Math.random() * 45 + 1);
			System.out.println(scoreArray[i]);
		}

		// 배열 + for + while
		// 서로 다른 숫자 7개가 배열에 할당될 때까지 반복
		// 배열의 값을 출력한다.
		// cose 1
		int conut = 0;
		int num = 0;
		boolean existsNumber = false;

		while (conut < 7) {
			num = (int) (Math.random() * 45 + 1);
			existsNumber = false;
			
			for (int i = 0; i < scoreArray.length; i++) {
				if (scoreArray[i] == num) {
					existsNumber = true;
					break;
				}
			}
			if (!existsNumber) {
				scoreArray[conut] = num;
				System.out.println(scoreArray[conut]);
			}
		}

		for (int i = 0; i < scoreArray.length; i++) {
			System.out.println(scoreArray[i] + "");
		}
		System.out.println("");

	}

}
