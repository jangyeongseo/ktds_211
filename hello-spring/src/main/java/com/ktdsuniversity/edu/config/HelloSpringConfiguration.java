package com.ktdsuniversity.edu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ktdsuniversity.edu.member.dao.MemberDao;
import com.ktdsuniversity.edu.security.authenticate.handlers.LoginFailureHandler;
import com.ktdsuniversity.edu.security.authenticate.handlers.LoginSuccessHandler;
import com.ktdsuniversity.edu.security.authenticate.service.SecurityPasswordEncoder;
import com.ktdsuniversity.edu.security.authenticate.service.SecurityUserDetailsService;
import com.ktdsuniversity.edu.security.providers.UsernameAndPasswordAuthenticationProvider;

//application.yml에서 표현하기 어려운 설정들을 자바 코드로 구성하기 위한 설정 클래스
//@Component의 하위 개념 → Bean을 정의하는 클래스
@Configuration

//Spring MVC 수동 설정 활성화
//이걸 쓰면 application.yml의 mvc 자동 설정이 무시됨
//- view prefix/suffix
//- static 리소스 경로
@EnableWebMvc

//@PreAuthorize 같은 메서드 보안 활성화
@EnableMethodSecurity
public class HelloSpringConfiguration implements WebMvcConfigurer {

	// DB 접근 DAO
	@Autowired
	private MemberDao memberDao;

	/**
	 * PasswordEncoder Bean 등록 → 비밀번호 암호화/검증에 사용
	 */
	@Bean
	PasswordEncoder createPasswordEncoder() {
		return new SecurityPasswordEncoder();
	}

	/**
	 * 사용자 조회 서비스 Bean → 로그인 시 DB에서 사용자 정보 조회
	 */
	@Bean
	UserDetailsService createUserDetailsService() {
		return new SecurityUserDetailsService(this.memberDao);
	}

	/**
	 * 인증 Provider Bean → 로그인 로직 : 비밀번호 비교 등
	 */
	@Bean
	AuthenticationProvider createAuthenticationProvider() {

		// 위에서 만든 Bean들을 직접 사용
		UserDetailsService userDetailsService = this.createUserDetailsService();
		PasswordEncoder passwordEncoder = this.createPasswordEncoder();

		return new UsernameAndPasswordAuthenticationProvider(userDetailsService, passwordEncoder);
	}

	/**
	 * 로그인 성공 핸들러 ->  로그인 성공 시 DB 업데이트 + redirect 처리
	 */
	@Bean
	AuthenticationSuccessHandler createLoginSuccessHandler() {
		return new LoginSuccessHandler(this.memberDao);
	}

	/**
	 * 로그인 실패 핸들러 -> 로그인 실패 횟수 증가 + 계정 차단 처리
	 */
	@Bean
	AuthenticationFailureHandler createLoginFailureHandler() {
		return new LoginFailureHandler(this.memberDao);
	}

	/**
	 * Spring Security 필터 체인 설정 -> 로그인, 인증, 보안 정책 전부 여기서 설정
	 */
	@Bean
	SecurityFilterChain configureFilterChain(HttpSecurity httpSecurity) {

		// 1️ CSRF 보호 비활성화
		// 기본적으로 POST 요청 시 CSRF 토큰 필요
		// 댓글 등록 등에서 에러 발생 시 끄기도 함
		httpSecurity.csrf(csrf -> csrf.disable());

		// 2️ 로그인 설정
		httpSecurity.formLogin(formLogin ->

		// 로그인 페이지 URL (GET)
		formLogin.loginPage("/login")

				// 로그인 처리 URL (POST)
				// -> 이 URL로 요청 오면 AuthenticationProvider 실행됨
				.loginProcessingUrl("/login-provider")

				// 기본 username → email로 변경
				.usernameParameter("email")

				// 로그인 성공 시 실행할 핸들러
				.successHandler(this.createLoginSuccessHandler())

				// 로그인 실패 시 실행할 핸들러
				.failureHandler(this.createLoginFailureHandler()));

		// 설정 완료 후 SecurityFilterChain 생성
		return httpSecurity.build();
	}

	/**
	 * ViewResolver 설정 → JSP 경로 자동 매핑
	 * 예: return "login"; → /WEB-INF/views/login.jsp
	 */
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/", ".jsp");
	}

	/**
	 * 정적 리소스 경로 설정 → CSS, JS, 이미지 접근 가능하게 설정
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		// /css/** → /static/css/
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");

		// /image/** → /static/image/
		registry.addResourceHandler("/image/**").addResourceLocations("classpath:/static/image/");

		// /js/** → /static/js/
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
	}
}
