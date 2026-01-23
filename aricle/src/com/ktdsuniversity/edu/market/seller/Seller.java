package com.ktdsuniversity.edu.market.seller;

public class Seller {

	// 1. 상수
	// 2. 멤버변수
	// 3. 인스턴스 메소드
	private final int PRODUCT_WEIGHT = 500;
	private final int PRICE = 1000;

	private int stock;
	private int account;

	public Seller(int stock) {
		this.stock = stock;
	}

	public int getPRICE() {
		return this.PRICE;
	}

	public int getPRODUCT_WEIGHT() {
		return this.PRODUCT_WEIGHT;
	}

	public int getStock() {
		return this.stock;
	}

	public int getAccount() {
		return this.account;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public void setAccount(int account) {
		this.account = account;
	}

	public boolean isSoldOut() {
		return this.stock == 0;
	}

	public boolean isEnoughStock(int orderCount) {
		return this.stock >= orderCount;
	}

	public void sell(int orderCount) {
		boolean isEnoughStock = this.isEnoughStock(orderCount);
		boolean isSoldOut = this.isSoldOut();

		if (isSoldOut) {
			System.out.println("품절 되었습니다.");
		} else if (isEnoughStock) {
			this.stock -= orderCount;
			this.account += orderCount * this.PRICE;
		} else if (!isEnoughStock) {
			this.account += this.stock * this.PRICE;
			this.stock = 0;
		}

		System.out.println("남은 재고: " + this.stock);
		System.out.println("자본금: " + this.account);
	}

	public void sell2(int orderCount) {
		boolean isSoldOut = this.isSoldOut();
		boolean isEnoughStock = this.isEnoughStock(orderCount);

		if (isSoldOut) {
			System.out.println("품절 되었습니다.");
			return;
		}

		if (!isEnoughStock) {
			orderCount = this.stock;
		}

		this.stock -= orderCount;
		this.account += orderCount * this.PRICE;

		System.out.println("남은 재고: " + this.stock);
		System.out.println("자본금: " + this.account);
	}

}