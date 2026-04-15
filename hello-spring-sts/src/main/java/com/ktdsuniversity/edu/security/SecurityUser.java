package com.ktdsuniversity.edu.security;

import java.util.Collection;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ktdsuniversity.edu.members.vo.MembersVO;

/**
 * Spring Security가 사용자를 식별할 때 사용
 */
public class SecurityUser implements UserDetails {

	/**
	 * 이 객체들을 식별하기 위한 번호다
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * UserDetails 인터페이스로 사용자의 세부 내용을 알 수 없기 때문에 사용자으 ㅣ정보를 가지고 있는 membersVO를 멤버변수로
	 * 추가해준다.
	 */
	private MembersVO membersVO;

	public MembersVO getMembersVO() {
		return this.membersVO;
	}

	public SecurityUser(MembersVO membersVO) {
		this.membersVO = membersVO;
	}

	/**
	 * 사용자의 궈난 목록을 관리 추후 권한 병 서비스 제공시 사용. ROLES 테이블에서 조회
	 * 
	 * GrantedAuthority <-- 사용자에게 허용된 권한 Collection <-- List/ Set
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// Spring Security 가 체크하는 권한 2가지
		// 1. ROLE ==> 권한
		// 2. ACTION => 생성, 조회, 수정, 삭제, 다운로드, 업로드, .... 등등
		// Spring Security 가 ROLE과 ACTION 을 구분하는 방법
		// ROLE => Prefix => 'ROLE_RL-20260414-000001'
		// ACTION => ACTION 이름으로 작성(CRATE, READ, MODIFY, DELETE, UPDATE 등등)

		return this.membersVO.getRoles()
							.stream()
							.map(role -> new SimpleGrantedAuthority("ROLE_" + role))
							.toList();
	}

	/**
	 * 로그인 한 회원의 비밀번호
	 */
	@Override
	public @Nullable String getPassword() {
		// DB에서 가져온 암호화된 비밀번호를 반환해야 합니다.
	    return this.membersVO.getPassword();
	}

	/**
	 * 사용자의 아이디(식별가능한) --> 우리 케이스(테이블)에서는 email 을 말함 boolean 값을 이용하여 사용자에게 정보를 주고 싶을
	 * 때 isAccount~Expired isCredentialsNonExpired isEnabled
	 */
	@Override
	public String getUsername() {
		return this.membersVO.getEmail();
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.membersVO.getBlockYn().equals("N"); // n이면 잠기지 않은거다.
	}

}
