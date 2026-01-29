package com.ktdsuniversity.edu.implement;

public class LgSmartRemotrController implements LgRemoteController {

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
	public void runNetfilx(LgSmartTV tv) {
		tv.runNetfilx();
	}

	@Override
	public void runIntrnet(LgSmartTV tv) {
		tv.runIntrnet();
	}

	@Override
	public void runYoutube(LgSmartTV tv) {
		tv.runYoutube();
	}

}
