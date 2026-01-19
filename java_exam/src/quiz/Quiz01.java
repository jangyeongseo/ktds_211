package quiz;

public class Quiz01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		2부터 100,000 까지의 숫자 중 소수(Prime Number : 약수가 1과 자신만 있는 수)만 출력하는 프로그램을 작성해
//		본인의 Git Repository에 Push하고 Repository 주소를 댓글로 등록하세요.

		for (int i = 2; i <= 100000; i++) { 
			// 2부터 시작
			boolean isPrime = true;

			for (int j = 2; j <= Math.sqrt(i); j++) {
				
				if (i % j == 0) {
					isPrime = false;
					break;
				}
			}

			if (isPrime) {
				System.out.println(i);
			}
		}
	}

}
