package com.ktdsuniversity.edu.implement;

/**
 * TV 인 인터페이스
 */
public class LgTv implements LgSmartTV {

	// 구현을 할때는 public 작성
	@Override
	public void turnOn() {
		System.out.println("LG TV를 켭니다.");
	}

	@Override
	public void turnOff() {
		System.out.println("LG TV를 끕니다.");
	}

	@Override
	public void changeChannel(int channelNumber) {
		System.out.println("LG TV의 채넣을 바꿉니다 : " + channelNumber);
	}

	@Override
	public void changeVolumn(int volumn) {
		System.out.println("LG TV의 볼륨을 바꿉니다 : " + volumn);
	}

	@Override
	public void runNetfilx() {
		System.out.println("LG TV의 넷플릭스를 실행합나다.");
	}

	@Override
	public void runIntrnet() {
		System.out.println("LG TV의 인터넷을 실행합나다.");
	}

	@Override
	public void runYoutube() {
		System.out.println("LG TV의 유튜브를 실행합나다.");
	}

}
