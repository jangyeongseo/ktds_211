package oop;

public class CarTest {

	public static void main(String[] args) {

		Car kona = new Car("KOKO");
		// new Car() : 인스턴스화 혹은 객체화 / car 클래스를 인스턴스로 생성함.
		// Car kona = new Car(); - 인스턴스를 만들어 Car 타입의 car 변수에 할당
		System.out.println(kona);

		kona.pressEngineStartButton(); // 코나의 시동을 건다.
		kona.pressGasolinPedal(20); // speed : 30

		// kona의 엔진 상태를 출력한다.
		boolean konaEnginState = kona.isEngineStart;
		// Instance.멤버변수 / Instance.인스턴스
		System.out.println("Koan Engine : " + konaEnginState + "\n" + "");

		// kona의 현재 속도를 출력한다.
		int konaSpeed = kona.speed;
		System.out.println("Koan Speed : " + konaSpeed);

		Car carnival = new Car("CARNI");
		System.out.println(carnival);

		carnival.pressGasolinPedal(200);
		carnival.pressEngineStartButton();
		carnival.pressBrakePedal(10);
		carnival.pressBrakePedal(10);

		boolean canrivalEnginState = carnival.isEngineStart;
		System.out.println(canrivalEnginState + "\n" + "");

		int carnivalSpeed = carnival.speed;
		System.out.println(carnivalSpeed);

	}

}
