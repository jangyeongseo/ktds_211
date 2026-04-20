package com.ktdsuniversity.edu.security.authenticate.filters;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ktdsuniversity.edu.common.utils.StringUtils;
import com.ktdsuniversity.edu.security.providers.JsonWebTokenAuthenticationProvider;
import com.ktdsuniversity.edu.security.user.SecurityUser;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 이 클래스의 객체가 Security Filter Chain에 등록되어 이늦ㅇ이 필요한 모든 End point 가 실행되기 전에
 * AuthenticationToken(UsernamePasswordAuthenticationToken)을 생성하도록 하는 필터
 * 
 * HttpServletRequest 의 header로 전달된 Authorization에 들어 있는 JWT를 가져와 분석(복호화 및 검증)을
 * 진행하고 분석된 결과를 AuthenticationToken으로 생성시킨다.
 */
public class JsonWebTokenAuthenticationFilter extends OncePerRequestFilter {
	// OncePerRequestFilter 상속을 박으면 WebAsyncManager ~ 들어갈 수 있다.

	private JsonWebTokenAuthenticationProvider jsonWebTokenAuthenticationProvider;
	private UserDetailsService userDetailsService;

	public JsonWebTokenAuthenticationFilter(JsonWebTokenAuthenticationProvider jsonWebTokenAuthenticationProvider,
			UserDetailsService userDetailsService) {
		this.jsonWebTokenAuthenticationProvider = jsonWebTokenAuthenticationProvider;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// 다음 필터가 동작되기 이전의 이 필터가 해야 할 일 작성.
		// preHandle

		// Authorization 이 존재하는지 확인하는 조건
		// 요청 URL이 "/api/"로 시작하는 경우만 실행한다.
		// 요청 URL 가져오기
		String requestURI = request.getServletPath();
		// requestURI -> /api/articles 만 가져오도록 : Http://`~~~~~ 에서 저것만 가져와주는거다.
		if (requestURI.startsWith("/api/")) {
			// Request에서 header에 있는 Authorization을 꺼내온다.
			String jsonWebToken = request.getHeader("Authorization");

			// jsonWebToken 이 없다면 -> header에 Authorization이 전달되지 않았따면
			// 사용에게 "인증이 필요합니다" 메세지 보내기
//			if (StringUtils.isEmpty(jsonWebToken)) {
//				String errorMessage = "{\"error\" : \"인증이 필여합니다.\"}";
//
//				// json 반환 시작
//				response.setCharacterEncoding("UTF-8");
//				response.setContentType("application/json");
//
//				PrintWriter writer = response.getWriter();
//				writer.append(errorMessage);
//				writer.flush();
//				return;
//
//			}
			
			// 훨신더 합리적인 방식이
			if (!StringUtils.isEmpty(jsonWebToken)) {
				// JWT를 복호화시켜 email을 가져온다.
				String email = this.jsonWebTokenAuthenticationProvider.decryptJsonWebToken(jsonWebToken);
				
				// email을 이용해 사용자의 정보와 권한을 조회한다.
				UserDetails userDetails =  this.userDetailsService.loadUserByUsername(email);
				SecurityUser securityUser = (SecurityUser)userDetails;
				
				// 사용자의 정보를 이용해 AuthenticationToken(UsernamePasswordAuthenticationToken) 을 발행한다.
				Authentication authToken = new UsernamePasswordAuthenticationToken(securityUser.getMembersVO(), userDetails.getPassword(), userDetails.getAuthorities());
				
				// 발행한 AuthenticationToken을 SecurityContext에 적재시킨다. (일회용 토큰)
				SecurityContextHolder.getContext().setAuthentication(authToken);
				
			}

			
		}

		filterChain.doFilter(request, response); // 이 다음 필터가 있다면 그 다음 필터를 동작시킨다.

		// 모든 필터가 동작이 완료된 Filter Chain의 역순으로 응답이 돌아 올 때
		// 이 팔터가 해야할 일 작성
		// postHandle
	}

}
