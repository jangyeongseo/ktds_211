package com.project.pjt.store;

/**
 * 편의점
 */
public class ConvenienceStore extends Mart {

	public ConvenienceStore(int productPrice) {
		super(productPrice);
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
		int point = (int) (amount * 0.001);
		guest.addPoint(point);

	}

	@Override
	public int discount(Guest guest, int amount) {
		return 0;
	}

}
