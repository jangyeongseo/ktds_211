package com.ktdsuniversity.edu.security.user;

import java.util.Collection;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ktdsuniversity.edu.member.vo.MemberVO;

public class SecurityUser implements UserDetails {
	private static final long serialVersionUID = 7029567271051047554L;

	// 멤버의 정보를 가져와야함
	private MemberVO memberVO;

	// 오버로딩을 이용하여 값 전달.
	public SecurityUser(MemberVO memberVO) {
		this.memberVO = memberVO;
	}

	public MemberVO getMemberVO() {
		return this.memberVO;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public @Nullable String getPassword() {
		// 로그인할 때 사용할 비밀번호
		return this.memberVO.getPassword(); // DB에 저장된 암호화된 비밀번호
	}

	@Override
	public String getUsername() {
		// 로그인할 때 사용할 이메일
		return this.memberVO.getEmail(); // DB에 저장된 이메일
	}

	@Override
	public boolean isAccountNonLocked() {
		// DB의 BLOCK_YN 컬럼을 기준으로 계정 잠금 여부를 판단하는 구조
		return this.memberVO.getBlockYn().equals("N");
	}

}
