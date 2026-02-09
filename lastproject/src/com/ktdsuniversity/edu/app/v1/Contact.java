package com.ktdsuniversity.edu.app.v1;

import java.util.ArrayList;
import java.util.List;

public class Contact {
	private String name; // 이름
	private String nickname; // 연락처 이름?
	private String email; // 연락처 이메일
	private String lastName; // 장 - 성
	private String firstName; // 영서 - 이름

	private List<Phone> phones; // 전화번호 목록
	private List<Company> company; // 근무중인 회사 정보
	private String meno; // 메모

	// 생성자
	public Contact() {
		this.phones = new ArrayList<>();
		this.company = new ArrayList<>();
	}

	// getter, setter
	public String getName() {
		return this.name;
	}

	public String getNickname() {
		return this.nickname;
	}

	public String getEmail() {
		return this.email;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public List<Phone> getPhones() {
		return this.phones;
	}

	public List<Company> getCompany() {
		return this.company;
	}

	public String getMeno() {
		return this.meno;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public void setCompany(List<Company> company) {
		this.company = company;
	}

	public void setMeno(String meno) {
		this.meno = meno;
	}

	@Override
	public String toString() {
		return "이름 =" + name + ", 닉네임 =" + nickname + ", 이메일=" + email + ", 성 =" + lastName + ", 이름 =" + firstName
				+ ", 전화번호 목록 =" + phones + ", 근무중인 회사 =" + company + ", 메모 =" + meno;
	}

}
