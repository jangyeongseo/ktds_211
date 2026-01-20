package quiz;

public class Quiz01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 2부터 100,000 까지의 숫자 중 소수(Prime Number : 약수가 1과 자신만 있는 수)만 출력하는 프로그램을 작성해
		// 본인의 Git Repository에 Push하고 Repository 주소를 댓글로 등록하세요.
		boolean isPrime = true;

		for (int i = 2; i <= 100000; i++) {
			// 2부터 시작
			isPrime = true; // 리셋

			for (int j = 2; j <= Math.sqrt(i); j++) { // Math.sqrt(x) 반환값 : x의 양의 제곱근

				if (i % j == 0) {
					// 2의 배수일 경우 0이 나오며 false가 되기때문에 소수가 아니여서 안나온다.
					isPrime = false;
					// 소수가 아니여서 false

					break;
				}
			}
			if (isPrime) {
				// isPrime이 ture이면 값이나오도록
				System.out.println(i);
			}
		}

	}

}
