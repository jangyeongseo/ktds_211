package com.ktdsuniversity.edu.implement;

public class SamsungSmartRemoteController implements SamsungRemoteController {

	@Override
	public void turnOn(TV tv) {
		tv.turnOn();
	}

	@Override
	public void turnOff(TV tv) {
		tv.turnOff();
	}

	@Override
	public void changeChannel(TV tv, int channelNumber) {
		tv.changeChannel(channelNumber);
	}

	@Override
	public void changeVolumn(TV tv, int volumn) {
		tv.changeVolumn(volumn);
	}

	@Override
	public void runNetfilx(SamsungSmartTV tv) {
		tv.runNetfilx();
	}

	@Override
	public void runMenu(SamsungSmartTV tv) {
		tv.runMenu();
	}

	@Override
	public void runCoumangPlay(SamsungSmartTV tv) {
		tv.runCoumangPlay();
	}

}
