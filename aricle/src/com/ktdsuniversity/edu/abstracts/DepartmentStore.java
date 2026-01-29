package com.ktdsuniversity.edu.abstracts;

public class DepartmentStore extends AbstractMart {

	public DepartmentStore(int productPrice) {
		super(productPrice);
	}

	@Override
	public int usePoint(Guest guest) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void givePoint(Guest guest, int amount) {
		/*
		 * Guest : Guest is a Guest 
		 * VIP : VIP is a Guest 
		 * VVIP : VVIP is VIP and Guest 높은
		 * 순서 대로 차례대로
		 * 
		 */
		if (guest instanceof VVIP vv) {
			guest.addPoint((int) (amount * 0.1));

		} else if (guest instanceof VIP v) {
			guest.addPoint((int) (amount * 0.03));

		} else if (guest instanceof Guest g) {
			guest.addPoint((int) (amount * 0.05));

		}

	}

	@Override
	public int discount(Guest guest, int amount) {
		return 0;
	}

}
