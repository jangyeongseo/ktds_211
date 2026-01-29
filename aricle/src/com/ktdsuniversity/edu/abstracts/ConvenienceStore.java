package com.ktdsuniversity.edu.abstracts;

/**
 * 편의점
 */
public class ConvenienceStore extends Mart {

	public ConvenienceStore(int productPrice) {
		super(productPrice);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int usePoint(Guest guest) {
		int point = guest.getPoint();
		if (point >= 100) {
			guest.usePoint(point);
			return point;
		}
		return 0;
	}

	@Override
	public void givePoint(Guest guest, int amount) {
		// TODO Auto-generated method stub
		int point = (int) (amount * 0.01);
		guest.addPoint(point);

	}

	@Override
	public int discount(Guest guest, int amount) {
		// TODO Auto-generated method stub
		return 0;
	}

}
