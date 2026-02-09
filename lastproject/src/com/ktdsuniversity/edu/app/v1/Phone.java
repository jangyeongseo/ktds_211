package com.ktdsuniversity.edu.app.v1;

public class Phone {
	public static enum Type {
		PERSONAL, HOME, COMPANY
		// 개인, 집, 회사
	}

	private Phone.Type phoneType;
	private String phoneNumber;

	public Phone(Phone.Type phoneType, String phoneNumber) {
		this.phoneType = phoneType;
		this.phoneNumber = phoneNumber;
	}

	public Phone.Type getPhoneType() {
		return this.phoneType;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneType(Phone.Type phoneType) {
		this.phoneType = phoneType;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return phoneType + " : " + phoneNumber;
	}

}
