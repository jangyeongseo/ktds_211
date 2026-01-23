package com.ktdsuniversity.edu.market.consumer;

import com.ktdsuniversity.edu.market.seller.Seller;

public class Buyer {
	private int maxCartWeight;
	private int nowCartWeight;

	private int itemCount;
	private int wallet;

	public Buyer(int wallet, int maxCartWeight) {
		this.wallet = wallet;
		this.maxCartWeight = maxCartWeight;
	}

	public int getMaxCartWeight() {
		return this.maxCartWeight;
	}

	public int getNowCartWeight() {
		return this.nowCartWeight;
	}

	public int getItemCount() {
		return this.itemCount;
	}

	public int getWallet() {
		return this.wallet;
	}

	public void setMaxCartWeight(int maxCartWeight) {
		this.maxCartWeight = maxCartWeight;
	}

	public void setNowCartWeight(int nowCartWeight) {
		this.nowCartWeight = nowCartWeight;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public void setWallet(int wallet) {
		this.wallet = wallet;
	}

	public boolean isEnoughMoney(int money) {
		return this.wallet >= money;
	}

	public boolean isEnoughWeight(int weight) {
		int predictWeigth = weight + this.nowCartWeight;
		return this.maxCartWeight >= predictWeigth;
	}

	public void buy(int money, int weight, int productCOunt) {
		if (!this.isEnoughMoney(money)) {
			System.out.println("돈이 부족합니다.");
		} else if (!this.isEnoughWeight(weight)) {
			System.out.println("더 이상 장바구니를 들 수 없습니다.");
		} else {
			this.wallet -= money;
			this.itemCount += productCOunt;
			this.nowCartWeight += weight;
		}

		System.out.println("상품의 수: " + this.itemCount);
		System.out.println("장바구니의 무게: " + this.nowCartWeight);
		System.out.println("지갑에 있는 돈: " + this.wallet);

	}

	/**
	 * 구매자가 판매자에게 상품을 구매한다.
	 * 
	 * @param seller       구매자에게 상품을 판매할 판매자 인스턴스
	 * @param productCOunt 구매자가 구매할 상품의 개수
	 */

	public void buyFrom(Seller seller, int productCOunt) {
		// 1. 구매자 : 혹시 두쫀쿠 productCOunt 만큼 있나요?
		if (seller.isEnoughStock(productCOunt)) {
			// 2. 판매자 : 있어요 혹은 없어요(break)
			// 3. 구매자 : 하나에 얼마에요?
			int price = seller.getPRICE();
			// 4. 판매자 : 1,000원 입니다.
			// 5. 구매자 : (내가 5,000원이 있나?)
			if (this.isEnoughMoney(price * productCOunt)) {
				// 있으면 구매, 판매 혹은 없으면 구매 불가, 판매 불가
				// 6. 구매자 : 혹시, 무게가 많이 무겁나요?
				int weight = seller.getPRODUCT_WEIGHT();
				// 7. 판매자 : 한 500g 합니다.
				// 8. 구매자 : 내가 2.5kg 들 수 있나?
				if (this.isEnoughWeight(weight * productCOunt)) {
					// 둘 수 있다면 구매, 판매 혹은 들 수 없다면 구매, 판매 불가
					// 9. 구매자 : 5개만 주시겠어요?
					int stock = seller.getStock();
					stock -= productCOunt;
					seller.setStock(stock);
					// 10. 판매자 : 두쫀쿠 5개 포장해서 준다.(판매자의 제고 감소)
					// 11.구매자 : 여기 5,000원 드릴게요.(구매자 돈이 감소 + 구매자의 무게 증가, 구매 개수 증가,판매자 자본이 증가)
					this.wallet -= price * productCOunt;
					int account = seller.getAccount();
					account += price * productCOunt;
					seller.setAccount(account);

					this.nowCartWeight += productCOunt * weight;
					this.itemCount += productCOunt;

				}

			}

		} else {
			System.out.println("품절 되었습니다.");
		}

	}

}