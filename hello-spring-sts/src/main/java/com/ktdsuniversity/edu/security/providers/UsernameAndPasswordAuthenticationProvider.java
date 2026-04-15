package com.ktdsuniversity.edu.security.providers;

import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ktdsuniversity.edu.members.helpers.SHA256Util;
import com.ktdsuniversity.edu.members.vo.MembersVO;
import com.ktdsuniversity.edu.security.SecurityUser;
import com.ktdsuniversity.edu.security.authentication.service.SecurityPasswordEncoder;

/**
 * Spring Security의 인증(아이디와 비밀번호 일치 검사)을 수행하는 공급자 사용자의 인증정보가 일치할 경우
 * Authentication Token을 발급해 SecurityContext에 저장하도록 한다.
 */
public class UsernameAndPasswordAuthenticationProvider implements AuthenticationProvider {

	/**
	 * 사용자가 로그인할 때 전송한 아이디로 회원의 정보를 조회
	 */
	private UserDetailsService userDetailsService;

	/**
	 * 사용자가 로그인할 때 전송한 비밀번호롸 회원의 비밀번호 비교
	 */
	private PasswordEncoder passwordEncoder;

	// 생성자 만들어 주기
	public UsernameAndPasswordAuthenticationProvider(UserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder) {
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
	}

	/**
	 * 사용자로부터 Spring Security 로그인 요청이 있을 때 마다 실행
	 * 
	 * 사용자가 보내준 아이디와 비밀번호를 인증을 수행한다. UserDetailsService 인터페이스를 이용해 사용자의 정보를 조회하고
	 * PasswordEncoder 인터페이스를 이용해 사용자의 비밀번호를 검증하고 인증정보가 일치할 때만
	 * UsernamePasswordAuthenticationToken을 발급한다.
	 * 
	 * @param authentication : 사용자가 로그인 요청한 정보(아이디, 비밀번호)
	 * @return UsernamePasswordAuthenicationToken
	 */
	@Override
	public @Nullable Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// 로그인에 사용된 사용자의 아이디(이메일)
		String email = authentication.getName();

		// UserDetails => SecrityUser
		// username => 아이디
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
		if (!userDetails.isAccountNonLocked()) {
			throw new LockedException("아이디 또는 비밀번호가 일치하지 않습니다");
		}

		MembersVO membersVO = ((SecurityUser) userDetails).getMembersVO();
		String rawPassword = authentication.getCredentials().toString(); // 다른 인증도 할 수 있어서 Object가 되어 있는거다.
		SecurityPasswordEncoder passwordComparator = (SecurityPasswordEncoder) this.passwordEncoder;

		boolean isMatch = passwordComparator.matches(rawPassword, membersVO.getSalt(), userDetails.getPassword());
		// 날것 그대로의 패스워드와 암호화된 패스워드 비교

		if (!isMatch) {
			throw new BadCredentialsException("아이디 또는 비밀번호가 일치하지 않습니다");
		}

		// Securitycontext에 저장할 인증 토큰 - membersVO를 넣으면 사용자에 개인 정보를 가져올 수 있다.
		return new UsernamePasswordAuthenticationToken(membersVO, userDetails.getPassword(),
				userDetails.getAuthorities());
	}

	/**
	 * 이 인증 공급자가 발급하는 토큰의 종류를 설정.
	 * 
	 * @param Authentication : authentication() 메소드가 발급한 토큰의 클래스 정보가
	 * @return authentication()가 발급한 토큰의 클래스가 적절한지 여부
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
