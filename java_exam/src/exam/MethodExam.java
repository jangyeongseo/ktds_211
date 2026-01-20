package exam;

public class MethodExam {

	// 1번.
	public static void printSum(int sum) {

		int num = 0;
		for (int i = 1; i < sum; i++) {
			num = i + sum;
		}
		System.out.println(num);
	}

	// 2번
	public static void printPrime(int prime) {
		boolean print = true;

		for (int i = 5; i <= prime; i++) {
			print = true;

			for (int j = 5; j <= Math.sqrt(i); j++) {
				if (i % j == 0) {
					print = false;
					break;
				}
			}
			if (print) {
				System.out.println(i);
			}
		}
	}

	// 3번
	public static int printMax(int n, int m) {
		if (n > m) {
			return n;
		} else {
			return m;
		}
	}

	// 4번
	public static void printAge(int age) {
		if (age >= 19) {
			System.out.println("성인");
		} else {
			System.out.println("미성년");
		}
	}

	// 5번
	public static String getFizzBuzz(int random) {
		
		if (random % 15 == 0) {
			return "FizzBuzz";
		} else if (random % 5 == 0) {
			return "Buzz";
		} else if (random % 3 == 0) {
			return "Fizz";
		} else {
			return random + "";
		}
	}

	// 6번
	public static void getUniqueNumbers() {
		int[] num = new int[7];

		int total = 0;
		boolean b = false;
		int conut = 0;

		while (conut < 7) {
			conut++;
			total = (int) (Math.random() * 45 + 1);
			b = false;

			for (int i = 0; i < num.length; i++) {
				num[i] = conut;

				for (int j = 0; j < i; j++) {
					if (num[i] == num[j]) {
						b = true;
						break;
					}
				}

				if (!b) {
					conut = num[i];
					System.out.println(num[i]);
				}
			}
		}
	}

	// 7번
	public static void getEvenOdd(int num1, int num2) {
		int total = num1 * num2;
		if (total % 2 == 0) {
			System.out.println("짝수");
		} else {
			System.out.println("홀수");
		}
	}

	// 8번
//	public static void getSumOfFive() {
//		int[] add = new int[5];
//
//		int a = 0;
//		for (int i = 1; i < add.length; i++) {
//			a = add[i];
//			System.out.println(i);
//		}
//	}

//	// 9번
//	public static void getAverage(int g, int c) {
//
//		double num = g / c;
//		num /= 0.1;
//		System.out.println(num);
//
//	}

	// 10번
	public static void getGrade(int math) {
		if (90 <= math && math <= 100) {
			System.out.println("A");
		} else if (80 <= math && math <= 89) {
			System.out.println("B");
		} else if (70 <= math && math <= 79) {
			System.out.println("C");
		} else if (60 <= math && math <= 69) {
			System.out.println("D");
		} else {
			System.out.println("F");
		}
	}

	// 11번
	public static void nextChar(char next) {
		next += 1;
		System.out.println(next);

	}

	// 12번
	public static void getCharacters(char g1, char g2) {

		for (int i = 0; i <= g2; i++) {
			if (g1 <= i && i <= g2) {

			}
		}
	}

	public static void main(String[] args) {

		// 1번 
		// 1부터 파라미터로 전달된 숫자까지의 합을 구한다.
		printSum(10);

		System.out.println();

		// 2번
		// 4부터 파라미터로 전달된 숫자 중 소수만 출력한다.
		printPrime(20);

		System.out.println();

		// 3번
		// 파라미터로 전달된 숫자 두 개 중 가장 큰 수 하나만 출력한다.
		int max = printMax(10, 20);
		System.out.println(max);

		System.out.println();

		// 4번
		// 파라미터로 전달된 나이가 성인인지 미성년인지 구분하여 출력한다.
		printAge(20);
		printAge(15);

		System.out.println();

		// 5번
		// 랜덤한 숫자를 생성하고 아래 규칙에 따른 결과를 반환한다.
		getFizzBuzz(9);
		getFizzBuzz(10);
		getFizzBuzz(15);
		getFizzBuzz(7);

		System.out.println();

		// 6번
		// 서로 다른 숫자 7개를 배열에 할당하여 반환한다.
		getUniqueNumbers();

		System.out.println();

		// 7번
		// 정수 파라미터 두 개를 전달받아 곱한 결과가 짝수인지 홀수인지 반환한다.
		getEvenOdd(4, 5);
		getEvenOdd(3, 5);

		System.out.println();

		// 8번
		// 전달된 정수 파라미터 5개의 합을 구해 반환한다.
		// getSumOfFive(1, 2, 3, 4, 5);

		System.out.println();

		// 9번
		// 합과 점수의 개수를 전달하여 평균을 구해 반환한다.
		// getAverage(150, 3);

		System.out.println();

		// 10번
		// 평균 점수를 파라미터로 전달하여 학점을 반환한다.
		getGrade(95);
		getGrade(85);
		getGrade(75);
		getGrade(65);
		getGrade(55);

		System.out.println();

		// 11번
		// char 타입의 파라미터 하나를 받아 그 다음의 글자를 문자열로 반환한다.
		nextChar('a');
		nextChar('c');

	}

}
