package com.ktdsuniversity.edu.security.authenticate.handlers;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.ktdsuniversity.edu.common.utils.StringUtils;
import com.ktdsuniversity.edu.member.dao.MemberDao;
import com.ktdsuniversity.edu.member.vo.response.LoginVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/**
 * 로그인 성공 처리를 위한 핸들러가 들어있는 곳
 */
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	// DB 의존성 - DB 작업용 객체
	private MemberDao memberDao;

	public LoginSuccessHandler(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@Override // 로그인 성공하면 자동 호출됨. - authentication 안에 로그인한 사용자 정보 들어 있음
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		// 로그인 정보 담기
		LoginVO loginVO = new LoginVO();
		loginVO.setIp(request.getRemoteAddr()); // 사용자 접속 Ip
		loginVO.setEmail(authentication.getName()); // 로그인한 사용자의 ID - email
		
		this.memberDao.updateSuccessLogin(loginVO); // 로그인 성공 정보 DB에 반영
		
		String go = request.getParameter("go"); 
		// 로그인 전에 접근하려던 페이지
		// 예 : /login?go=/board/list
		
		response.sendRedirect(StringUtils.emptyTo(go, "/")); // go 값이 있으면 그 페이지로 이동 , 없으면 "/" 페이지로 이동
		
	}

}
