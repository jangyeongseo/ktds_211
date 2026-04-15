package com.ktdsuniversity.edu.security.authentication.service;

import org.jspecify.annotations.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ktdsuniversity.edu.members.helpers.SHA256Util;

/**
 * 데이터베이에 있는 비밀번호와 로그인 요텅 정보의 비밀번호가 일치하는지 검사
 * 
 * 필요한 데이터 1. 데이터베이시의 회원 비밀번호(암호화되어 있는 비밀번호) 2. 로그인 요청 정보 중 비밀번호(암호화되어있지 않은
 * 비밀번호) 3. 로그인 요청 정보 중 비밀번호를 암호하 하기 위한 salt 정보
 */
public class SecurityPasswordEncoder implements PasswordEncoder {

	/**
	 * 로그인 요청 정보 중 비밀번호를 암호화 하는 코드
	 * 
	 * SHA 암호화 코드에서는 사용 불가!
	 * 
	 * @param rawPassword : 암호화 되어있지 않은 평문 비밀번호
	 * @return 암호화된 비밀번호
	 */
	@Override
	public @Nullable String encode(@Nullable CharSequence rawPassword) {
		// CharSequence -> String과 같다.

		return null;
	}

	/**
	 * 로그인 요청 정보 중 평문 비밀번호와 데이터베이스에 있는 암호화된 비밀번호가 일치하는지 검사 평문 비밀번호 ==> 암호화 ==>
	 * 데이터베이스의 암호화 비교
	 */
	@Override
	public boolean matches(@Nullable CharSequence rawPassword, @Nullable String encodedPassword) {
	    // 이 메소드는 salt 가 없어서 SHA256Util을 직접 쓰기 어렵다면, 
	    // 현재 Provider 에서 직접 만든 커스텀 matches 를 호출하고 있으니 
	    // 최소한의 방어 코드로 두거나, 아예 커스텀 로직에 집중
	    return false; 
	}

	public String encode(String rawPassword, String salt) {
		return SHA256Util.getEncrypt(rawPassword, salt);
	}
	
	// 현재 Provider 에서 사용
	public boolean matches(String rawPassword, String salt, String encodedPassword) {
	    return this.encode(rawPassword, salt).equals(encodedPassword);
	}

}
