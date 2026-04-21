package com.ktdsuniversity.edu.members.vo.request;

public class OAuthMembersVO {

	private String registrationId;
	private String email;
	private String name;

	public synchronized String getRegistrationId() {
		return this.registrationId;
	}

	public synchronized void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	public synchronized String getEmail() {
		return this.email;
	}

	public synchronized void setEmail(String email) {
		this.email = email;
	}

	public synchronized String getName() {
		return this.name;
	}

	public synchronized void setName(String name) {
		this.name = name;
	}

}
