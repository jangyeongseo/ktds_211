package quiz;

/**
 * 소바자
 */

public class Buyer {
	int money; // 지갑
	int cart; // 장바구니 수량
	int maxWeight; // 들 수 있는 최대 무게 (g)

	/**
	 * 생성자가 만들어준 인스턴스 / 파라미터 값을 가지고 있는 생성자다. 
	 * 생성자 호출(파라미터 추가)
	 * 
	 * @param money     소비자의 돈
	 * @param maxWeight 소비자가 들 수 잇는 최대 무게ㄴ
	 */
	public Buyer(int money, int maxWeight) {
		this.money = money;
		this.maxWeight = maxWeight;
	}

	/**
	 * 소비자의 구매 목록과 남은 돈을 확인할 수 있다.
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
