package com.ktdsuniversity.edu.security.authenticate.handlers;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.ktdsuniversity.edu.member.dao.MemberDao;
import com.ktdsuniversity.edu.member.vo.response.LoginVO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/**
 * 로그인 실패 처리를 위한 핸들러가 들어있는 곳
 */
public class LoginFailureHandler implements AuthenticationFailureHandler {

	private static final Logger logger = LoggerFactory.getLogger(LoginFailureHandler.class);

	private MemberDao memberDao; // DB 작업을 위한 DAO

	public LoginFailureHandler(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@Override // 로그인 실패 시 자동 살행되는 메소드
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		logger.error(exception.getMessage(), exception);
		
		String email = request.getParameter("email"); // 로그인 할때 입력한 이메일 가져오기
		
		// 비밀번호 틀림 혹은 존재하지 않는 계정 시
		if (exception instanceof BadCredentialsException) {
			this.memberDao.updateIncreaseLoginFailCount(email); // 로그인 실패 횟수 증가
			this.memberDao.updateBlock(email); // 횟수 이상이면 계정 Block 처리
		}

		// 로그인 페이지 경로
		String loginPagePath = "/WEB-INF/views/member/login.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(loginPagePath); // forward를 위한 dispatcher 생성
		
		// 사용자가 입력했던 이메일 유지 되도록
		LoginVO loginVO = new LoginVO();
		loginVO.setEmail(email);

		// JSP에서 ${inputData.email}로 사용 가능
		// Spring 의 model.addAttribute()와 동일 개념
		request.setAttribute("inputData", loginVO);

		// 에러 메시지 보내주기.
		request.setAttribute("errorMessage", exception.getMessage());

		dispatcher.forward(request, response); // 로그인 페이지로 이동
	}

}
