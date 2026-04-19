package com.ktdsuniversity.edu.security.providers;

import java.time.Duration;
import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

/**
 * 사용자의 정보를 이용해 인증 객체를 생성하고 검증하는 클래스. Spring Security AuthenticationProvider와는
 * 무관하다. 사용 목적 : API를 호풀할 때 인증수단으로 사용하기 위해
 */
public class JsonWebTokenAuthenticationProvider {

	/**
	 * 사용자의 이메일을 이용해 인증용 JWT를 생성한다
	 * 
	 * @param email    사용자의 이메일
	 * @param expredAt JWT의 유효기간(지금으로부터 ~분까지(시간, 일, 월, 연) 유효 - 토큰이 길어지면 길어질 수록 좋지 않다
	 * @return email 과 expredAt으로 생성한 JsonWebToken
	 */
	public String makeJsonWebToken(String email, Duration expiredAt) {
		// JsonWebToken이 발생되는 날짜와 기단을 생성.

		Date issueData = new Date();
		// JsonWebToken이 만료되는 날짜와 시간을 생성
		// 발행 날짜 시간 + expiredAt
		Date expirationDate = new Date(issueData.getTime() + expiredAt.toMillis());

		// application.yml에 작성한 비밀키
		// TODO application.yml에 작성한 비밀키
		SecretKey key = Keys.hmacShaKeyFor("a7F3kP9xQ2mZ8Lw1R5bT6YcH4uV0dN3sJ8eKpX2GqM7".getBytes());

		// JWT를 생성하고 만둘어준다 - JWTS
		String jsonWebtoken = Jwts.builder()
				// JsonWebTokendmf 발생한 시스템의 이름
				// TODO application.yml에 작성한 시스템 이름
				.issuer("hello-spring-sts")
				// JsonWebToken의 이름
				.subject(email + "_token")
				// JsonWebToken에 포함되어야 할 회원의 정보들 claim(key,value) - 개인정보가 들어가면 들어갈 수록 위험해진다.
				.claim("identify", email)
				// JsonWebToken을 발행한 날짜와시간
				.issuedAt(issueData)
				// JsonWebToken의 만료되는 날짜와 시간
				.expiration(expirationDate)
				// 평문으로 구성된 JsonWebToken을 암호화 또는 복호화 시킬 때 사용할 키(=salt)
				.signWith(key)
				// Jwts에 ㅔㅈ동된 데이터를 이용해 String Type의 Token을 생성
				.compact();

		return jsonWebtoken;
	}
	
	public static void main(String[] args) {
		JsonWebTokenAuthenticationProvider jsonProvider = new JsonWebTokenAuthenticationProvider();
		String jwt = jsonProvider.makeJsonWebToken("test@naver.com", Duration.ofHours(3));
		System.out.println(jwt); // 토큰 확인
	}

}
