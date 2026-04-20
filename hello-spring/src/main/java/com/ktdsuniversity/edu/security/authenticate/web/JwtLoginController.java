package com.ktdsuniversity.edu.security.authenticate.web;

import java.time.Duration;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktdsuniversity.edu.common.utils.ServletUtils;
import com.ktdsuniversity.edu.exception.HelloSpringApiException;
import com.ktdsuniversity.edu.member.dao.MemberDao;
import com.ktdsuniversity.edu.member.vo.MemberVO;
import com.ktdsuniversity.edu.member.vo.response.LoginVO;
import com.ktdsuniversity.edu.security.authenticate.service.SecurityPasswordEncoder;
import com.ktdsuniversity.edu.security.providers.JsonWebTokenAuthenticationProvider;
import com.ktdsuniversity.edu.security.user.SecurityUser;

import jakarta.validation.Valid;

/**
 * JWT 로그인 전용 컨트롤러
 * 
 * 👉 기존 formLogin 방식이 아니라
 * 👉 API 기반 로그인 (JSON 요청/응답)
 * 👉 로그인 성공 시 JWT 토큰 발급
 */
@Controller
public class JwtLoginController {

	/**
	 * 사용자 조회 서비스
	 * → 이메일로 사용자 정보 조회
	 */
	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * 비밀번호 암호화/검증
	 */
	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * DB 접근 DAO
	 * → 로그인 성공/실패 처리 (횟수 증가, 차단 등)
	 */
	@Autowired
	private MemberDao memberDao;

	/**
	 * JWT 생성 클래스
	 */
	@Autowired
	private JsonWebTokenAuthenticationProvider jsonWebTokenAuthenticationProvider;

	/**
	 * JWT 로그인 API
	 * 
	 * 요청:
	 * POST /api/authorization
	 * Content-Type: application/json
	 * 
	 * body:
	 * {
	 *   "email": "test@test.com",
	 *   "password": "1234"
	 * }
	 * 
	 * 응답:
	 * {
	 *   "token": "JWT토큰"
	 * }
	 */
	@PostMapping("/api/authorization")
	@ResponseBody
	public Map<String, String> doJwtLogin(
			@Valid @RequestBody LoginVO loginVO, // JSON 요청 데이터를 객체로 받는다
			BindingResult bindingResult // 유효성 검사 결과
	) {

		/**
		 * 1. 입력값 검증 실패 시
		 */
		if (bindingResult.hasErrors()) {
			throw new HelloSpringApiException(
					"로그인 실해",
					HttpStatus.BAD_REQUEST.value(),
					bindingResult.getFieldErrors()
			);
		}

		/**
		 * 2. 이메일로 사용자 조회
		 */
		UserDetails userDetails = null;

		try {
			// 내부적으로 DB 조회
			userDetails = this.userDetailsService.loadUserByUsername(loginVO.getEmail());

		} catch (UsernameNotFoundException unfe) {
			// 사용자 없음 → 로그인 실패
			throw new HelloSpringApiException(
					"로그인 실패",
					HttpStatus.BAD_REQUEST.value(),
					"아이디 또는 비밀번호가 일치하지 않습니다."
			);
		}

		/**
		 * 3. 계정 잠금 여부 체크
		 */
		if (!userDetails.isAccountNonLocked()) {
			throw new HelloSpringApiException(
					"로그인 실패",
					HttpStatus.BAD_REQUEST.value(),
					"아이디 또는 비밀번호가 일치하지 않습니다."
			);
		}

		/**
		 * 4. 비밀번호 비교
		 */
		String password = loginVO.getPassword();

		// 우리가 만든 커스텀 Encoder
		SecurityPasswordEncoder securityPasswordEncoder =
				(SecurityPasswordEncoder) this.passwordEncoder;

		// UserDetails → 우리가 만든 SecurityUser
		SecurityUser securityUser = (SecurityUser) userDetails;

		// 실제 DB 사용자 정보
		MemberVO memberVO = securityUser.getMemberVO();

		/**
		 * 비밀번호 불일치
		 */
		if (!securityPasswordEncoder.matches(
				password,               // 입력한 비밀번호
				memberVO.getSalt(),   // DB salt
				memberVO.getPassword() // DB 암호화된 비밀번호
		)) {

			// 로그인 실패 횟수 증가
			this.memberDao.updateIncreaseLoginFailCount(loginVO.getEmail());

			// 일정 횟수 이상이면 계정 잠금
			this.memberDao.updateBlock(loginVO.getEmail());

			throw new HelloSpringApiException(
					"로그인 실패",
					HttpStatus.BAD_REQUEST.value(),
					"아이디 또는 비밀번호가 일치하지 않습니다."
			);
		}

		/**
		 * 5. 로그인 성공 처리
		 */

		// 접속 IP 저장
		loginVO.setIp(ServletUtils.getIp());

		// 로그인 성공 DB 반영 (로그인 시간, 실패횟수 초기화 등)
		this.memberDao.updateSuccessLogin(loginVO);

		/**
		 * 6. JWT 생성
		 */
		String jwt = this.jsonWebTokenAuthenticationProvider.makeJsonWebToken(
				loginVO.getEmail(),
				Duration.ofHours(9) // 9시간 유효
		);

		/**
		 * 7. 토큰 반환
		 */
		return Map.of("token", jwt);
	}
}
