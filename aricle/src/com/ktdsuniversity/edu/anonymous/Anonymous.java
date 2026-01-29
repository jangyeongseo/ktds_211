package com.ktdsuniversity.edu.anonymous;

import com.ktdsuniversity.edu.abstracts.AbstractMart;
import com.ktdsuniversity.edu.abstracts.Guest;
import com.ktdsuniversity.edu.implement.TV;

public class Anonymous {
	public static void main(String[] args) {

		// Cannot instantiate the type AbstractMart
		AbstractMart mart = new AbstractMart(1500) {
			// 이름이 없는 클래스
			@Override
			public int usePoint(Guest guest) {
				return 0;
			}

			@Override
			public void givePoint(Guest guest, int amount) {
				guest.addPoint(10_000_000);
			}

			@Override
			public int discount(Guest guest, int amount) {
				return amount;
			}
		};

		Guest guest = new Guest(0, 0);
		mart.sell(guest, 10000, 0);
		System.out.println(guest);

		TV emartTV = new TV() {
			@Override
			public void turnOn() {
				System.out.println("on");
			}

			@Override
			public void turnOff() {
				System.out.println("off");
			}

			@Override
			public void changeChannel(int channelNumber) {
				System.out.println("changeChannel");
			}

			@Override
			public void changeVolumn(int volumn) {
				System.out.println("changeVolumn");
			}
		};

		emartTV.turnOn();
		emartTV.turnOff();
		emartTV.changeChannel(36);
		emartTV.changeVolumn(50);

		System.out.println(mart);
		System.out.println(emartTV);

	}
}
