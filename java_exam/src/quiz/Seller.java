package quiz;

/**
 * 판매자
 */

public class Seller {
	int price = 1000; // 상품 가격
	int stock; // 재고
	int capital; // 자본금

	/**
	 * 
	 * @param stock   상품의 재고 개수
	 * @param capital 자본금
	 */
	public Seller(int stock, int capital) {
		this.stock = stock; // this : 멤버 변수 -> 매개 변수로 변환
		this.capital = capital;
	}

	/**
	 * 재고가 0일때 품절 안내문 등록
	 * 
	 * @param count
	 * @return
	 */
	public int sell(int count) {
		if (stock == 0) {
			// 제고 = 0;
			System.out.println("품절되었습니다");
			return 0;
		}

		int sellCount = Math.min(stock, count);
		// Math.min : 가장 작은 숫자(재고, 나간 개수)
		stock -= sellCount; // 재고 = 재고 - 나간 개수
		capital += sellCount * price; // 자본금 = 나간 개수 * 원가

		return sellCount; // 총 개수
	}
}
