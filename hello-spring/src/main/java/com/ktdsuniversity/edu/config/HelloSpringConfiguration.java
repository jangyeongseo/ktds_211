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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
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
//@Component의 하위 개념 -> Bean을 정의하는 클래스
@Configuration

//Spring MVC 수동 설정 활성화
//이걸 쓰면 application.yml의 mvc 자동 설정이 무시됨
// - view prefix/suffix
// - static 리소스 경로
@EnableWebMvc

//@PreAuthorize 같은 메서드 보안 활성화
@EnableMethodSecurity
public class HelloSpringConfiguration implements WebMvcConfigurer {

	// DB 접근 DAO
	@Autowired
	private MemberDao memberDao;

	/**
	 * PasswordEncoder Bean 등록 -> 비밀번호 암호화/검증에 사용
	 */
	@Bean
	PasswordEncoder createPasswordEncoder() {
		return new SecurityPasswordEncoder();
	}

	/**
	 * 사용자 조회 서비스 Bean -> 로그인 시 DB에서 사용자 정보 조회
	 */
	@Bean
	UserDetailsService createUserDetailsService() {
		return new SecurityUserDetailsService(this.memberDao);
	}

	/**
	 * 인증 Provider Bean -> 로그인 로직 : 비밀번호 비교 등
	 */
	@Bean
	AuthenticationProvider createAuthenticationProvider() {

		// 위에서 만든 Bean들을 직접 사용
		UserDetailsService userDetailsService = this.createUserDetailsService();
		PasswordEncoder passwordEncoder = this.createPasswordEncoder();

		return new UsernameAndPasswordAuthenticationProvider(userDetailsService, passwordEncoder);
	}

	/**
	 * 로그인 성공 핸들러 -> 로그인 성공 시 DB 업데이트 + redirect 처리
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
	 * Spring Security 필터 체인 설정
	 * -> CORS, 로그인, 인증, 보안 정책을 여기서 전부 정의
	 */
	@Bean
	SecurityFilterChain configureFilterChain(HttpSecurity httpSecurity) {

	    /**
	     * CORS 설정
	     * -> 다른 도메인에서 이 서버로 요청을 보낼 수 있도록 허용하는 설정
	     */
	    httpSecurity.cors(corsConfigurer -> {

	        // 요청이 들어올 때마다 실행되는 CORS 정책 정의
	        CorsConfigurationSource source = (httpServletRequest) -> {

	            // CORS 설정 객체 생성
	            CorsConfiguration config = new CorsConfiguration();

	            // 1️. 허용할 Origin - 요청을 보낼 수 있는 외부 사이트
	            // 예: 특정 IP에서 오는 요청만 허용
	            config.addAllowedOrigin("http://192.168.211.26:8080");

	            // 2️. 허용할 HTTP Method
	            // -> 해당 도메인에서 GET, POST 요청만 허용
	            config.addAllowedMethod("POST");
	            config.addAllowedMethod("GET");

	            // 3️. 허용할 Header
	            // -> 모든 요청 헤더 허용 - Authorization 등 포함 가능
	            config.addAllowedHeader("*");

	            return config;
	        };

	        // 위에서 정의한 CORS 정책을 Security에 적용
	        corsConfigurer.configurationSource(source);
	    });

	    
	    /**
	     * CSRF 설정
	     * -> 기본적으로 POST 요청 시 CSRF 토큰 필요
	     * 필요 시 아래 코드로 비활성화 가능
	     */
	    // httpSecurity.csrf(csrf -> csrf.disable());


	    /**
	     * 로그인 설정 -> Form Login
	     */
	    httpSecurity.formLogin(formLogin ->

	        // 1️. 로그인 페이지 URL (GET 요청)
	        // -> 사용자가 /login으로 접근하면 로그인 페이지 보여줌
	        formLogin.loginPage("/login")

	            // 2️. 로그인 처리 URL (POST 요청)
	            // -> 이 URL로 요청 오면 Spring Security가 가로채서 인증 수행
	            // -> 내부적으로 AuthenticationProvider 실행됨
	            .loginProcessingUrl("/login-provider")

	            // 3️. username 파라미터 이름 변경
	            // 기본값: username -> email로 변경
	            .usernameParameter("email")

	            // 4️. 로그인 성공 시 실행할 핸들러
	            // -> DB 로그인 성공 처리 + redirect 수행
	            .successHandler(this.createLoginSuccessHandler())

	            // 5.️ 로그인 실패 시 실행할 핸들러
	            // -> 로그인 실패 횟수 증가 + 계정 차단 처리
	            .failureHandler(this.createLoginFailureHandler())
	    );

	    /**
	     * 설정 완료 → SecurityFilterChain 생성
	     * -> 이 체인이 실제 요청을 가로채서 보안 처리 수행
	     */
	    return httpSecurity.build();
	}

	/**
	 * ViewResolver 설정 -> JSP 경로 자동 매핑 예: return "login"; ->
	 * /WEB-INF/views/login.jsp
	 */
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/", ".jsp");
	}

	/**
	 * 정적 리소스 경로 설정 -> CSS, JS, 이미지 접근 가능하게 설정
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
