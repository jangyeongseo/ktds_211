package com.ktdsuniversity.edu.inheritance.car;

/**
 * 스포츠카
 */

public class SporteCar extends Vehicle {
	public boolean enabledTurbo; // 터보모드

	// Vehicle의 생성자
	public SporteCar(String modelName, String engineSound) {
		super(modelName, engineSound);
	}

	public void startTurbo() {
		if (super.getIsStart()) {
			this.enabledTurbo = !this.enabledTurbo;

			String modelName = super.getModelName();
			if (this.enabledTurbo) {
				System.out.println(modelName + " 터보 시작됐습니다.");
			} else {
				System.out.println(modelName + " 터보 종료됐습니다.");
			}
		}
	}

}