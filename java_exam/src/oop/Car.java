package oop;

/**
 * 문서 주석
 * 
 * <pre>
 * 객체 지향 프로그래밍의 첫 번째 실습.
 * 자동차릐 속성과 기능을 구현함으로서 클래스와 객체에 대해 이해한다.
 * </pre>
 */

public class Car {
	// 클래스 - 26.01.21
	/**
	 * <pre>
	 * 엔진의 시동 여부 엔진이 꼬져있을 때는 가속 페덜울 누르면 브래이크 페달을 눌러도 아무런 효과가 없다. 
	 * 엔진이 켜져 있을 떄는 누르는 페달에 따라 속도가 변화된다.
	 * </pre>
	 */

	boolean isEngineStart;

	/**
	 * <pre>
	 * 자동차의 현재 속도를 표현한다. 
	 * 가속 페달을 누르면 속도가 증가한다. 
	 * 브레이크 페달을 누르면 속도가 감소한다.
	 * </pre>
	 */

	int speed;

	// 인스턴스 메소드
	/**
	 * <pre>seating
	 * 현재 엔진의 상태에 따라서 시동을 켜거나 끈다.
	 * 시동이 걸리면 isEnfineStart 의 값이 true, 꺼지면 false 가 된다.
	 * 시동이 꺼지면 speed 의 값은 0이 된다.
	 * 시동이 켜지면 speed 의 값은 10이 된다.
	 * </pre>
	 */

	public void pressEngineStartButton() {
		if (isEngineStart) {
			isEngineStart = false;
			speed = 0;
			System.out.println("시동을 끕니다.");
			System.out.println("현재 속도 : " + speed);

		} else {
			isEngineStart = true;
			speed = 10;
			System.out.println("시동을 켭니다.");
			System.out.println("현재 속도 : " + speed);

		}
	}

	/**
	 * <pre>
	 * 시동이 켜져 있을 떄 가속 페달을 누르는 압력만큼 속도가 증가된다.
	 * </pre>
	 * 
	 * @param press 가속 페달을 누르는 압력의 정도.
	 */

	public void pressGasolinPedal(int press) {
		if (isEngineStart) {
			speed -= press;
			if (speed < 0) {
				speed = 0;
				// 이렇게 작성시 음수가 될 수 없도록 처리.
			}
			System.out.println("현재 속도 : " + speed);

		} else {
			speed = 10;
			System.out.println("현재 속도 : " + speed);

		}
	}

	/**
	 * <pre>
	 * 시동이 켜져 있을 때 브레이크 페달을 누르는 압력만큼 속도가 감소한다.
	 * </pre>
	 * 
	 */

	public void pressBrakePedal(int press) {

	}

	/*
	 * 클래스의 속성 (멤버 변수 | 인스턴스 필드) 클래스의 속성(임시변수 | 지역변수) public static void
	 * main(String[] args)
	 */

	// 지역 변수. (local variable)

}
