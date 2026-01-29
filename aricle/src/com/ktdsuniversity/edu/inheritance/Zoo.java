package com.ktdsuniversity.edu.inheritance;

public class Zoo {
	public static void main(String[] args) {
		String name = "이름";
		String other = "이름";
		System.out.println(name == other);
		// String 안에는 equals 가 있기 때문에 true 가 나온다

		// Animal(Super Class) duck = new Bird(Sub Class)("오리", "꽥", 15f, 20f, 50f);
		Animal duck = new Bird("오리", "꽥", 64f, 20f, 50f);
		Animal duck2 = new Bird("오리", "꽥", 64f, 20f, 50f); // 실험용

		System.out.println("toSting : " + (duck == duck2)); // 인스턴스의 주소값이 다르기 때문에 false 가 나온다.
		System.out.println("equals : " + (duck.equals(duck2)));
		// 이것을 했을 때 true - 이름이 같을경우 나왔으면 해서 오버라이딩을 작성

		System.out.println();

		duck.howling();
		duck.move();
		System.out.println(duck); // 메소드를 오버라이딩을 한 결과

		// instanceof : duck 인스턴스가 Bird인지
		// 확장성으로 인한 사용
		// 옛날 버전
		if (duck instanceof Bird) {
			Bird bird = (Bird) duck;
			bird.fly();
			bird.land();
		}

		// 현재 버전
		// 티압으로 이한
		if (duck instanceof Bird bird) {
			bird.fly();
			bird.move();

			bird.land();
			bird.move();

			bird.fly(); // 오리는 날고 있다
		}

		Animal lion = new Animal("사자", "크아앙", 80f, 60f, 200f);
		Animal tiger = new Animal("호랑이", "어흥", 50f, 55f, 200f);

		lion.howling();
		tiger.howling();

		lion.move();
		tiger.move();

		lion.attack(tiger);
		lion.attack(duck); // 다형성(Polymorphism)

		tiger.attack(lion);
		duck.attack(lion);

	}
}
