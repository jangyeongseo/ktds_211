package com.ktdsuniversity.edu.implement;

/**
 * implements 는 여러개를 인터페이스 할 수 있다.
 * 
 * Before TV SamsungTV - SamsungTV( TV + SamsungSmartTV)
 * 
 * LgSmartTV - LgTv(TV + LgSmartTV)
 * 
 * After TV -SamsungTV - SamsungTV - LgSmartTV - LgTv
 * 
 */
public class SamsungTV implements SamsungSmartTV {

	@Override
	public void turnOn() {
		System.out.println("삼성 TV를 켭니다.");
	}

	@Override
	public void turnOff() {
		System.out.println("삼성 TV를 끕니다.");
	}

	@Override
	public void changeChannel(int channelNumber) {
		System.out.println("삼성 TV의 채넣을 바꿉니다 : " + channelNumber);
	}

	@Override
	public void changeVolumn(int volumn) {
		System.out.println("삼성 TV의 볼륨을 바꿉니다 : " + volumn);
	}

	@Override
	public void runNetfilx() {
		System.out.println("삼성 TV의 넷플릭스를 실행합나다.");
	}

	@Override
	public void runMenu() {
		System.out.println("삼성 TV의 메뉴를 실행합나다.");
	}

	@Override
	public void runCoumangPlay() {
		System.out.println("삼성 TV의 쿠팡플레이를 실행합나다.");
	}

}
