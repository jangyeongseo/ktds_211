package com.ktdsuniversity.edu.inheritance.car;

/**
 * 전기차
 */
public class Ev extends Vehicle {
	private int batteryAmount; // 배터리

	public Ev(String modelName) {
		super(modelName, "위이잉~");
	}

	public void printBatteryAmount() {
		if (super.getIsStart()) {
			String modelName = super.getModelName();
			System.out.println(modelName + " 배터리 잔량은 " + this.batteryAmount + "입니다.");
		}
	}
}