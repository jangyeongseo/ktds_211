package com.ktdsuniversity.edu.member.vo.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class MemberWriteVO {
	@NotEmpty(message = "이메일을 입력해야 합니다.")
	@Email(message = "이메일 형태가 아닙니다.")
	private String email;

	@NotEmpty(message = "비밀번호를 입력해야 합니다.")
	@Pattern(message = "특수문자로 시작해서 영어소문자 혹은 대문자, 숫자 최소 1개 이상의 8자리를 입력해주세요.", regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$")
	private String password;
	private String name;

	private String salt;

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public synchronized String getName() {
		return this.name;
	}

	public synchronized void setName(String name) {
		this.name = name;
	}

}
