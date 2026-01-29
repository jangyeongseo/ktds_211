package com.ktdsuniversity.edu.implement;

public interface TV {
	// public abstract void turnOn(); public abstract 가 숨겨져 있는 상황
	void turnOn();

	void turnOff();

	void changeChannel(int channelNumber);

	void changeVolumn(int volumn);

}
