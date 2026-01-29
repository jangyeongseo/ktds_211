package com.ktdsuniversity.edu.inheritance.car;

/**
 * 자동차
 */

public class Vehicle {

	private String modelName; // 자동차 이름
	private boolean isStart; // 시동걸기
	private String engineSound; // 엔진 소리

	// 일반적인 소리
	public Vehicle(String modelName) {
		this.modelName = modelName;
		this.engineSound = "부르릉";
	}

	public Vehicle(String modelName, String engineSound) {
		this.modelName = modelName;
		this.engineSound = engineSound;
	}

	public String getModelName() {
		return this.modelName;
	}

	public boolean getIsStart() {
		return this.isStart;
	}

	public void startEngine() {
		this.isStart = !isStart;
		if (this.isStart) {
			System.out.println(this.modelName + " 시동을 걸었습니다.");
			System.out.println(this.engineSound);
		} else {
			System.out.println(this.modelName + " 시동을 껏습니다.");
		}
	}
}