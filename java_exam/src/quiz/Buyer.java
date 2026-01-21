package quiz;

/**
 * 소바자
 */

public class Buyer {
	int money; // 지갑
	int cart; // 장바구니 수량
	int maxWeight; // 들 수 있는 최대 무게 (g)

	public Buyer(int money, int maxWeight) {
		this.money = money; // this : 멤버 변수 -> 매개 변수로 변환
		this.maxWeight = maxWeight;
	}

	/**
	 * 
	 * @param seller 매개 변수로 변환한 판매자 사용
	 * @param count  구매할 개수
	 */
	public void buy(Seller seller, int count) {
		int price = seller.price; // 상품의 가격 값
		int totalPrice = price * count; // 상품의 가격 값 * 구매개수
		int totalWeight = (cart + count) * 500; // 구매 개수 * 장바구니 = * 500g

		if (totalWeight > maxWeight) {
			// 들 수 있는 무게 초과 시
			System.out.println("더 이상 장바구니를 들 수 없습니다");
			return;
		}

		if (money < totalPrice) {
			// 지갑의 돈 부족
			System.out.println("돈이 부족합니다");
			return;
		}

		int bought = seller.sell(count); // 나간 재고 수
		cart += bought; // 장바구니 수량 + 구매 수
		money -= bought * price; // 지갑 = 재고수 * 상품 가격
	}
}
