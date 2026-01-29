package com.ktdsuniversity.edu.inheritance;

/**
 * 새 extends : 상속
 */
public class Bird extends Animal {
	private boolean isFly;
	private float walkingSpeed;

	public Bird(String name, String voice, float speed, float damage, float hitPoint) {
		super(name, voice, speed, damage, hitPoint); // super : 수퍼
		// TODO Auto-generated constructor stub
		this.walkingSpeed = speed;
	}

	public boolean getisFly() {
		return this.isFly;
	}

	// 나는 속도
	public void fly() {
		this.isFly = true;
		// super.speed = 70f;
		// super 키워드: 현재 클래스의 부모(상위) 클래스를 가리킵니다.
		super.setSpeed(70f);
	}

	// 원래의 속도
	public void land() {
		this.isFly = true;
		super.setSpeed(this.walkingSpeed);
	}

	// Bird 클래스의 최종 Super Class 인 Object 클래스의 toString() 메소드를 다시 정의한다.
	// Animal 클래스의 toString 메소드를 Bird에서 재정의
	// 메소드 오버라이딩
	@Override
	public String toString() {
		String str = "Bird[name: %s, isFly: %s]";
		return str.formatted(super.getName(), this.isFly);
	}

}
