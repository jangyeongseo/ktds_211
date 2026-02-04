package com.project.pjt.store;

public class DepartmentStore extends AbstractMart {

	public DepartmentStore(int productPrice) {
		super(productPrice);
	}

	@Override
	public int usePoint(Guest guest) {
		int point = guest.getPoint();
		if (point >= 10000) {
			int used = Math.min(point, getProductPrice());
			guest.usePoint(used);
			return used;
		}
		return 0;
	}

	@Override
	public void givePoint(Guest guest, int amount) {
		if (guest instanceof VVIP) {
			guest.addPoint((int) (amount * 0.03)); // 3%
		} else if (!(guest instanceof VIP)) {
			guest.addPoint((int) (amount * 0.005)); // 0.5%
		}

	}

	@Override
	public int discount(Guest guest, int amount) {
		if (guest instanceof VVIP)
			return (int) (amount * 0.10); // 10%
		if (guest instanceof VIP)
			return (int) (amount * 0.03); // 3%
		return 0;
	}

}
