package com.ktdsuniversity.edu.security.providers;

import java.time.Duration;
import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

/**
 * JWT(Json Web Token) 생성 클래스
 * - Spring Security의 AuthenticationProvider와는 별개 
 * - API 인증(토큰 기반 인증)에 사용
 */
public class JsonWebTokenAuthenticationProvider {

	/**
	 * 사용자의 이메일로 JWT 생성
	 * 
	 * @param email     사용자 이메일 - 토큰 식별용
	 * @param expiredAt 토큰 유효기간 (예: 3시간)
	 * @return 생성된 JWT 문자열
	 */
	public String makeJsonWebToken(String email, Duration expiredAt) {

		// 1️. 토큰 발행 시간 - 현재 시간으로
		Date issueData = new Date();

		// 2️. 토큰 만료 시간
		// 현재 시간 + 유효기간
		Date expirationDate = new Date(issueData.getTime() + expiredAt.toMillis());

		// 3️. JWT 서명에 사용할 비밀 키 생성
		// 실제로는 application.yml에 두는 것이 좋음 - 하는 방법은 나중에 하는걸로 하고 우선 넣음.
		// TODO application.yml에 작성한 비밀키
		SecretKey key = Keys.hmacShaKeyFor("a7F3kP9xQ2mZ8Lw1R5bT6YcH4uV0dN3sJ8eKpX2GqM7".getBytes());

		// 4️. JWT 생성
		String jsonWebtoken = Jwts.builder()
				// 토큰 발급자 (issuer)
				// TODO application.yml에 작성한 시스템 이름
				.issuer("hello-spring-sts")

				// 토큰 제목 (subject)
				.subject(email + "_token")

				// 토큰에 포함될 데이터 (claims)
				// 민감한 정보는 넣지 않는 것이 중요 - 개인정보
				.claim("identify", email)

				// 발행 시간
				.issuedAt(issueData)

				// 만료 시간
				.expiration(expirationDate)

				// 서명 (암호화)
				// -> 이 키로 위조 여부 검증 가능
				.signWith(key)

				// 문자열 토큰 생성
				.compact();

		// 생성된 JWT 반환
		return jsonWebtoken;
	}

	/**
	 * 테스트용 main 메서드
	 */
	public static void main(String[] args) {
		// JWT 생성 객체
		JsonWebTokenAuthenticationProvider jsonProvider = new JsonWebTokenAuthenticationProvider();
		// 3시간짜리 토큰 생성
		String jwt = jsonProvider.makeJsonWebToken("test@naver.com", Duration.ofHours(3));

		// 콘솔 출력 - 토큰 확인
		System.out.println(jwt);
	}
}