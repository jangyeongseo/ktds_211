package com.ktdsuniversity.edu.security.authenticate.service;

import org.jspecify.annotations.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ktdsuniversity.edu.member.helpers.SHA256Util;

public class SecurityPasswordEncoder implements PasswordEncoder{

	@Override // SHA  암호화 코드에서는 사용불가
	public @Nullable String encode(@Nullable CharSequence rawPassword) {
		return null;
	}

	@Override
	public boolean matches(@Nullable CharSequence rawPassword, @Nullable String encodedPassword) {
		return false;
	}
	
	// rawPassword - 사용자가 입력한 평문 비밀번호, salt - 사용자마다 다른 렌덤 값
	public String encode(String rawPassword, String salt) {
		return SHA256Util.getEncrypt(rawPassword, salt); // SHA256 해시 수행
	}
	
	// 비밀번호 검증 메소드
	public boolean matches(String rawPassword, String salt, String encodePassword) {
		return this.encode(rawPassword, salt).equals(encodePassword); // 같으면 true / 다르면 false
	}

}
