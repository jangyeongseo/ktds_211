package com.ktdsuniversity.edu.implement;

public class Test {
	public static void main(String[] args) {
		LgSmartTV lgTv = new LgTv();
		LgRemoteController lgRemoteController = new LgSmartRemotrController();
		lgRemoteController.turnOn(lgTv);
		lgRemoteController.turnOff(lgTv);
		lgTv.changeChannel(1);
		lgTv.changeVolumn(2);
		lgTv.runIntrnet();
		lgTv.runNetfilx();
		lgTv.runYoutube();

		SamsungSmartTV samsungTv = new SamsungTV();
		SamsungRemoteController samsungRemoteController = new SamsungSmartRemoteController();
		samsungTv.turnOn();
		samsungTv.turnOff();
		samsungTv.changeChannel(1);
		samsungTv.changeVolumn(2);
		samsungTv.runCoumangPlay();
		samsungTv.runNetfilx();
		samsungTv.runMenu();

		lgRemoteController.turnOn(samsungTv);
		// 타입이 같으며, 다향성 : is a 때문에 사용이 가능하다 TV를 받았기에

	}
}
