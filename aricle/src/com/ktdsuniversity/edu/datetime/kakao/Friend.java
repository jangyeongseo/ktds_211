package com.ktdsuniversity.edu.datetime.kakao;

import java.time.LocalDate;

public class Friend {
	private String name;
	private LocalDate birthdate;

	public Friend(String name, String birthdate) {
		super();
		this.name = name;
		this.birthdate = LocalDate.parse(birthdate);
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	@Override
	public String toString() {
		return "이름 : " + this.name + ", 생일 : " + this.birthdate;
	}

}
