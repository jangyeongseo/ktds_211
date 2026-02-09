package com.ktdsuniversity.edu.app.v2;

public class Contact {

	/**
	 * 연락처의 이름
	 */
	private String name;

	/**
	 * 연락처 별명
	 */
	private String nickname;

	/**
	 * 연락처 이메일
	 */
	private String email;

	/**
	 * 이름
	 */
	private String firstName;

	/**
	 * 성
	 */
	private String lastName;

	/**
	 * 전화번호
	 */
	private String phone;

	/**
	 * 메모
	 */
	private String memo;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String toString() {
		return "이름 : " + name + ", 별명 : " + nickname + ", 이메일 : " + email + ", 이름 : " + firstName + ", 성 : " + lastName
				+ ", 연락처 : " + phone + ", 메모 : " + memo;
	}

}