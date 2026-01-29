package com.ktdsuniversity.edu.implement;

public interface RemoteController {
	void turnOn(TV tv);

	void turnOff(TV tv);

	void changeChannel(TV tv, int channelNumber);

	void changeVolumn(TV tv, int volumn);
}
