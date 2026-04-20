package com.ktdsuniversity.edu.security.providers;

import java.time.Duration;
import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

/**
 * 사용자의 정보를 이용해 인증 객체를 생성하고 검증하는 클래스. Spring Security AuthenticationProvider와는
 * 무관하다. 사용 목적 : API를 호풀할 때 인증수단으로 사용하기 위해
 */
public class JsonWebTokenAuthenticationProvider {

	private String secretKey; // secert-key: a7F3kP9xQ2mZ8Lw1R5bT6YcH4uV0dN3sJ8eKpX2GqM7
	private String issuer; // issuer: hello-spring-sts

	public JsonWebTokenAuthenticationProvider(String secretKey, String issuer) {
		super();
		this.secretKey = secretKey;
		this.issuer = issuer;
	}

	/**
	 * 사용자가 요청할 때마다 Request Header[Authorization]에 전달한 JsonWebToken을 가져와 복호화 시킨다.
	 * 복호화 된 결과에서 사용자의 이메일(identify)을 추출하여 반환시킨다.
	 * 
	 * @param jsonWebToken 사용자가 전달한 토큰
	 * @return jsonWebToken에서 추출한 사용자의 이메일
	 */
	public String decryptJsonWebToken(String jsonWebToken) {

		// application.yml에 작성한 비밀키
		// 암, 복호화 키 생성
		SecretKey key = Keys.hmacShaKeyFor(this.secretKey.getBytes());

		// .claim("identify", email) 이 정보를 가져오겠다
		Claims claims = Jwts.parser() // JsonWebToken을 분석하기 위한 선언.
				.verifyWith(key) // JsonWebToken을 복호화 하기 위한 비밀키 지정
				.requireIssuer(this.issuer) // 사용자가 전달한 JsonWebToken이 hello-spring-sts시스템에서 만든것인지 확인한다.
				.build() // JsonWebToken을 복호화 시작
				.parseSignedClaims(jsonWebToken) // 사용자가 전달한 JsonWebToken을 복호화 한다.
				.getPayload(); // 복호화된 결과에서 claim들만 모아 반환시킨다. - Map 의 형태

		// 사용자가 전달한 JsonWebToken을 복호화 한 뒤 identify 값을 추출한다.
		String email = claims.get("identify", String.class);
		return email;
	}

	/**
	 * 사용자의 이메일을 이용해 인증용 JWT를 생성하고 결과를 사용자에게 보내주어야 한다.
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
		SecretKey key = Keys.hmacShaKeyFor(this.secretKey.getBytes());

		// JWT를 생성하고 만둘어준다 - JWTS
		String jsonWebToken = Jwts.builder()
				// JsonWebTokendmf 발생한 시스템의 이름
				.issuer(this.issuer)
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

		return jsonWebToken;
	}

	public static void main(String[] args) {
		JsonWebTokenAuthenticationProvider jsonProvider = new JsonWebTokenAuthenticationProvider("a7F3kP9xQ2mZ8Lw1R5bT6YcH4uV0dN3sJ8eKpX2GqM7","hello-spring-sts");
		String jwt = jsonProvider.makeJsonWebToken("test@naver.com", Duration.ofHours(3));
		System.out.println(jwt); // 토큰 확인

		// 복호화 진행
		String email = jsonProvider.decryptJsonWebToken(jwt);
		System.out.println(email); // 토큰
	}

}
