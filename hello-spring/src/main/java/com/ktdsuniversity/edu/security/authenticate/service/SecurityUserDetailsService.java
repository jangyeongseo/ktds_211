package com.ktdsuniversity.edu.security.authenticate.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ktdsuniversity.edu.member.dao.MemberDao;
import com.ktdsuniversity.edu.member.vo.MemberVO;
import com.ktdsuniversity.edu.security.user.SecurityUser;
/**
 * 로그인 시 사용자 정보를 DB에서 조회
 */
public class SecurityUserDetailsService implements UserDetailsService {

	// DAO 의존성
	private MemberDao memberDao;

	public SecurityUserDetailsService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@Override // 로그인 시 Spring Secuirty 기 자동 호출
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO loadedUser = this.memberDao.selectMemberByEmail(username); 
		// username - 로그인페이지에서 입력한 이메일
		// DB에서 이메일로 회원 조회
		
		// 사용자가 없을 경우
		if (loadedUser == null) {
			throw new UsernameNotFoundException("아이디 또는 비밀번호가 일치하지 않습니다.");
		}
		
		// 권한 조회
		List<String> userRole = this.memberDao.selectMemberRolesByEmail(username);
		loadedUser.setRoles(userRole);
		
		return new SecurityUser(loadedUser); 
		// SecurityUser -> UserDetails 구현체
		// username(이메일), password(암호화된 비밀번호), 권한 목록, 계정 상태
		
	}

}
