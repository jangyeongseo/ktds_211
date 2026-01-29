package com.ktdsuniversity.edu.inheritance;

public class Animal {
	private String name; // 이름
	private String voice; // 목소리
	private float speed; // 동물의 속도

	private float damage; // 데미지
	private float hitPoint; // 히든 포인트

	public Animal(String name, String voice, float speed, float damage, float hitPoint) {
		this.name = name;
		this.voice = voice;
		this.speed = speed;
		this.damage = damage;
		this.hitPoint = hitPoint;
	}

	// name 에 관한 겟터
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// 동물의 속도 set 셋터
	public void setSpeed(float speed) {
		this.speed = speed;
	}

	// 동물의 하울링
	public void howling() {
		System.out.println(this.name + " : " + this.voice);
	}

	// 동물의 움직임
	public void move() {
		System.out.println(this.name + " : " + this.speed + "km/h의 속도로 움직임");
	}

	// 공격을 한다
	public void attack(Animal other) {
		if (this.isDead()) {
			System.out.println(this.name + "이 이미 죽었습니다.");
			return;
		}

		// attack 공격을 하고 있는 내가 Bird 새인지 / !this 은 없음
		// 나는 새가 아니다 그리고 재는 새다 / Bird bird : 형변환
		if (!(this instanceof Bird) && other instanceof Bird bird) {
			// bird 가 날고 있으면 공격 불가 - 난는 새가 아니여서
			if (bird.getisFly()) {
				System.out.println(other.getName() + "이 비행 중이라 공격할 수 없습니다.");
				return;
			}
		}

		System.out.println(this.name + "이 " + other.getName() + "에게 공격하려 합니다.");
		if (!other.isDead()) {
			other.getDanage(this.damage);
		} else {
			System.out.println(other.getName() + "이 이미 죽었습니다.");
		}
	}

	// 공격을 받는다.
	public void getDanage(float damage) {
		System.out.println(this.name + "이 " + damage + "만큼 피해를 입었습니다.");
		this.hitPoint -= this.damage;
	}

	// 히든 포인트가 없으면 죽은 것이다.
	public boolean isDead() {
		return this.hitPoint <= 0;
	}

	// 오브젝트에 있는 equals를 오버라이딩 한다.
	// bird의 새에 이름이 같을 때 true가 나오도록 하기 위한 코드
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Animal other) {
			return other.getName().equals(this.name);
		}

		return super.equals(obj);
	}
	
	


}
